package fr.janotlelapin.ptemplate

import fr.janotlelapin.ptemplate.core.commands.CommandHandler
import fr.janotlelapin.ptemplate.core.listeners.ArmorListener
import fr.janotlelapin.ptemplate.core.util.Tablist
import fr.janotlelapin.ptemplate.listeners.PlayerListener
import org.bukkit.craftbukkit.v1_8_R3.CraftServer
import org.bukkit.plugin.java.JavaPlugin

class MainPlugin : JavaPlugin() {
    override fun onEnable() {
        // Init custom tab list
        Tablist.setAll("§ePlugin Template", "§7A cool template")

        // Initialize command handler
        CommandHandler.init("fr.janotlelapin.ptemplate.commands", this)

        // Core event listeners
        server.pluginManager.registerEvents(ArmorListener(), this)
        server.pluginManager.registerEvents(Tablist(), this);

        server.pluginManager.registerEvents(PlayerListener(this), this)

        server.consoleSender.sendMessage("§ePlugin enabled!")
    }
}
