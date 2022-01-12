package runner;

import util.Navigator;

public class ScreenLifecycleManager {
    private final GUI gui;
    private ScreenLifecycle currentScreenLifecycle = null;

    ScreenLifecycleManager(GUI gui) {
        this.gui = gui;
    }

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
}
