package com.redstoneblocks.java.swing_mvc.annotations.util;

import com.redstoneblocks.java.swing_mvc.annotations.MVC;
import com.redstoneblocks.java.swing_mvc.annotations.runner.ScreenFinder;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A class to find Annotations based on their runtime class.
 */
public class AnnotationFinder {
    private final ReflectionsService reflectionsService;

    /**
     * Create a AnnotationFinder
     *
     * @param service a service wrapper around reflections
     */
    public AnnotationFinder(ReflectionsService service) {
        reflectionsService = service;
    }

    /**
     * Find annotations in classpath
     * The class needs to have <code>@Retention(RetentionPolicy.RUNTIME)</code> for it to be found.
     *
     * @param annotation the annotation class to look for
     * @param <T>        the type of annotation
     * @return the list of <code>Classes</code>
     * @see ScreenFinder
     * @see MVC
     */
    public <T extends Annotation> List<Class<?>> find(Class<? extends Annotation> annotation) {

        Set<Class<?>> annotatedClasses = reflectionsService.getTypesAnnotatedWith(annotation);

        return new ArrayList<>(annotatedClasses);
    }
}
