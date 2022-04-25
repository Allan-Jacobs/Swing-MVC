package com.redstoneblocks.java.swing_mvc.util;

import com.redstoneblocks.java.swing_mvc.runner.GUI;

/**
 * An interface to allow for configuration of the underlying GUI, which is a subclass of JFrame
 */
public interface FrameConfiguration {
    /**
     * Run to configure the gui
     *
     * @param gui the GUI to configure
     */
    void config(GUI gui);
}
