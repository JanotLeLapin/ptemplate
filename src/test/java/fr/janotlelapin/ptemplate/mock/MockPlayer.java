package fr.janotlelapin.ptemplate.mock;

import static org.mockito.Mockito.*;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.util.UUID;

public class MockPlayer {
    public static Player mockPlayer(String name) {
        final UUID id = UUID.randomUUID();

        final PlayerInventory i = mock(PlayerInventory.class);

        final Player p = mock(Player.class);
        when(p.getName()).thenReturn(name);
        when(p.getUniqueId()).thenReturn(id);
        when(p.getInventory()).thenReturn(i);
        when(p.getPlayer()).thenReturn(p);

        return p;
    }
}
