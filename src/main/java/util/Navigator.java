package util;

import runner.GUI;

public class Navigator {
    private final GUI gui;

    public Navigator(GUI gui) {
        this.gui = gui;
    }

    public void navigate(String to) {
        if (!gui.containsScreen(to))
            throw new NavigatorException("Could not navigate: screen \"" + to + "\" does not exist");
        gui.switchTo(to, null);
    }

    public void navigate(String to, Object metadata) {
        if (!gui.containsScreen(to))
            throw new NavigatorException("Could not navigate: screen \"" + to + "\" does not exist");
        gui.switchTo(to, metadata);
    }
}
