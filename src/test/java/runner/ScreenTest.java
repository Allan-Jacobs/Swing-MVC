package runner;

import com.redstoneblocks.java.swing_mvc.annotations.core.Controller;
import com.redstoneblocks.java.swing_mvc.annotations.core.Model;
import com.redstoneblocks.java.swing_mvc.annotations.core.View;
import com.redstoneblocks.java.swing_mvc.annotations.runner.Screen;
import com.redstoneblocks.java.swing_mvc.annotations.util.Navigator;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ScreenTest {
    @Test
    void whenGetterIsCalled_shouldReturnValue() {
        Model model = new FakeModel();
        View view = new FakeView();
        Controller controller = new FakeController(view, model);

        Screen screen = new Screen(model, view, controller, "TEST");

        assertSame(model, screen.getModel());
        assertSame(view, screen.getView());
        assertSame(controller, screen.getController());
        assertEquals("TEST", screen.getName());
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