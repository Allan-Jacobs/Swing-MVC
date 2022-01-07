package runner;

import annotations.MVC;
import core.Controller;
import core.Model;
import core.View;
import org.reflections.Reflections;
import util.AnnotationFinder;
import util.ReflectionsServiceImpl;
import util.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to find screens annotated with @MVC
 *
 * @see GUI
 */
public class ScreenFinder {

    /**
     * Finds Models, Views, and Controllers annotated with @MVC, and returns them as screen creators.
     *
     * @return the list of screen creators
     * @throws ScreenMissingPartsException the screen is missing a Model, View, or Controller
     * @throws DuplicateScreenException    there is more than one of the same Model, View Or Controller
     */
    @SuppressWarnings("unchecked")
    public static ScreenCreator<?, ?, ?>[] find() throws ScreenMissingPartsException, DuplicateScreenException {
        Reflections reflections = new Reflections("");
        // name: Model, View, Controller
        Map<String, Tuple<Class<? extends Model>, Class<? extends View>, Class<? extends Controller>>> mvc = new HashMap<>();

        new AnnotationFinder(new ReflectionsServiceImpl()).find(MVC.class, (clazz) -> {
            String key = clazz.getAnnotation(MVC.class).value();
            Tuple<Class<? extends Model>, Class<? extends View>, Class<? extends Controller>> v = mvc.getOrDefault(key, new Tuple<>(null, null, null));

            if (Model.class.isAssignableFrom(clazz)) {
                if (v.first != null) {
                    throw new DuplicateScreenException("Duplicate model " + clazz);
                }
                v = new Tuple<>((Class<? extends Model>) clazz, v.second, v.third);
            } else if (View.class.isAssignableFrom(clazz)) {
                if (v.second != null) {
                    throw new DuplicateScreenException("Duplicate view " + clazz);
                }
                v = new Tuple<>(v.first, (Class<? extends View>) clazz, v.third);
            } else if (Controller.class.isAssignableFrom(clazz)) {
                if (v.third != null) {
                    throw new DuplicateScreenException("Duplicate controller " + clazz);
                }
                v = new Tuple<>(v.first, v.second, (Class<? extends Controller>) clazz);
            } else {
                throw new IllegalArgumentException("Annotated class " + clazz + " does not extend from Model, View, or Controller");
            }

            mvc.put(key, v);
        });

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
}
