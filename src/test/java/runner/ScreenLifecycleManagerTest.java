package runner;

import core.Controller;
import core.Model;
import core.View;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ScreenLifecycleManagerTest {
    @Test
    void whenSwitchToIsCalled_shouldCleanupOldScreenAndInitNewOne() {

        GUI gui = mock(GUI.class);

        Model oldModel = mock(Model.class);
        View oldView = mock(View.class);
        Controller oldController = mock(Controller.class);

        Model newModel = mock(Model.class);
        View newView = mock(View.class);
        Controller newController = mock(Controller.class);

        Screen oldScreen = new Screen(oldModel, oldView, oldController, "OLD");
        Screen newScreen = new Screen(newModel, newView, newController, "NEW");

        ScreenLifecycleManager manager = new ScreenLifecycleManager(gui);

        manager.switchTo(oldScreen, null);
        manager.switchTo(newScreen, null);

        verify(oldModel).init(eq(null));
        verify(oldView).create(eq(oldModel));
        verify(oldController).init(any());
        verify(oldController).cleanup();

        verify(newModel).init(eq(null));
        verify(newView).create(eq(newModel));
        verify(newController).init(any());
        // should not be cleaned up
        verify(newController, never()).cleanup();

        manager.cleanUpLifecycles();

        // newController should be cleaned up now
        verify(newController).cleanup();

    }
}