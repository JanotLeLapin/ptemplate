package fr.janotlelapin.ptemplate.listeners;

import static org.mockito.Mockito.*;

import fr.janotlelapin.ptemplate.MainTest;
import fr.janotlelapin.ptemplate.MainPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.junit.Before;
import org.junit.Test;

public class PlayerListenerTest extends MainTest {
    MainPlugin plugin;
    PlayerListener listener;

    @Before
    public void setupListener() {
        // Mock the plugin class
        plugin = mock(MainPlugin.class);
        // Instantiate listener
        listener = new PlayerListener(plugin);
    }

    @Test
    public void onJoinTest() {
        // Mock a player
        Player p = mock(Player.class);
        when(p.getName()).thenReturn("Janot");

        // Mock a PlayerJoinEvent
        PlayerJoinEvent e = mock(PlayerJoinEvent.class);
        when(e.getPlayer()).thenReturn(p);

        listener.onJoin(e);

        // Was the custom join message displayed
        b.verify(() -> Bukkit.broadcastMessage(eq("§7Janot §8joined the server.")), times(1));
    }
}
