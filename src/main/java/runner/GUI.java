package runner;

import annotations.MVC;
import core.Model;
import core.View;
import core.Controller;
import org.reflections.Reflections;
import util.Navigator;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class GUI extends JFrame
{
    private final Map<String, ScreenCreator<?, ?, ?>> screens = new HashMap<>();
    private Screen currentScreen = null;

    private GUI()
    {
        this.setSize(1650, 1000);
        this.getContentPane().setLayout(new BorderLayout());
    }

    /**
     * A method to register a <code>runner.Screen</code> with this gui.
     * @param screen the screen to register
     * @param <M> the class of the model, eg <code>core.Model.class</code>
     * @param <V> the class of the view, eg <code>core.View.class</code>
     * @param <C> the class of the controller, eg <code>core.Controller.class</code>
     */
    public <M extends Class<? extends Model>,
            V extends Class<? extends View>,
            C extends Class<? extends Controller>> void addScreen(ScreenCreator<M, V, C> screen)
    {
        if(!screens.containsKey(screen.getName()))
        {
            screens.put(screen.getName(), screen);
        }
        if(currentScreen == null) // if it's the first screen, switch to it
        {
            switchTo(screen.getName(), null);
        }
    }

    /**
     * Switch to the specified screen, or if it doesn't exist, do nothing
     * @param name the name of the screen to switch to
     */
    public void switchTo(String name, Object metadata)
    {
        if(!screens.containsKey(name)) return;

        // first time, no cleanup needed
        if(currentScreen != null)
        {
            // cleanup old screen
            currentScreen.getController().cleanup();
        }

        // set new screen
        currentScreen = screens.get(name).create();

        // initialize model with initial state
        currentScreen.getModel().init(metadata);

        // create view components
        currentScreen.getView().create(currentScreen.getModel());

        // initialize controller
        currentScreen.getController().init(new Navigator(this));

        // switch view
        this.getContentPane().removeAll();
        this.getContentPane().add(currentScreen.getView().getGui(), BorderLayout.CENTER);

        // tell swing that we updated layout
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * A method to see if the runner.GUI contains the specified screen
     * @param name the name of the screen creator
     * @return if the gui has a screen creator with that name
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean containsScreen(String name)
    {
        return screens.containsKey(name);
    }

    /**
     * Create the gui and start it
     */
    public static void createAndStart()
    {
        //GUI gui = new GUI();

        Reflections reflections = new Reflections("com.redstoneblocks.java");

        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(MVC.class);

        for(Class<?> annotatedClass: annotatedClasses) {
            System.out.println(annotatedClass);
        }

        //gui.setVisible(true);
    }

}