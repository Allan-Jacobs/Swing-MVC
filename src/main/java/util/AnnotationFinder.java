package util;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.Consumer;


public class AnnotationFinder {
    /**
     * A generic Annotation finder
     * @param annotation the annotation class to look for
     * @param forEach the function applied to each class: (Class<?> clazz) -> void
     */
    public static <T extends Annotation> void find(Class<? extends Annotation> annotation, Consumer<Class<?>> forEach) {
        Reflections reflections = new Reflections("");

        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotation);

        for (Class<?> annotatedClass : annotatedClasses) {
            forEach.accept(annotatedClass);
        }
    }
}
