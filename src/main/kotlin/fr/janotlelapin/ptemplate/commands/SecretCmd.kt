package fr.janotlelapin.ptemplate.commands

import fr.janotlelapin.ptemplate.MainPlugin
import fr.janotlelapin.ptemplate.core.commands.BaseCommand
import org.bukkit.entity.Player

class SecretCmd(name: String, plugin: MainPlugin) : BaseCommand<Player>(name, plugin) {
    init {
        description = "A super secret command, so shush!"
        permission = "op"
        permissionMessage = "§cYou don't have the right to run this command !"
    }

    override fun isOp(): Boolean {
        return true
    }

    override fun run(sender: Player, args: Array<out String>): Boolean {
        sender.sendMessage("§eHi fellow server admin!");
        return true
    }
}
