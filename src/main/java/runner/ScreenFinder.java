package runner;

import annotations.MVC;
import core.Controller;
import core.Model;
import core.View;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ScreenFinder {

    /**
     * Validates that all the classes make MVC Groups (no missing parts / duplicates), and lists them
     *
     * @return the list of screen creators
     */
    @SuppressWarnings("unchecked")
    public static ScreenCreator<?, ?, ?>[] find() throws ScreenMissingPartsException, DuplicateScreenException {
        Reflections reflections = new Reflections("");
        // name: Model, View, Controller
        Map<String, Tuple<Class<? extends Model>, Class<? extends View>, Class<? extends Controller>>> mvc = new HashMap<>();

        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(MVC.class);

        // add all the mvc components
        for (Class<?> annotatedClass : annotatedClasses) {
            String key = annotatedClass.getAnnotation(MVC.class).value();
            Tuple<Class<? extends Model>, Class<? extends View>, Class<? extends Controller>> v = mvc.getOrDefault(key, new Tuple<>(null, null, null));

            if (Model.class.isAssignableFrom(annotatedClass)) {
                if (v.first != null) throw new DuplicateScreenException("Duplicate model " + annotatedClass);
                v = new Tuple<>((Class<? extends Model>) annotatedClass, v.second, v.third);
            } else if (View.class.isAssignableFrom(annotatedClass)) {
                if (v.second != null) throw new DuplicateScreenException("Duplicate view " + annotatedClass);
                v = new Tuple<>(v.first, (Class<? extends View>) annotatedClass, v.third);
            } else if (Controller.class.isAssignableFrom(annotatedClass)) {
                if (v.third != null) throw new DuplicateScreenException("Duplicate controller " + annotatedClass);
                v = new Tuple<>(v.first, v.second, (Class<? extends Controller>) annotatedClass);
            } else {
                throw new IllegalArgumentException("Annotated class " + annotatedClass + " does not extend from Model, View, or Controller");
            }

            mvc.put(key, v);
        }

        ArrayList<ScreenCreator<?, ?, ?>> creators = new ArrayList<>();

        for (Map.Entry<String, Tuple<Class<? extends Model>, Class<? extends View>, Class<? extends Controller>>> entry : mvc.entrySet()) {
            if (entry.getValue().first == null)
                throw new ScreenMissingPartsException("Screen " + entry.getKey() + " is missing a model.");
            if (entry.getValue().second == null)
                throw new ScreenMissingPartsException("Screen " + entry.getKey() + " is missing a view.");
            if (entry.getValue().third == null)
                throw new ScreenMissingPartsException("Screen " + entry.getKey() + " is missing a controller.");

            creators.add(new ScreenCreator<>(entry.getValue().first, entry.getValue().second, entry.getValue().third, entry.getKey()));
        }

        return creators.toArray(new ScreenCreator[0]);
    }

    private static class Tuple<T1, T2, T3> {
        public final T1 first;
        public final T2 second;
        public final T3 third;

        public Tuple(T1 first, T2 second, T3 third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }
}
