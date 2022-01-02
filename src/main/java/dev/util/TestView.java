package dev.util;

import annotations.MVC;
import core.Model;
import core.View;

import javax.swing.*;

@MVC("TEST")
public class TestView extends View {

    @Override
    public void create(Model model) {

    }

    @Override
    public JPanel getGui() {
        return new JPanel();
    }
}
