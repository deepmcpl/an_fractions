package pl.animekkk.fractions.fraction.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.setting.FractionSetting;
import pl.animekkk.fractions.fraction.util.LocationUtil;

public class BlockFromToListener implements Listener {

    @EventHandler
    public void onBlock(BlockFromToEvent event) {
        Fraction placedFraction = LocationUtil.getCurrentFraction(event.getBlock().getLocation());
        if(placedFraction == null) return;
        if(event.getBlock().getType() != Material.WATER) return;
        if(placedFraction.getFractionSetting().hasSetting(FractionSetting.WATER_SPREAD)) return;
        event.setCancelled(true);
    }

}
