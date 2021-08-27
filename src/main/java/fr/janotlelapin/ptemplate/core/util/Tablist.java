package fr.janotlelapin.ptemplate.core.util;

import java.lang.reflect.Field;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;

public class Tablist implements Listener {
    private static PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

    private static String header;
    private static String footer;

    private static void reset() {
        if (Bukkit.getOnlinePlayers().size() == 0) return;

        try {
            Field a = packet.getClass().getDeclaredField("a");
            a.setAccessible(true);
            Field b = packet.getClass().getDeclaredField("b");
            b.setAccessible(true);

            Object header = new ChatComponentText(Tablist.header);
            Object footer = new ChatComponentText(Tablist.footer);

            a.set(packet, header);
            b.set(packet, footer);

            for (Player player : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setHeader(String header) {
        Tablist.header = header;
        reset();
    }

    public static void setFooter(String footer) {
        Tablist.footer = footer;
        reset();
    }

    public static void setAll(String header, String footer) {
        Tablist.header = header;
        Tablist.footer = footer;
        reset();
    }

    // Apply tab list to new players
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        reset();
    }
}
