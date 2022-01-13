package com.redstoneblocks.java.swing_mvc.runner;

import com.redstoneblocks.java.swing_mvc.EntryPoint;
import com.redstoneblocks.java.swing_mvc.MVC;
import com.redstoneblocks.java.swing_mvc.core.Controller;
import com.redstoneblocks.java.swing_mvc.core.Model;
import com.redstoneblocks.java.swing_mvc.core.View;
import com.redstoneblocks.java.swing_mvc.util.AnnotationFinder;
import com.redstoneblocks.java.swing_mvc.util.Tuple2;
import com.redstoneblocks.java.swing_mvc.util.Tuple4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to find screens annotated with @MVC
 *
 * @see GUI
 */
public class ScreenFinder {
    private final AnnotationFinder finder;

    public ScreenFinder(AnnotationFinder annotationFinder) {
        finder = annotationFinder;
    }


    /**
     * Finds Models, Views, and Controllers annotated with @MVC, and returns them as screen creators.
     *
     * @return the array of screen creators
     * @throws ScreenMissingPartsException the screen is missing a Model, View, or Controller
     * @throws DuplicateScreenException    there is more than one of the same Model, View Or Controller
     */
    @SuppressWarnings("unchecked")
    public Tuple2<ScreenCreator<?, ?, ?>, Boolean>[] find() throws ScreenMissingPartsException, DuplicateScreenException {
        // name: Model, View, Controller
        Map<String, Tuple4<Class<? extends Model>, Class<? extends View>, Class<? extends Controller>, Boolean>> mvc = new HashMap<>();

        finder.find(MVC.class).forEach((clazz) -> {
            String key = clazz.getAnnotation(MVC.class).value();
            Tuple4<Class<? extends Model>, Class<? extends View>, Class<? extends Controller>, Boolean> v = mvc.getOrDefault(key, new Tuple4<>(null, null, null, false));

            if (Model.class.isAssignableFrom(clazz)) {
                if (v.first != null) {
                    throw new DuplicateScreenException("Duplicate model " + clazz);
                }
                v = new Tuple4<>((Class<? extends Model>) clazz, v.second, v.third, v.fourth || null != clazz.getAnnotation(EntryPoint.class));
            } else if (View.class.isAssignableFrom(clazz)) {
                if (v.second != null) {
                    throw new DuplicateScreenException("Duplicate view " + clazz);
                }
                v = new Tuple4<>(v.first, (Class<? extends View>) clazz, v.third, v.fourth || null != clazz.getAnnotation(EntryPoint.class));
            } else if (Controller.class.isAssignableFrom(clazz)) {
                if (v.third != null) {
                    throw new DuplicateScreenException("Duplicate controller " + clazz);
                }
                v = new Tuple4<>(v.first, v.second, (Class<? extends Controller>) clazz, v.fourth || null != clazz.getAnnotation(EntryPoint.class));
            } else {
                throw new IllegalArgumentException("Annotated class " + clazz + " does not extend from Model, View, or Controller");
            }

            mvc.put(key, v);
        });

        ArrayList<Tuple2<ScreenCreator<?, ?, ?>, Boolean>> creators = new ArrayList<>();

        for (Map.Entry<String, Tuple4<Class<? extends Model>, Class<? extends View>, Class<? extends Controller>, Boolean>> entry : mvc.entrySet()) {
            if (entry.getValue().first == null)
                throw new ScreenMissingPartsException("Screen " + entry.getKey() + " is missing a model.");
            if (entry.getValue().second == null)
                throw new ScreenMissingPartsException("Screen " + entry.getKey() + " is missing a view.");
            if (entry.getValue().third == null)
                throw new ScreenMissingPartsException("Screen " + entry.getKey() + " is missing a controller.");

            creators.add(new Tuple2<>(new ScreenCreator<>(entry.getValue().first, entry.getValue().second, entry.getValue().third, entry.getKey()), entry.getValue().fourth));
        }

        return creators.toArray(new Tuple2[0]);
    }
}
