package pl.animekkk.fractions.fraction.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

public class InviteCommand extends Command {


    public InviteCommand() {
        super("invite", "", "/invite <player>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7You are not owner of any faction.");
        if(!fraction.getOwner().equals(user.getUuid())) return ChatUtil.sendMessage(player, "&7You are not owner of this faction.");
        if(args.length != 1) return ChatUtil.sendMessage(player, "&7Wrong usage. (&3/invite <player>&7)");
        String name = args[0];
        if(name.equalsIgnoreCase(user.getName())) return ChatUtil.sendMessage(player, "&7You can't add yourself.");
        User toAdd = UserManager.getUserByName(name);
        if(toAdd == null) return ChatUtil.sendMessage(player, "&7This player does not exist.");
        if(toAdd.getFraction() != null) return ChatUtil.sendMessage(player, "&7This player already have fraction.");
        if(fraction.isInvited(toAdd.getUuid())) {
            fraction.removeInvite(toAdd.getUuid());
            return ChatUtil.sendMessage(player, "&7This invite has been canceled.");
        }
        fraction.addInvite(toAdd.getUuid());
        if(toAdd.isOnline()) {
            ChatUtil.sendMessage(toAdd.getPlayer(), "&7You have been invited to &3" + fraction.getTag() + " &7fraction.\n" +
                    "&7To join type: &3/join " + fraction.getTag() + "&7.");
        }
        return ChatUtil.sendMessage(player, "&3" + toAdd.getName() + " &7has been invited to your faction.\n" +
                "To cancel this invite type: &3/invite " + toAdd.getName());
    }
}
