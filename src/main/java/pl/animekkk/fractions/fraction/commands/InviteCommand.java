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
        super("zapros", "", "/zapros <nazwa>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7Nie należysz do żadnej frakcji.");
        if(!fraction.getOwner().equals(user.getUuid())) return ChatUtil.sendMessage(player, "&7Nie jesteś właścicielem tej frakcji.");
        if(args.length != 1) return ChatUtil.sendMessage(player, "&7Złe użycie. (&3/invite <nazwa>&7)");
        String name = args[0];
        if(name.equalsIgnoreCase(user.getName())) return ChatUtil.sendMessage(player, "&7Nie możesz dodać samego siebie.");
        User toAdd = UserManager.getUserByName(name);
        if(toAdd == null) return ChatUtil.sendMessage(player, "&7Ten gracz nie istnieje.");
        if(toAdd.getFraction() != null) return ChatUtil.sendMessage(player, "&7Ten gracz posiada już frakcje.");
        if(fraction.isInvited(toAdd.getUuid())) {
            fraction.removeInvite(toAdd.getUuid());
            return ChatUtil.sendMessage(player, "&7Zaproszenie anulowane.");
        }
        fraction.addInvite(toAdd.getUuid());
        if(toAdd.isOnline()) {
            ChatUtil.sendMessage(toAdd.getPlayer(), "&7Zostałeś zaproszony do &3" + fraction.getTag() + " &7.\n" +
                    "&7Wpisz: &3/dolacz " + fraction.getTag() + "&7, aby dołączyć.");
        }
        return ChatUtil.sendMessage(player, "&3" + toAdd.getName() + " &7został zaproszony do twojej frakcji.\n" +
                "Aby anulować te zaproszenie, wpisz: &3/zapros " + toAdd.getName());
    }
}
