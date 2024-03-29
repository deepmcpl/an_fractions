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
        super("stworz", "", "/stworz <tag> <nazwa>", new String[] {"zaloz"}, "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction != null) return ChatUtil.sendMessage(player, "&7Należysz już do &3" + fraction.getTag() + " &7.");
        if(args.length < 2) return ChatUtil.sendMessage(player, "&7Złe użycie. (&3/stworz <tag*> <nazwa**>&7)" +
                "\n*Tag może zawierać maksymalnie &34 znaki&7." +
                "\n**Nazwa nie może zawierać spacji.");
        String tag = args[0].toUpperCase();
        String name = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        if(!isStringAlphanumeric(tag)) return ChatUtil.sendMessage(player, "&7Tag może zawierać tylko: &3litery i liczby&7.");
        if(!isStringAlphanumeric(name)) return ChatUtil.sendMessage(player, "&7Nazwa może zawierać tylko: &3litery i liczby&7.");
        if(tag.length() > 4) return ChatUtil.sendMessage(player, "&7Tag maksymalnie może zawierać 4 znaki.");
        if(FractionManager.isExisting(tag)) return ChatUtil.sendMessage(player, "&7Ta frakcja już istnieje.");
        if(!LocationUtil.canCreateInLocation(player.getLocation())) return ChatUtil.sendMessage(player, "&7Jesteś zbyt blisko innej frakcji.");
        if(!player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 12))
            return ChatUtil.sendMessage(player, "&7Musisz posiadać &312 diamentów&7, aby stworzyć frakcję.");
        Fraction createdFraction =
                FractionManager.addFraction(new Fraction(player.getUniqueId(), tag, name, new Cuboid(player.getLocation(), 50, player.getLocation())));
        createdFraction.addMember(player.getUniqueId());
        createdFraction.setExpireDate(System.currentTimeMillis() + 259200000L); //3 days
        user.setFraction(createdFraction);
        player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 12));
        TagUtil.updateAll();
        return ChatUtil.sendMessage(player, "&7Stworzyłeś frakcję o nazwie &3" + createdFraction.getTag() + "&7 - &3" + createdFraction.getName() + "&7.");
    }

    public boolean isStringAlphanumeric(String s){
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        return !p.matcher(s).find();
    }

}
