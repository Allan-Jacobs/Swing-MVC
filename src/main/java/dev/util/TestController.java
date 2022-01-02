package dev.util;

import annotations.MVC;
import core.Controller;
import core.Model;
import core.View;
import util.Navigator;

@MVC("TEST")
public class TestController extends Controller {
    /**
     * Create the controller with the view and model
     *
     * @param view  the view (gui)
     * @param model the model (data)
     */
    public TestController(View view, Model model) {
        super(view, model);
    }

    @Override
    protected void onInit(Navigator nav) {

    }

    @Override
    protected void onCleanup() {

    }
}