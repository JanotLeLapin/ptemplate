package fr.janotlelapin.ptemplate.listeners

import fr.janotlelapin.ptemplate.MainPlugin
import org.bukkit.Bukkit
import org.bukkit.event.*
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener(plugin: MainPlugin) : Listener {
    val plugin = plugin

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val p = e.player

        Util.sendTitle(p, "§9Hello §b${p.name}§9!", "§9Nice subtitle")

        e.joinMessage = null
        Bukkit.broadcastMessage("§7${p.name} §8joined the server.")
    }
}
