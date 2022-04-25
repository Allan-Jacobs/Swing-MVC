package com.redstoneblocks.java.swing_mvc.util;

import com.redstoneblocks.java.swing_mvc.runner.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FrameConfigurationTest {
    GUI gui;

    @BeforeEach
    void setupGUI() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Constructor<GUI> constructor = GUI.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        gui = constructor.newInstance();
    }

    @Test
    void canConfigureGUI() {
        FrameConfiguration configuration = frame -> frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        int closeOperation = gui.getDefaultCloseOperation();
        assertNotEquals(WindowConstants.DO_NOTHING_ON_CLOSE, closeOperation);

        configuration.config(gui);

        assertEquals(WindowConstants.DO_NOTHING_ON_CLOSE, gui.getDefaultCloseOperation());
    }
}