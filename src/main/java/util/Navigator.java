package util;

import runner.GUI;
import runner.ScreenRegistry;

/**
 * A Class to allow easy navigation within controllers.
 * This is passed to controllers in their init method to allow them to navigate.
 *
 * @see core.Controller
 */
public class Navigator {
    private final GUI gui;
    private final ScreenRegistry registry;

    public Navigator(GUI gui, ScreenRegistry registry) {
        this.gui = gui;
        this.registry = registry;
    }

    /**
     * Navigate to a page with no metadata (null)
     *
     * @param to The name of the screen, e.g. <code>"NAME"</code> for a screen made with <code>@MVC("NAME")</code>
     * @see annotations.MVC
     */
    public void navigate(String to) {
        if (!registry.containsScreen(to))
            throw new NavigatorException("Could not navigate: screen \"" + to + "\" does not exist");
        gui.switchTo(to, null);
    }

    /**
     * Navigate to a page with the provided metadata.
     *
     * @param to       The name of the screen, e.g. "NAME" for a screen made with @MVC("NAME")
     * @param metadata the specified metadata as an object. The resulting controller may interpret
     *                 it however it likes.
     * @see annotations.MVC
     * @see GUI
     */
    public void navigate(String to, Object metadata) {
        if (!registry.containsScreen(to))
            throw new NavigatorException("Could not navigate: screen \"" + to + "\" does not exist");
        gui.switchTo(to, metadata);
    }
}
