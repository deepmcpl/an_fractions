package pl.animekkk.fractions.fraction.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.FractionManager;
import pl.animekkk.fractions.fraction.cuboid.Cuboid;
import pl.animekkk.fractions.fraction.util.LocationUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtils;

import java.util.Arrays;

public class CreateCommand extends Command {

    public CreateCommand() {
        super("create", "", "/create <tag> <name>", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction != null) return ChatUtils.sendMessage(player, "&7You are already in &3" + fraction.getTag() + " &7fraction.");
        if(args.length < 2) return ChatUtils.sendMessage(player, "&7Wrong usage. (&3/create <tag*> <name**>&7)" +
                "\n*Tag can be 4 characters long." +
                "\n**Name can contains spaces.");
        String tag = args[0];
        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        if(tag.length() > 4) return ChatUtils.sendMessage(player, "&7Tag can be 4 characters long.");
        Location location = player.getLocation();
        location.setY(0);
        int distance = LocationUtil.fractionDistance(player.getLocation());
        if(distance <= 80) return ChatUtils.sendMessage(player, "&7You are too close to other fraction. &3(" + (80 - distance) + " blocks)");
        //TODO Check items
        Fraction createdFraction = FractionManager.addFraction(new Fraction(player.getUniqueId(), tag.toUpperCase(), name, new Cuboid(location, 50)));
        createdFraction.addMember(player.getUniqueId());
        user.setFraction(createdFraction);
        return ChatUtils.sendMessage(player, "&7You have created &3" + createdFraction.getTag() + "&7 - &3" + createdFraction.getName() + " &7fraction.");
    }
}
