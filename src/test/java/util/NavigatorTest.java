package util;

import org.junit.jupiter.api.Test;
import runner.Screen;
import runner.ScreenLifecycleManager;
import runner.ScreenRegistry;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;

class NavigatorTest {

    @Test
    void whenNavigateWithScreenName_shouldCallSwitchToInGUIWithScreenNameUnlessScreenDoesNotExist() {

        ScreenLifecycleManager manager = mock(ScreenLifecycleManager.class);

        Screen fakeScreen = mock(Screen.class);

        when(fakeScreen.getName()).thenReturn("TEST");

        ScreenRegistry registry = mock(ScreenRegistry.class);

        when(registry.containsScreen(anyString())).thenReturn(false);
        when(registry.containsScreen("TEST")).thenReturn(true);

        when(registry.createFromName("TEST")).thenReturn(fakeScreen);

        Navigator nav = new Navigator(manager, registry);

        assertDoesNotThrow(() -> nav.navigate("TEST"));
        verify(manager).switchTo(argThat(screen -> screen != null && "TEST".equals(screen.getName())), eq(null));

        reset(manager);

        assertThrowsExactly(NavigatorException.class, () -> nav.navigate("NOT A SCREEN"));
        verify(manager, never()).switchTo(any(), any());
    }

    @Test
    void whenNavigatedWithMetadata_shouldBePassedToGUISwitchTo() {

        ScreenLifecycleManager manager = mock(ScreenLifecycleManager.class);

        Screen fakeScreen = mock(Screen.class);

        when(fakeScreen.getName()).thenReturn("TEST");

        ScreenRegistry registry = mock(ScreenRegistry.class);

        when(registry.containsScreen(anyString())).thenReturn(false);
        when(registry.containsScreen("TEST")).thenReturn(true);

        when(registry.createFromName("TEST")).thenReturn(fakeScreen);

        Navigator nav = new Navigator(manager, registry);

        assertDoesNotThrow(() -> nav.navigate("TEST", "Some Text"));
        verify(manager).switchTo(argThat(screen -> "TEST".equals(screen.getName())), eq("Some Text"));
    }
}