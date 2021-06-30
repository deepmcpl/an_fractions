package pl.animekkk.fractions.fraction.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.setting.FractionSetting;
import pl.animekkk.fractions.fraction.util.LocationUtil;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        Fraction spawnFraction = LocationUtil.getCurrentFraction(event.getEntity().getLocation());
        if(spawnFraction == null) return;
        if(!spawnFraction.getFractionSetting().hasSetting(FractionSetting.EXPLOSIONS)) event.setCancelled(true);
    }

}
