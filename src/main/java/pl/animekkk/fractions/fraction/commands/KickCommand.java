package pl.animekkk.fractions.fraction.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.util.TagUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

public class KickCommand extends Command {


    public KickCommand() {
        super("kick", "", "/kick <player>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7You are not owner of any faction.");
        if(!fraction.getOwner().equals(user.getUuid())) return ChatUtil.sendMessage(player, "&7You are not owner of this faction.");
        if(args.length != 1) return ChatUtil.sendMessage(player, "&7Wrong usage. (&3/kick <player>&7)");
        String name = args[0];
        if(name.equalsIgnoreCase(user.getName())) return ChatUtil.sendMessage(player, "&7You can't kick yourself.");
        User toKick = UserManager.getUserByName(name);
        if(toKick == null) return ChatUtil.sendMessage(player, "&7This player does not exists.");
        if(toKick.getFraction() == null) return ChatUtil.sendMessage(player, "&7This player does not belong to any fraction.");
        if(toKick.getFraction() != fraction) return ChatUtil.sendMessage(player, "&7This player does not belong to your fraction.");
        fraction.getMembers().remove(toKick.getUuid());
        toKick.setFraction(null);
        TagUtil.updateAll();
        if(toKick.isOnline()) ChatUtil.sendMessage(toKick.getPlayer(), "&7You have been kicked from &3" + fraction.getTag() + " &7fraction.");
        return ChatUtil.sendMessage(player, "&3" + toKick.getName() + " &7has been kicked from your fraction.");
    }
}
