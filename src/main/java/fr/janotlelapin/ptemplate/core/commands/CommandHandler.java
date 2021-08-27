package fr.janotlelapin.ptemplate.core.commands;

import fr.janotlelapin.ptemplate.MainPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandHandler {
    private static ArrayList<BaseCommand> commands = new ArrayList<>();

    public static void init(String packageName, MainPlugin plugin) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        Set<Class<? extends BaseCommand>> commandClasses = reflections.getSubTypesOf(BaseCommand.class).stream().collect(Collectors.toSet());
        commandClasses.forEach(command -> {
            try {
                String cmdName = command.getName().toLowerCase().replace("cmd", "").replace(packageName + ".", "");
                BaseCommand cmd = command.getDeclaredConstructor(String.class, MainPlugin.class).newInstance(cmdName, plugin);
                ((CraftServer) plugin.getServer()).getCommandMap().register(cmd.getName(), cmd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static ArrayList<BaseCommand> getCommands() {
        return commands;
    }

    public static BaseCommand find(String name) {
        return commands.stream().filter(command -> command.getName().equals(name)).findAny().orElse(null);
    }

    public static boolean canRun(CommandSender sender, BaseCommand command) {
        String perm = command.getPermission();

        if (command.isOp() && !sender.isOp()) return false;
        if (perm != null && !sender.hasPermission(perm)) return false;
        return true;
    }

    public static Set<BaseCommand> filter(CommandSender sender) {
        return CommandHandler.commands.stream().filter(cmd -> canRun(sender, cmd)).collect(Collectors.toSet());
    }
}
