package pl.animekkk.fractions.fraction.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

public class OwnerCommand extends Command {

    public OwnerCommand() {
        super("lider", "", "/lider <nick>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7Nie należysz do żadnej frakcji.");
        if(!fraction.getOwner().equals(user.getUuid())) return ChatUtil.sendMessage(player, "&7Nie jesteś właścicielem tej frakcji.");
        if(args.length < 1) return ChatUtil.sendMessage(player, "&7Złe użycie. (&3/lider <nick>&7)");
        User newOwner = UserManager.getUserByName(args[0]);
        if(newOwner == null) return ChatUtil.sendMessage(player, "&7Ten gracz nie istnieje.");
        if(args.length < 2) return ChatUtil.sendMessage(player, "&7Aby to potwierdzić, wpisz: &3/lider " + newOwner.getName() + " potwierdz");
        if(!args[1].equalsIgnoreCase("potwierdz")) return ChatUtil.sendMessage(player, "&7Aby to potwierdzić, wpisz: &3/lider " + newOwner.getName() + " potwierdz");
        fraction.setOwner(newOwner.getUuid());
        if(newOwner.isOnline()) {
            ChatUtil.sendMessage(newOwner.getPlayer(), "&7Jesteś nowym liderem &3" + fraction.getName() + " &7.");
        }
        return ChatUtil.sendMessage(player, "&3" + newOwner.getName() + " &7został nowym liderem twojej frakcji.");
    }

}
