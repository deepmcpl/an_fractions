package pl.animekkk.fractions.fraction.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.setting.FractionSetting;
import pl.animekkk.fractions.fraction.util.LocationUtil;

public class CreatureSpawnListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onSpawn(CreatureSpawnEvent event) {
        Fraction spawnFraction = LocationUtil.getCurrentFraction(event.getLocation());
        if(spawnFraction == null) return;
        if(!spawnFraction.getFractionSetting().hasSetting(FractionSetting.MOBS_SPAWN)) event.setCancelled(true);
    }

}
