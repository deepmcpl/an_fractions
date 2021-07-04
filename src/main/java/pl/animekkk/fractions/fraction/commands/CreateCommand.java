package pl.animekkk.fractions.fraction.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.FractionManager;
import pl.animekkk.fractions.fraction.cuboid.Cuboid;
import pl.animekkk.fractions.fraction.util.LocationUtil;
import pl.animekkk.fractions.fraction.util.TagUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

import java.util.Arrays;
import java.util.regex.Pattern;

public class CreateCommand extends Command {

    public CreateCommand() {
        super("create", "", "/create <tag> <name>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction != null) return ChatUtil.sendMessage(player, "&7You are already in &3" + fraction.getTag() + " &7fraction.");
        if(args.length < 2) return ChatUtil.sendMessage(player, "&7Wrong usage. (&3/create <tag*> <name**>&7)" +
                "\n*Tag can be 4 characters long." +
                "\n**Name can contains spaces.");
        String tag = args[0].toUpperCase();
        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        if(!isStringAlphanumeric(tag)) return ChatUtil.sendMessage(player, "&7Tag can be only alphanumeric.");
        if(!isStringAlphanumeric(name)) return ChatUtil.sendMessage(player, "&7Name can be only alphanumeric.");
        if(tag.length() > 4) return ChatUtil.sendMessage(player, "&7Tag can be 4 characters long.");
        if(FractionManager.exist(tag)) return ChatUtil.sendMessage(player, "&7This fraction already exists.");
        if(!LocationUtil.canCreateInLocation(player.getLocation())) return ChatUtil.sendMessage(player, "&7You are too close to other fraction.");
        if(!player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 12))
            return ChatUtil.sendMessage(player, "&7You have to have at least &312 diamonds &7to create fraction.");
        Fraction createdFraction =
                FractionManager.addFraction(new Fraction(player.getUniqueId(), tag, name, new Cuboid(player.getLocation(), 50, player.getLocation())));
        createdFraction.addMember(player.getUniqueId());
        createdFraction.setExpireDate(System.currentTimeMillis() + 259200000L); //3 days
        user.setFraction(createdFraction);
        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 12));
        TagUtil.updateAll();
        return ChatUtil.sendMessage(player, "&7You have created &3" + createdFraction.getTag() + "&7 - &3" + createdFraction.getName() + " &7fraction.");
    }

    public boolean isStringAlphanumeric(String s){
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        return !p.matcher(s).find();
    }

}
