package runner;

import com.redstoneblocks.java.swing_mvc.runner.NoScreensException;
import com.redstoneblocks.java.swing_mvc.runner.Screen;
import com.redstoneblocks.java.swing_mvc.runner.ScreenCreator;
import com.redstoneblocks.java.swing_mvc.runner.ScreenRegistry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ScreenRegistryTest {
    @Test
    void whenRegistryHasNoScreens_shouldThrowNoScreensExceptionWhenUsingCreateEntryPoint() {
        ScreenRegistry registry = ScreenRegistry.getInstance();

        assertThrowsExactly(NoScreensException.class, () -> {
            Screen screen = registry.createEntryPoint();
        });
    }

    @Test
    void whenRegistryHasOneScreen_shouldNotThrowNoScreensExceptionWhenUsingCreateEntryPoint() {
        ScreenRegistry registry = ScreenRegistry.getInstance();

        ScreenCreator<?, ?, ?> fake = mock(ScreenCreator.class);

        registry.addScreen(fake);

        assertDoesNotThrow(() -> {
            Screen screen = registry.createEntryPoint();
        });
    }

    @Test
    void whenRegistryHasAScreen_containsScreenShouldReturnTrue() {
        ScreenRegistry registry = ScreenRegistry.getInstance();

        ScreenCreator<?, ?, ?> fake = mock(ScreenCreator.class);

        when(fake.getName()).thenReturn("TEST");

        registry.addScreen(fake);

        assertTrue(registry.containsScreen("TEST"));
    }

    @Test
    void whenRegistryDoesNotHaveAScreen_containsScreenShouldReturnFalse() {
        ScreenRegistry registry = ScreenRegistry.getInstance();

        ScreenCreator<?, ?, ?> fake = mock(ScreenCreator.class);

        when(fake.getName()).thenReturn("TEST");

        registry.addScreen(fake);

        assertFalse(registry.containsScreen("NOT TEST"));
    }

    @AfterEach
    void resetScreenRegistrySingleton() throws NoSuchFieldException, IllegalAccessException {
        Field field = ScreenRegistry.class.getDeclaredField("registry");
        field.setAccessible(true);
        field.set(ScreenRegistry.class, null);
        field.setAccessible(false);
    }
}