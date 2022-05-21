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
        super("przedluz", "", "/przedluz", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7Nie należysz do żadnej frakcji.");
        if(!fraction.getOwner().equals(user.getUuid())) return ChatUtil.sendMessage(player, "&7Nie jesteś właścicielem tej frakcji.");
        if(!player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 12))
            return ChatUtil.sendMessage(player, "&7Musisz mieć przynajmniej &312 diamentów&7, aby przedłużyć ważność swojej frakcji.");
        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 12));
        fraction.setExpireDate(fraction.getExpireDate() + 259200000L); //3 Days
        return ChatUtil.sendMessage(player, "&7Przedłużyłeś ważność swojej frakcji na kolejne &33 dni&7.");
    }
}
