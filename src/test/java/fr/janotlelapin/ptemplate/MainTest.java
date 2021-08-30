package fr.janotlelapin.ptemplate;

import static org.mockito.Mockito.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.BeforeClass;
import org.mockito.MockedStatic;

import java.util.logging.Logger;

public class MainTest {
    protected static MockedStatic<Bukkit> b;

    @BeforeClass
    public static void init() {
        if (Bukkit.getServer() != null) return;

        final Logger l = Logger.getLogger("Logger");

        final Server s = mock(Server.class);
        when(s.getLogger()).thenReturn(l);
        when(s.isPrimaryThread()).thenReturn(true);
        when(s.getName()).thenReturn("TestServer");
        when(s.getVersion()).thenReturn("0.0.1");
        when(s.getBukkitVersion()).thenReturn("1.8.8");

        final ItemFactory f = mock(ItemFactory.class);

        final ItemMeta m = mock(ItemMeta.class);

        when(f.getItemMeta(any(Material.class))).thenReturn(m);

        b = mockStatic(Bukkit.class);
        b.when(() -> Bukkit.getServer()).thenReturn(s);
        b.when(() -> Bukkit.getItemFactory()).thenReturn(f);
    }
}
