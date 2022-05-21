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
        super("dolacz", "", "/dolacz <tag>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction != null) return ChatUtil.sendMessage(player, "&7Należysz już do &3" + fraction.getTag() + "&7.");
        if(args.length != 1) return ChatUtil.sendMessage(player, "&7Złe użycie. (&3/dolacz <tag>&7)");
        Fraction newFraction = FractionManager.getFraction(args[0]);
        if(newFraction == null) return ChatUtil.sendMessage(player, "&7Ta frakcja nie istnieje.");
        if(!newFraction.getInvites().contains(user.getUuid())) return ChatUtil.sendMessage(player, "&7Ta frakcja nie wysłała ci zaprsozenia.");
        user.setFraction(newFraction);
        newFraction.removeInvite(user.getUuid());
        newFraction.getMembers().add(user.getUuid());
        TagUtil.updateAll();
        return ChatUtil.sendMessage(player, "&7Dołączyłeś do &3" + newFraction.getTag() + " &7.");
    }
}
