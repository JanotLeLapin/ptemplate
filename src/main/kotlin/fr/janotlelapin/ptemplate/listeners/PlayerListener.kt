package fr.janotlelapin.ptemplate.listeners

import fr.janotlelapin.ptemplate.MainPlugin
import fr.janotlelapin.ptemplate.core.util.Tablist
import org.bukkit.Bukkit
import org.bukkit.event.*
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener(plugin: MainPlugin) : Listener {
    val plugin = plugin

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val p = e.player

        e.joinMessage = null
        Bukkit.broadcastMessage("§7${p.name} §8joined the server.")
    }
}
