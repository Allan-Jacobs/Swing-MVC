package core;

import util.Navigator;

/**
 * A base core.Controller class for MVC.
 */
public abstract class Controller {
    protected View view;
    protected Model model;

    /**
     * Create the controller with the view and model
     * @param view the view (gui)
     * @param model the model (data)
     */
    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
    }

    /**
     * This should be used for initialization logic.
     * This includes binding event listeners, and loading in data
     * @param nav the navigator (change screens)
     */
    abstract protected void onInit(Navigator nav);

    /**
     * This should be used to clean up after your controller has run.
     */
    abstract protected void onCleanup();

    /**
     * Get the view for the runner.GUI to render it.
     * @return the controllers view
     */
    public View getView() {
        return view;
    }

    public void init(Navigator nav)
    {
        onInit(nav);
    }
    public void cleanup()
    {
        onCleanup();
    }
}
