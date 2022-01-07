package util;

import annotations.MVC;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnnotationFinderTest {
    @Test
    void whenAClassHasAnAnnotation_forEachShouldBeCalledForEachOne() {
        ReflectionsServiceImpl mocked = mock(ReflectionsServiceImpl.class);
        when(mocked.getTypesAnnotatedWith(MVC.class)).thenReturn(new HashSet<>(Collections.singleton(FakeAnnotatedClass.class)));

        AnnotationFinder finder = new AnnotationFinder(mocked);

        List<Class<?>> list = finder.find(MVC.class);

        assertArrayEquals(new Class<?>[]{FakeAnnotatedClass.class}, list.toArray(new Class<?>[0]));
    }

    private static class FakeAnnotatedClass {
    }
}