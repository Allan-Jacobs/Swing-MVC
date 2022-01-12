package util;

import runner.GUI;
import runner.ScreenLifecycleManager;
import runner.ScreenRegistry;

/**
 * A Class to allow easy navigation within controllers.
 * This is passed to controllers in their init method to allow them to navigate.
 *
 * @see core.Controller
 */
public class Navigator {
    private final ScreenLifecycleManager manager;
    private final ScreenRegistry registry;

    public Navigator(ScreenLifecycleManager manager, ScreenRegistry registry) {
        this.manager = manager;
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
        manager.switchTo(registry.createFromName(to), null);
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
        manager.switchTo(registry.createFromName(to), metadata);
    }
}
