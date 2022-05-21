package pl.animekkk.fractions.user.task;

import org.bukkit.Bukkit;
import pl.animekkk.fractions.Fractions;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.setting.FractionSetting;
import pl.animekkk.fractions.fraction.util.LocationUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

import java.util.*;

public class PlayerMoveTask implements Runnable {

    public static Set<UUID> seenOnCuboid = new HashSet<>();

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            User user = UserManager.getUser(player.getUniqueId());
            Fraction fraction = LocationUtil.getCurrentFraction(player.getLocation());
            if(fraction != null) {
                if((!fraction.getFractionSetting().hasSetting(FractionSetting.ENTER_CUBOID) && Fractions.getFractionsConfig().getSettingsEnabled())
                        && !fraction.isMember(user.getUuid()) && user.getLastSafeLocation() != null
                        && !player.hasPermission("fractions.cuboidbypass")) {
                    Bukkit.getScheduler().runTask(Fractions.getInstance(), () -> {
                        player.teleport(user.getLastSafeLocation());
                        ChatUtil.sendActionBar(player, "&7Wkroczyłeś na teren frakcji &3" + fraction.getTag() + "&7.");
                    });
                } else {
                    ChatUtil.sendActionBar(player, "&7Wkroczyłeś na teren frakcji &3" + fraction.getTag() + "&7." +
                            (player.hasPermission("fractions.cuboidbypass") ? " &c[ADMIN]" : ""));
                    seenOnCuboid.add(player.getUniqueId());
                }
            } else {
                user.setLastSafeLocation(player.getLocation());
                if(seenOnCuboid.contains(player.getUniqueId())){
                    ChatUtil.sendActionBar(player, "&k");
                    seenOnCuboid.remove(player.getUniqueId());
                }
            }
        });
    }

}
