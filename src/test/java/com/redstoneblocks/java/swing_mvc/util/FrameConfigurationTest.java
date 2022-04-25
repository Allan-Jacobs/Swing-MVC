package com.redstoneblocks.java.swing_mvc.util;

import com.redstoneblocks.java.swing_mvc.runner.GUI;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

class FrameConfigurationTest {
    @SuppressWarnings("MagicConstant")
    @Test
    void canConfigureGUI() {
        GUI gui = mock(GUI.class);
        doCallRealMethod().when(gui).getDefaultCloseOperation();
        doCallRealMethod().when(gui).setDefaultCloseOperation(anyInt());
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // This is set in the default constructor, but we set it here because we are not calling the real constructor

        FrameConfiguration configuration = frame -> frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        int closeOperation = gui.getDefaultCloseOperation();
        assertNotEquals(WindowConstants.DO_NOTHING_ON_CLOSE, closeOperation);

        configuration.config(gui);

        assertEquals(WindowConstants.DO_NOTHING_ON_CLOSE, gui.getDefaultCloseOperation());
    }
}