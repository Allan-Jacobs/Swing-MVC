package com.redstoneblocks.java.swing_mvc.annotations.runner;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The entry point of the application
 */
public class App {
    /**
     * Create the gui and start it
     */
    public static void createAndStart() {
        GUI gui = new GUI();

        ScreenRegistry.getInstance().addScreensFromClassPath();

        ScreenLifecycleManager manager = new ScreenLifecycleManager(gui);

        try {
            Screen entryPoint = ScreenRegistry.getInstance().createEntryPoint();
            manager.switchTo(entryPoint, null);
        } catch (NoScreensException e) {
            throw new RuntimeException(e);
        }

        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                manager.cleanUpLifecycles();
            }
        });

        gui.setVisible(true);
    }
}
