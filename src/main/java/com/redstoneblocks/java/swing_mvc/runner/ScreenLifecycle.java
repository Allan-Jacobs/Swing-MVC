package com.redstoneblocks.java.swing_mvc.runner;

import com.redstoneblocks.java.swing_mvc.util.Navigator;

/**
 * A class to manage a screen's lifecycle.
 * This makes sure to properly initialize it, and then properly cleanup.
 */
public class ScreenLifecycle {
    private final Screen screen;
    private final Navigator nav;

    public ScreenLifecycle(Screen screen, Navigator nav) {
        this.screen = screen;
        this.nav = nav;
    }

    /**
     * Initialize the screen with the specified metadata.
     *
     * @param metadata the metadata to be passed the next model.
     */
    void init(Object metadata) {

        // initialize model with initial state
        screen.getModel().init(metadata);

        // create view components
        screen.getView().create(screen.getModel());

        // initialize controller
        screen.getController().init(nav);
    }

    /**
     * Cleanup the controller of the screen.
     */
    void cleanup() {
        screen.getController().cleanup();
    }
}
