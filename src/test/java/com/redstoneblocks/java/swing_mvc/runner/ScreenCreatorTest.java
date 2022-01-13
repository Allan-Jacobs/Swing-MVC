package com.redstoneblocks.java.swing_mvc.runner;

import com.redstoneblocks.java.swing_mvc.core.Controller;
import com.redstoneblocks.java.swing_mvc.core.Model;
import com.redstoneblocks.java.swing_mvc.core.View;
import com.redstoneblocks.java.swing_mvc.util.Navigator;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScreenCreatorTest {
    @Test
    void whenScreenCreatorCreatesScreen_shouldNotBeSame() {
        ScreenCreator<Class<FakeModel>, Class<FakeView>, Class<FakeController>> creator = new ScreenCreator<>(FakeModel.class, FakeView.class, FakeController.class, "TEST");
        assertNotSame(creator.create(), creator.create());
    }

    @Test
    void whenScreenCreatorGetsNullValues_shouldThrowAnException() {
        ScreenCreator<Class<FakeModel>, Class<FakeView>, Class<FakeController>> creator = new ScreenCreator<>(null, null, null, "TEST");
        assertThrows(RuntimeException.class, creator::create);
    }

    static class FakeModel extends Model {

        @Override
        public void init(Object state) {

        }
    }

    static class FakeView extends View {

        @Override
        public void create(Model model) {

        }

        @Override
        public JPanel getGui() {
            return null;
        }
    }

    static class FakeController extends Controller {

        public FakeController(View view, Model model) {
            super(view, model);
        }

        @Override
        protected void onInit(Navigator nav) {

        }

        @Override
        protected void onCleanup() {

        }
    }
}