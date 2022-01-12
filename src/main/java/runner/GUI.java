package runner;

import util.Navigator;

import javax.swing.*;
import java.awt.*;

/**
 * A class to manage all MVC systems and display them to screen.
 */
public class GUI extends JFrame {
    private Screen currentScreen = null;

    private GUI() {
        this.setSize(1650, 1000);
        this.getContentPane().setLayout(new BorderLayout());
    }

    /**
     * Create the gui and start it
     */
    public static void createAndStart() {
        GUI gui = new GUI();

        ScreenRegistry.getInstance().addScreensFromClassPath();

        gui.setVisible(true);
    }

    /**
     * Switch to the specified screen, or if it doesn't exist, do nothing.
     * This method is used by navigator to change screens.
     *
     * @param name     the name of the screen to switch to
     * @param metadata metadata passed to the next screen's model
     * @see Navigator
     */
    public void switchTo(String name, Object metadata) {
        if (!ScreenRegistry.getInstance().containsScreen(name)) return;

        // first time, no cleanup needed
        if (currentScreen != null) {
            // cleanup old screen
            currentScreen.getController().cleanup();
        }

        // set new screen
        currentScreen = ScreenRegistry.getInstance().createFromName(name);

        // initialize model with initial state
        currentScreen.getModel().init(metadata);

        // create view components
        currentScreen.getView().create(currentScreen.getModel());

        // initialize controller
        currentScreen.getController().init(new Navigator(this, ScreenRegistry.getInstance()));

        // switch view
        this.getContentPane().removeAll();
        this.getContentPane().add(currentScreen.getView().getGui(), BorderLayout.CENTER);

        // tell swing that we updated layout
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
}