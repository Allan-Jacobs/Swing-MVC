package util;

import annotations.MVC;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnnotationFinderTest {
    @Test
    void whenAClassHasAnAnnotation_forEachShouldBeCalledForEachOne() {
        ReflectionsServiceImpl mocked = mock(ReflectionsServiceImpl.class);
        when(mocked.getTypesAnnotatedWith(MVC.class)).thenReturn(new HashSet<>(Collections.singleton(FakeAnnotatedClass.class)));

        AnnotationFinder finder = new AnnotationFinder(mocked);

        ArrayList<Class<?>> calls = new ArrayList<>();

        finder.find(MVC.class, calls::add);

        assertArrayEquals(new Class<?>[]{FakeAnnotatedClass.class}, calls.toArray(new Class<?>[0]));
    }

    private static class FakeAnnotatedClass {
    }
}