package pl.animekkk.fractions.user.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.FractionManager;
import pl.animekkk.fractions.user.util.ChatUtils;

import java.util.*;

public class CuboidInfoTask implements Runnable {

    public static Set<UUID> seenOnCuboid = new HashSet<>();

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            Fraction fraction = getCurrentFraction(player.getLocation());
            if(fraction != null) {
                ChatUtils.sendActionBar(player, fraction.getMembers().contains(player.getUniqueId()) ?
                        "&aAlly cuboid: " + fraction.getTag() :
                        "&cEnemy cuboid: " + fraction.getTag());
                seenOnCuboid.add(player.getUniqueId());
            } else if(seenOnCuboid.contains(player.getUniqueId())){
                ChatUtils.sendActionBar(player, "&k");
                seenOnCuboid.remove(player.getUniqueId());
            }
        });
    }

    private Fraction getCurrentFraction(Location location) {
        for(Fraction fraction : FractionManager.getFractions()) {
            if(fraction.getCuboid().isCuboid(location)) return fraction;
        }
        return null;
    }

}
