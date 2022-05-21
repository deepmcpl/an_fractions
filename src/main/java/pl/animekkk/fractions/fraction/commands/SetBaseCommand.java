package pl.animekkk.fractions.fraction.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

public class SetBaseCommand extends Command {

    public SetBaseCommand() {
        super("ustawbaze", "", "/ustawbaze", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7Nie należysz do żadnej frakcji.");
        if(!fraction.getOwner().equals(user.getUuid())) return ChatUtil.sendMessage(player, "&7Nie jesteś właścicielem tej frakcji.");
        Location location = player.getLocation().subtract(0, 1, 0);
        if(location.getBlock().getType() == Material.AIR) return ChatUtil.sendMessage(player, "&7Nie możesz ustawić bazy w powietrzu.");
        location.add(0, 1, 0);
        fraction.getCuboid().setBase(location);
        return ChatUtil.sendMessage(player, "&7Ustawiłeś nową bazę dla swojej frakcji.");
    }
}
