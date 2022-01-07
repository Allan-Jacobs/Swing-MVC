package runner;

import annotations.MVC;
import core.Controller;
import core.Model;
import core.View;
import org.junit.jupiter.api.Test;
import util.AnnotationFinder;
import util.Navigator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScreenFinderTest {
    @Test
    void whenAComponentIsMissing_shouldThrowScreenMissingPartsException() {
        AnnotationFinder finder = mock(AnnotationFinder.class);
        when(finder.find(eq(MVC.class))).thenReturn(Arrays.asList(FakeController1.class, FakeView1.class));

        assertThrowsExactly(ScreenMissingPartsException.class, () -> {
            ScreenFinder screenFinder = new ScreenFinder(finder);
            screenFinder.find();
        });
    }

    @Test
    void whenThereAreDuplicateComponents_shouldThrowDuplicateScreenException() {
        AnnotationFinder finder = mock(AnnotationFinder.class);
        when(finder.find(eq(MVC.class)))
                .thenReturn(Arrays.asList(FakeController1.class, FakeView1.class, FakeModel1.class, FakeModel1Duplicate.class));

        assertThrowsExactly(DuplicateScreenException.class, () -> {
            ScreenFinder screenFinder = new ScreenFinder(finder);
            screenFinder.find();
        });
    }

    @Test
    void whenThereAreTheCorrectNumberOfScreenComponents_shouldReturnTheCorrectScreenCreator() {
        AnnotationFinder finder = mock(AnnotationFinder.class);
        when(finder.find(eq(MVC.class)))
                .thenReturn(Arrays.asList(FakeController1.class, FakeView1.class, FakeModel1.class));

        assertDoesNotThrow(() -> {
            ScreenFinder screenFinder = new ScreenFinder(finder);
            ScreenCreator<?, ?, ?>[] found = screenFinder.find();
            assertEquals(1, found.length, "Mismatched Lengths");
            assertNotNull(found[0]);
            assertEquals("TEST1", found[0].getName());
        });

    }

    @Test
    void whenThereAreMultipleScreens_shouldReturnMultipleScreenCreators() {
        AnnotationFinder finder = mock(AnnotationFinder.class);
        when(finder.find(eq(MVC.class)))
                .thenReturn(Arrays.asList(FakeController1.class, FakeView1.class, FakeModel1.class,
                        FakeModel2.class, FakeController2.class, FakeView2.class));

        assertDoesNotThrow(() -> {
            ScreenFinder screenFinder = new ScreenFinder(finder);
            ScreenCreator<?, ?, ?>[] found = screenFinder.find();
            assertEquals(2, found.length, "Mismatched Lengths");
            assertNotNull(found[0]);
            assertNotNull(found[1]);

            assertTrue(found[0].getName().equals("TEST1") ^ found[1].getName().equals("TEST1"), "No TEST1");
            assertTrue(found[0].getName().equals("TEST2") ^ found[1].getName().equals("TEST2"), "No TEST2");
        });
    }

    @Test
    void whenThereAreNoScreens_shouldReturnNoScreenCreators() {
        AnnotationFinder finder = mock(AnnotationFinder.class);
        when(finder.find(eq(MVC.class))).thenReturn(new ArrayList<>());

        assertDoesNotThrow(() -> {
            ScreenFinder screenFinder = new ScreenFinder(finder);
            ScreenCreator<?, ?, ?>[] found = screenFinder.find();
            assertEquals(0, found.length, "There should be no screens, but found some");
        });
    }

    // a bunch of classes for mocks
    private static class BaseFakeModel extends Model {
        @Override
        public void init(Object state) {

        }
    }

    private static class BaseFakeView extends View {

        @Override
        public void create(Model model) {

        }

        @Override
        public JPanel getGui() {
            return null;
        }
    }

    private static class BaseFakeController extends Controller {

        public BaseFakeController(View view, Model model) {
            super(view, model);
        }

        @Override
        protected void onInit(Navigator nav) {

        }

        @Override
        protected void onCleanup() {

        }
    }

    @MVC("TEST1")
    private static class FakeModel1 extends BaseFakeModel {
    }

    @MVC("TEST2")
    private static class FakeModel2 extends BaseFakeModel {
    }

    @MVC("TEST1")
    private static class FakeModel1Duplicate extends BaseFakeModel {
    }

    @MVC("TEST1")
    private static class FakeView1 extends BaseFakeView {
    }

    @MVC("TEST2")
    private static class FakeView2 extends BaseFakeView {
    }

    @MVC("TEST1")
    private static class FakeController1 extends BaseFakeController {
        public FakeController1(View view, Model model) {
            super(view, model);
        }
    }

    @MVC("TEST2")
    private static class FakeController2 extends BaseFakeController {

        public FakeController2(View view, Model model) {
            super(view, model);
        }
    }
}