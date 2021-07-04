package pl.animekkk.fractions.fraction.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

public class ExtendCommand extends Command {

    public ExtendCommand() {
        super("extend", "", "/extend", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7You are not owner of any faction.");
        if(!fraction.getOwner().equals(user.getUuid())) return ChatUtil.sendMessage(player, "&7You are not owner of this faction.");
        if(!player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 12))
            return ChatUtil.sendMessage(player, "&7You have to have at least &312 diamonds &7to extend time of your fraction.");
        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 12));
        fraction.setExpireDate(fraction.getExpireDate() + 259200000L); //3 Days
        return ChatUtil.sendMessage(player, "&7You have extended time of your fraction for another &33 days&7.");
    }
}
