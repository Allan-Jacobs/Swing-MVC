package util;

import org.junit.jupiter.api.Test;
import runner.GUI;
import runner.ScreenRegistry;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NavigatorTest {

    @Test
    void whenNavigateWithScreenName_shouldCallSwitchToInGUIWithScreenNameUnlessScreenDoesNotExist() {

        GUI gui = mock(GUI.class);
        ScreenRegistry registry = mock(ScreenRegistry.class);

        when(registry.containsScreen(anyString())).thenReturn(false);
        when(registry.containsScreen("TEST")).thenReturn(true);

        Navigator nav = new Navigator(gui, registry);

        assertDoesNotThrow(() -> nav.navigate("TEST"));
        verify(gui).switchTo(eq("TEST"), eq(null));

        reset(gui);

        assertThrowsExactly(NavigatorException.class, () -> nav.navigate("NOT A SCREEN"));
        verify(gui, never()).switchTo(any(), any());
    }

    @Test
    void whenNavigatedWithMetadata_shouldBePassedToGUISwitchTo() {
        ScreenRegistry registry = mock(ScreenRegistry.class);
        GUI gui = mock(GUI.class);

        when(registry.containsScreen(anyString())).thenReturn(false);
        when(registry.containsScreen("TEST")).thenReturn(true);



        Navigator nav = new Navigator(gui, registry);

        assertDoesNotThrow(() -> nav.navigate("TEST", "Some Text"));
        verify(gui).switchTo(eq("TEST"), eq("Some Text"));
    }
}