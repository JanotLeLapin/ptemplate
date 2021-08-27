package fr.janotlelapin.ptemplate.core.commands;

import fr.janotlelapin.ptemplate.MainPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.ParameterizedType;

public abstract class BaseCommand<T extends CommandSender> extends BukkitCommand {
    protected MainPlugin plugin;

    protected BaseCommand(String name, MainPlugin plugin) {
        super(name);
        this.plugin = plugin;
    }

    /** Whether the sender needs to be OP to run the command */
    public boolean isOp() {
        return false;
    }

    public abstract boolean run(T sender, String[] args);

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (CommandHandler.canRun(sender, this)) {
            Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            if (type.isInstance(sender)) return run((T) sender, args);
            else {
                sender.sendMessage("§cYou cannot run this command.");
                return false;
            }
        } else return false;
    }
}
