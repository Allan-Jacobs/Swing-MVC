package com.redstoneblocks.java.swing_mvc.runner;

import com.redstoneblocks.java.swing_mvc.util.FrameConfiguration;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The entry point of the application
 */
public class App
{
/**
 * Create the gui and start it with the specified config.
 *
 * @param config the configuration to apply
 */
public static void createAndStart(FrameConfiguration config)
{
    GUI gui = new GUI();

    if (config != null)
        config.config(gui);

    ScreenRegistry.getInstance().addScreensFromClassPath();

    ScreenLifecycleManager manager = new ScreenLifecycleManager(gui);

    try
    {
        Screen entryPoint = ScreenRegistry.getInstance().createEntryPoint();
        manager.switchTo(entryPoint, null);
    }
    catch (NoScreensException e)
    {
        throw new RuntimeException(e);
    }

    gui.addWindowListener(new WindowAdapter()
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            manager.cleanUpLifecycles();
        }
    });

    gui.setVisible(true);
}

    /**
     * Create the gui and start it with default config
     */
    public static void createAndStart() {
        createAndStart(null);
    }
}
