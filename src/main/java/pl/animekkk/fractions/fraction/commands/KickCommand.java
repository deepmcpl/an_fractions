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
        super("wyrzuc", "", "/wyrzuc <nazwa>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7Nie należysz do żadnej frakcji.");
        if(!fraction.getOwner().equals(user.getUuid())) return ChatUtil.sendMessage(player, "&7Nie jesteś właścicielem tej frakcji.");
        if(args.length != 1) return ChatUtil.sendMessage(player, "&7Złe użycie. (&3/kick <nazwa>&7)");
        String name = args[0];
        if(name.equalsIgnoreCase(user.getName())) return ChatUtil.sendMessage(player, "&7Nie możesz tego zrobić.\n"
                + "Jeżeli chcesz usunąć frakcję wpisz &3/opusc&7.");
        User toKick = UserManager.getUserByName(name);
        if(toKick == null) return ChatUtil.sendMessage(player, "&7Ten gracz nie istnieje.");
        if(toKick.getFraction() == null) return ChatUtil.sendMessage(player, "&7Ten gracz nie należy do żadnej frakcji.");
        if(toKick.getFraction() != fraction) return ChatUtil.sendMessage(player, "&7Ten gracz nie należy do twojej frakcji.");
        fraction.getMembers().remove(toKick.getUuid());
        toKick.setFraction(null);
        TagUtil.updateAll();
        if(toKick.isOnline()) ChatUtil.sendMessage(toKick.getPlayer(), "&7Zostałeś wyrzucony z &3" + fraction.getTag() + " &7.");
        return ChatUtil.sendMessage(player, "&3" + toKick.getName() + " &7został wyrzucony z twojej frakcji.");
    }
}
