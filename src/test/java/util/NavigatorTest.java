package util;

import org.junit.jupiter.api.Test;
import runner.GUI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;

class NavigatorTest {

    @Test
    void whenNavigateWithScreenName_shouldCallSwitchToInGUIWithScreenNameUnlessScreenDoesNotExist() {
        GUI gui = mock(GUI.class);

        when(gui.containsScreen(anyString())).thenReturn(false);
        when(gui.containsScreen("TEST")).thenReturn(true);

        Navigator nav = new Navigator(gui);

        assertDoesNotThrow(() -> nav.navigate("TEST"));
        verify(gui).switchTo(eq("TEST"), eq(null));

        reset(gui);

        assertThrowsExactly(NavigatorException.class, () -> nav.navigate("NOT A SCREEN"));
        verify(gui, never()).switchTo(any(), any());
    }

    @Test
    void whenNavigatedWithMetadata_shouldBePassedToGUISwitchTo() {
        GUI gui = mock(GUI.class);

        when(gui.containsScreen(anyString())).thenReturn(false);
        when(gui.containsScreen("TEST")).thenReturn(true);

        Navigator nav = new Navigator(gui);

        assertDoesNotThrow(() -> nav.navigate("TEST", "Some Text"));
        verify(gui).switchTo(eq("TEST"), eq("Some Text"));
    }
}