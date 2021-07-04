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

public class LeaveCommand extends Command {

    public LeaveCommand() {
        super("leave", "", "/leave", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7You don't belong to any fraction.");
        if(fraction.getOwner().equals(user.getUuid())) {
            if(args.length < 1) return ChatUtil.sendMessage(player,
                    "&7You are the owner of this fraction. If you will leave it then it will be deleted.\n" +
                            "To confirm this action type: &3/leave confirm");
            else if(args[0].equalsIgnoreCase("confirm")) {
                FractionManager.deleteFraction(fraction);
                ChatUtil.sendMessage(player, "&7You have deleted your fraction.");
            }
        } else {
            fraction.removeMember(user.getUuid());
            user.setFraction(null);
            ChatUtil.sendMessage(player, "&7You have left &3" + fraction.getTag() + " &7fraction.");
        }
        TagUtil.updateAll();
        return true;
    }
}
