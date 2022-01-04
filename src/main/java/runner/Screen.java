package runner;

import core.Controller;
import core.Model;
import core.View;

/**
 * A class to warp an MVC system. After navigating, it is discarded.
 * This is created by ScreenCreator during runtime.
 *
 * @see ScreenCreator
 */
public class Screen {
    private final Model model;
    private final View view;
    private final Controller controller;
    private final String name;

    public Screen(Model model, View view, Controller controller, String name) {
        this.model = model;
        this.view = view;
        this.controller = controller;
        this.name = name;
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }

    public String getName() {
        return name;
    }
}
