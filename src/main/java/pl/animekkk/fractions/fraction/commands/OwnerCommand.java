package pl.animekkk.fractions.fraction.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtils;

public class OwnerCommand extends Command {

    public OwnerCommand() {
        super("owner", "", "/owner <player>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtils.sendMessage(player, "&7You are not owner of any faction.");
        if(fraction.getOwner() != user.getUuid()) return ChatUtils.sendMessage(player, "&7You are not owner of this faction.");
        if(args.length < 1) return ChatUtils.sendMessage(player, "&7Wrong usage. (&3/owner <player>&7)");
        User newOwner = UserManager.getUserByName(args[0]);
        if(newOwner == null) return ChatUtils.sendMessage(player, "&7This player does not exist.");
        if(args.length < 2) return ChatUtils.sendMessage(player, "&7To confirm this operation type: &3/owner " + newOwner.getName() + " confirm");
        if(!args[1].equalsIgnoreCase("confirm")) return ChatUtils.sendMessage(player, "&7To confirm this operation type: &3/owner " + newOwner.getName() + " confirm");
        fraction.setOwner(newOwner.getUuid());
        if(newOwner.isOnline()) {
            ChatUtils.sendMessage(newOwner.getPlayer(), "&7You have become new owner of &3" + fraction.getName() + " &7fraction.");
        }
        return ChatUtils.sendMessage(player, "&3" + newOwner.getName() + " &7is new owner of your fraction.");
    }

}
