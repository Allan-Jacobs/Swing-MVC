package runner;

import core.Controller;
import core.Model;
import core.View;

import java.lang.reflect.InvocationTargetException;

/**
 * This class encapsulates a model, view, and controller
 *
 * @param <ModelClass>      the class of the model, eg <code>core.Model.class</code>
 * @param <ViewClass>       the class of the view, eg <code>core.View.class</code>
 * @param <ControllerClass> the class of the core.Controller, eg <code>core.Controller.class</code>
 */
public class ScreenCreator<ModelClass extends Class<? extends Model>,
        ViewClass extends Class<? extends View>,
        ControllerClass extends Class<? extends Controller>> {
    private final ModelClass modelClass;
    private final ViewClass viewClass;
    private final ControllerClass controllerClass;

    private final String name;

    public ScreenCreator(ModelClass modelClass, ViewClass viewClass, ControllerClass controllerClass, String name) {
        this.modelClass = modelClass;
        this.viewClass = viewClass;
        this.controllerClass = controllerClass;

        this.name = name;
    }

    /**
     * Create a new <code>Screen</code> via reflection wizardry.
     *
     * @return the created screen.
     */
    public Screen create() {
        try {

            Model model = modelClass.newInstance();
            View view = viewClass.newInstance();
            Controller controller = controllerClass.getDeclaredConstructor(new Class[]{View.class, Model.class}).newInstance(view, model);
            return new Screen(model, view, controller, name);

        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
