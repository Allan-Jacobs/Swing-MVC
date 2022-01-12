package runner;

import util.Navigator;

public class ScreenLifecycle {
    private final Screen screen;
    private Navigator nav;

    public ScreenLifecycle(Screen screen, Navigator nav) {
        this.screen = screen;
    }

    void init(Object metadata) {

        // initialize model with initial state
        screen.getModel().init(metadata);

        // create view components
        screen.getView().create(screen.getModel());

        // initialize controller
        screen.getController().init(nav);
    }

    void cleanup() {
        screen.getController().cleanup();
    }
}
