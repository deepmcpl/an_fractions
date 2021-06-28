package pl.animekkk.fractions.fraction.commands;

import org.bukkit.command.CommandSender;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.user.util.ChatUtils;

public class FractionsCommand extends Command {

    public FractionsCommand() {
        super("fraction", "", "/fraction", new String[] {"fractions"}, "");
    }

    //TODO Create setbase command

    public boolean onExecute(CommandSender commandSender, String[] args) {
        return ChatUtils.sendMessage(commandSender, "&7Available commands:\n" +
                "&3/settings &8- &7Fraction settings.\n" +
                "&3/create <tag> <name> &8- &7Create fraction.\n" +
                "&3/invite <player> &8- &7Invite player to your fraction.\n" +
                "&3/kick <player> &8- &7Kick player from your fraction.\n" +
                "&3/join <tag> &8- &7Join fraction.\n" +
                "&3/base &8- &7Teleport to your fraction base.\n" +
                "&3/setbase &8- &7Set base of your fraction." +
                "&3/extend &8- &7Extend the time length of your fraction.\n" +
                "&3/owner <player> &8- &7Give owner to another player.\n" +
                "&3/ally <tag> &8- &7Make ally with other fraction.\n" +
                "&3/unally <tag> &8- &7Remove other fraction from your allies.\n" +
                "&3/leave &8- &7Leave fraction.");
    }
}
