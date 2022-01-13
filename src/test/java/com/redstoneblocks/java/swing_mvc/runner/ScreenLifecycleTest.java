package com.redstoneblocks.java.swing_mvc.runner;

import com.redstoneblocks.java.swing_mvc.core.Controller;
import com.redstoneblocks.java.swing_mvc.core.Model;
import com.redstoneblocks.java.swing_mvc.core.View;
import com.redstoneblocks.java.swing_mvc.util.Navigator;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ScreenLifecycleTest {
    @Test
    void whenInitCalled_allComponentsGetInitializedProperly() {

        Model model = mock(Model.class);
        View view = mock(View.class);
        Controller controller = mock(Controller.class);


        Screen fakeScreen = new Screen(model, view, controller, "TEST");

        Navigator navigator = mock(Navigator.class);

        ScreenLifecycle lifecycle = new ScreenLifecycle(fakeScreen, navigator);

        lifecycle.init("TEST META");

        verify(fakeScreen.getModel()).init(eq("TEST META"));
        verify(fakeScreen.getView()).create(eq(model));
        verify(fakeScreen.getController()).init(eq(navigator));
    }
}