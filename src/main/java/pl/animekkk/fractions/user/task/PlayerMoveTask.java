package pl.animekkk.fractions.user.task;

import org.bukkit.Bukkit;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.setting.FractionSetting;
import pl.animekkk.fractions.fraction.util.LocationUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtils;

import java.util.*;

public class PlayerMoveTask implements Runnable {

    public static Set<UUID> seenOnCuboid = new HashSet<>();

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            User user = UserManager.getUser(player.getUniqueId());
            Fraction fraction = LocationUtil.getCurrentFraction(player.getLocation());
            if(fraction != null) {
                if(!fraction.getFractionSetting().hasSetting(FractionSetting.ENTER_CUBOID)
                        && !fraction.isMember(user.getUuid()) && user.getLastSafeLocation() != null) {
                    player.teleport(user.getLastSafeLocation());
                    ChatUtils.sendActionBar(player, "&7You can't enter this fraction cuboid. (&3" + fraction.getTag() + "&7)");
                } else {
                    ChatUtils.sendActionBar(player, "&7You are in &3" + fraction.getTag() + " &7fraction cuboid.");
                    seenOnCuboid.add(player.getUniqueId());
                }
            } else {
                user.setLastSafeLocation(player.getLocation());
                if(seenOnCuboid.contains(player.getUniqueId())){
                    ChatUtils.sendActionBar(player, "&k");
                    seenOnCuboid.remove(player.getUniqueId());
                }
            }
        });
    }

}
