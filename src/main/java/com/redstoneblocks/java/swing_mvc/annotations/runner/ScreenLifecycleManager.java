package com.redstoneblocks.java.swing_mvc.annotations.runner;

import com.redstoneblocks.java.swing_mvc.annotations.util.Navigator;

/**
 * This class manages multiple screen life cycles.
 * This makes sure to start and stop lifecycles.
 */
public class ScreenLifecycleManager {
    private final GUI gui;
    private ScreenLifecycle currentScreenLifecycle = null;

    ScreenLifecycleManager(GUI gui) {
        this.gui = gui;
    }

    /**
     * This stops the old lifecycle, and starts the new one.
     * This also updates the gui with the current lifecycle.
     *
     * @param screen   the new <code>Screen</code> to manage
     * @param metadata any data that should be passed to the screen's <code>Model</code>.
     */
    public void switchTo(Screen screen, Object metadata) {
        ScreenLifecycle createdScreenLifecycle = new ScreenLifecycle(screen, new Navigator(this, ScreenRegistry.getInstance()));
        createdScreenLifecycle.init(metadata);

        // first lifecycle, so there isn't an old one to clean up
        if (currentScreenLifecycle != null) {
            currentScreenLifecycle.cleanup();
        }

        currentScreenLifecycle = createdScreenLifecycle;
        gui.switchView(screen.getView());
    }

    /**
     * Used internally to clean up lifecycles when the program is stopped.
     */
    void cleanUpLifecycles() {
        if (currentScreenLifecycle != null) {
            currentScreenLifecycle.cleanup();
        }
    }
}
