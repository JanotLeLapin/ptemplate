package fr.janotlelapin.ptemplate.commands

import fr.janotlelapin.ptemplate.MainPlugin
import fr.janotlelapin.ptemplate.core.commands.BaseCommand
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class SampleCmd(name: String, plugin: MainPlugin) : BaseCommand<Player>(name, plugin) {
    init {
        description = "Sends a sample message to a specified player"
        usage = "/sample <player>"
    }

    override fun run(sender: Player, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("§cNot enough arguments! Usage: $usage")
            return false
        }

        val target = Bukkit.getPlayer(args[0])
        target?.sendMessage("§7Hi there §e${target.name}§7, you just received a sample message from §e${sender.name}!")

        return true
    }
}
