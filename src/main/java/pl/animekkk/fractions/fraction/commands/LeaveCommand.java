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
        super("opusc", "", "/opusc", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7Nie należysz do żadnej frakcji.");
        if(fraction.getOwner().equals(user.getUuid())) {
            if(args.length < 1) return ChatUtil.sendMessage(player,
                    "&7Jesteś liderem tej frakcji. Jeżeli opuścisz tą frakcję to zostanie ona usunięta.\n" +
                            "Aby potwierdzić tą akcję, wpisz: &3/opusc potwierdz");
            else if(args[0].equalsIgnoreCase("potwierdz")) {
                FractionManager.deleteFraction(fraction);
                ChatUtil.sendMessage(player, "&7Usunąłeś swoją frakcję");
            }
        } else {
            fraction.removeMember(user.getUuid());
            user.setFraction(null);
            ChatUtil.sendMessage(player, "&7Opuściłeś &3" + fraction.getTag() + " &7.");
        }
        TagUtil.updateAll();
        return true;
    }
}
