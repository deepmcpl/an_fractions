package pl.animekkk.fractions.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

public class CommandManager {

    private static final CommandMap commandMap = Bukkit.getServer().getCommandMap();

    public static void register(Command command) {
        commandMap.register(command.getName(), command);
    }

}
