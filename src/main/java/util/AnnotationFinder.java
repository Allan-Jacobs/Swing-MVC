package util;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.Consumer;

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
     * @param forEach    the function applied to each class: (Class<?> clazz) -> void
     * @see runner.ScreenFinder
     * @see annotations.MVC
     */
    public <T extends Annotation> void find(Class<? extends Annotation> annotation, Consumer<Class<?>> forEach) {

        Set<Class<?>> annotatedClasses = reflectionsService.getTypesAnnotatedWith(annotation);

        for (Class<?> annotatedClass : annotatedClasses) {
            forEach.accept(annotatedClass);
        }
    }
}
