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

public class UnAllyCommand extends Command {

    public UnAllyCommand() {
        super("zerwijsojusz", "", "/zerwijsojusz <tag>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7Nie należysz do żadnej frakcji.");
        if(!fraction.getOwner().equals(user.getUuid())) return ChatUtil.sendMessage(player, "&7Nie jesteś właścicielem tej frakcji.");
        if(args.length != 1) return ChatUtil.sendMessage(player, "&7Złe użycie. (&3/zerwijsojusz <tag>&7)");
        String tag = args[0];
        Fraction ally = FractionManager.getFraction(tag);
        if(ally == null) return ChatUtil.sendMessage(player, "&7Ta frakcja nie istnieje.");
        if(!fraction.isAlly(ally.getTag())) return ChatUtil.sendMessage(player, "&7Ta frakcja nie jest twoim sojusznikiem.");
        fraction.removeAlly(ally.getTag());
        ally.removeAlly(fraction.getTag());
        TagUtil.updateAll();
        return ChatUtil.sendMessage(player, "&3" + ally.getTag() + " &7 już nie jest twoim sojusznikiem.");
    }

}