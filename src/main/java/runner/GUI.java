package runner;

import core.View;
import util.Navigator;

import javax.swing.*;
import java.awt.*;

/**
 * A class to display views to the screen.
 */
public class GUI extends JFrame {

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

        ScreenLifecycleManager manager = new ScreenLifecycleManager(gui);

        try {
            Screen entryPoint = ScreenRegistry.getInstance().createEntryPoint();
            manager.switchTo(entryPoint, null);
        } catch (NoScreensException e) {
            throw new RuntimeException(e);
        }

        gui.setVisible(true);
    }

    /**
     * Switch to the specified view.
     *
     * @param view the view that we are going to switch to
     * @see Navigator
     */
    void switchView(View view) {
        // switch view
        this.getContentPane().removeAll();
        this.getContentPane().add(view.getGui(), BorderLayout.CENTER);

        // tell swing that we updated layout
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
}