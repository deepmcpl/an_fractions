package pl.animekkk.fractions.fraction.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.FractionManager;
import pl.animekkk.fractions.fraction.util.TagUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

public class JoinCommand extends Command {

    public JoinCommand() {
        super("join", "", "/join <tag>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction != null) return ChatUtil.sendMessage(player, "&7You already belong to &3" + fraction.getTag() + " &7fraction.");
        if(args.length != 1) return ChatUtil.sendMessage(player, "&7Wrong usage. (&3/join <tag>&7)");
        Fraction newFraction = FractionManager.getFraction(args[0]);
        if(newFraction == null) return ChatUtil.sendMessage(player, "&7This fraction does not exist.");
        if(!newFraction.getInvites().contains(user.getUuid())) return ChatUtil.sendMessage(player, "&7You have not been invited to this fraction.");
        user.setFraction(newFraction);
        newFraction.removeInvite(user.getUuid());
        newFraction.getMembers().add(user.getUuid());
        TagUtil.updateAll();
        return ChatUtil.sendMessage(player, "&7You have joined &3" + newFraction.getTag() + " &7fraction.");
    }
}
