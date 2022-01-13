package com.redstoneblocks.java.swing_mvc.annotations.runner;

import com.redstoneblocks.java.swing_mvc.annotations.core.View;
import com.redstoneblocks.java.swing_mvc.annotations.util.Navigator;

import javax.swing.*;
import java.awt.*;

/**
 * A class to display views to the screen.
 */
public class GUI extends JFrame {

    GUI() {
        this.setSize(1650, 1000);
        this.getContentPane().setLayout(new BorderLayout());
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