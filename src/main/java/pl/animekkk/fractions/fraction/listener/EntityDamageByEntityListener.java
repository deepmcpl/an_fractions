package pl.animekkk.fractions.fraction.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.setting.FractionSetting;
import pl.animekkk.fractions.fraction.util.LocationUtil;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        Entity damager = event.getDamager();
        if(damager instanceof Projectile) {
            Projectile projectile = (Projectile) event.getDamager();
            if(projectile.getShooter() instanceof Entity)
            {
                damager = (Entity) projectile.getShooter();
            }
        }
        Fraction fraction = LocationUtil.getCurrentFraction(player.getLocation());
        if(fraction == null) return;
        if(damager instanceof Player && !fraction.getFractionSetting().hasSetting(FractionSetting.PLAYER_DAMAGE)) event.setCancelled(true);
            else if(!fraction.getFractionSetting().hasSetting(FractionSetting.MOBS_DAMAGE)) event.setCancelled(true);
    }
}
