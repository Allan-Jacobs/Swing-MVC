package runner;

import core.Controller;
import core.Model;
import core.View;
import util.AnnotationFinder;
import util.Navigator;
import util.ReflectionsServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A registry of every screen for the application.
 */
public class ScreenRegistry {
    private static ScreenRegistry registry = null;
    private final AnnotationFinder finder;
    private final Map<String, ScreenCreator<?, ?, ?>> screens = new HashMap<>();

    private ScreenRegistry(AnnotationFinder annotationFinder) {
        finder = annotationFinder;
    }

    /**
     * A method to register a <code>runner.Screen</code>.
     *
     * @param screen the screen to register
     * @param <M>    the class of the model, eg <code>core.Model.class</code>
     * @param <V>    the class of the view, eg <code>core.View.class</code>
     * @param <C>    the class of the controller, eg <code>core.Controller.class</code>
     */
    public <M extends Class<? extends Model>,
            V extends Class<? extends View>,
            C extends Class<? extends Controller>> void addScreen(ScreenCreator<M, V, C> screen) {
        if (!screens.containsKey(screen.getName())) {
            screens.put(screen.getName(), screen);
        }
    }

    /**
     * create a new ScreenRegistry if it doesn't exist, or return the current one.
     *
     * @return the current ScreenRegistry
     */
    public static ScreenRegistry getInstance() {
        if (registry == null) {
            registry = new ScreenRegistry(new AnnotationFinder(new ReflectionsServiceImpl()));
        }
        return registry;
    }

    /**
     * A method to see if the ScreenRegistry contains the specified screen.
     * This method is used by navigator to check for screen existence.
     *
     * @param name the name of the screen creator
     * @return if the ScreenRegistry has a screen creator with that name
     * @see Navigator
     */
    public boolean containsScreen(String name) {
        return screens.containsKey(name);
    }

    /**
     * Call <code>Create()</code> on the screen creator with the specified name.
     *
     * @param name the name of the screen creator
     * @return the result of <code>Create()</code>
     */
    public Screen createFromName(String name) {
        return screens.get(name).create();
    }

    /**
     * Call <code>Create()</code> on the first found screen creator.
     * Not guaranteed to be the same screen creator when run multiple times.
     * This will be fixed in the future.
     *
     * @return the result of <code>Create()</code>
     * @throws NoScreensException when there are no screens in the registry
     */
    public Screen createEntryPoint() throws NoScreensException {
        Optional<Map.Entry<String, ScreenCreator<?, ?, ?>>> maybeScreen = screens.entrySet().stream().findFirst();
        if (!maybeScreen.isPresent()) {
            throw new NoScreensException("No Screens exist");
        }
        return maybeScreen.get().getValue().create();
    }

    /**
     * Adds all the screens with the <code>@MVC</code> annotation in the classpath to the registry
     */
    public void addScreensFromClassPath() {
        try {
            Arrays.stream(new ScreenFinder(finder).find()).forEach(this::addScreen);
        } catch (ScreenMissingPartsException | DuplicateScreenException e) {
            e.printStackTrace();
        }
    }
}
