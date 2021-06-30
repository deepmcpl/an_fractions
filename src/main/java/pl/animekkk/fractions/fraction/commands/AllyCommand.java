package pl.animekkk.fractions.fraction.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.FractionManager;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtils;

public class AllyCommand extends Command {

    public AllyCommand() {
        super("ally", "", "/ally <tag>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtils.sendMessage(player, "&7You are not owner of any faction.");
        if(fraction.getOwner() != user.getUuid()) return ChatUtils.sendMessage(player, "&7You are not owner of this faction.");
        if(args.length != 1) return ChatUtils.sendMessage(player, "&7Wrong usage. (&3/ally <tag>&7)");
        String tag = args[0];
        Fraction ally = FractionManager.getFraction(tag);
        if(ally == null) return ChatUtils.sendMessage(player, "&7This fraction does not exist.");
        fraction.addAlly(ally.getTag());
        return ChatUtils.sendMessage(player, "&3" + ally.getTag() + " &7is now your ally.");
    }

}
