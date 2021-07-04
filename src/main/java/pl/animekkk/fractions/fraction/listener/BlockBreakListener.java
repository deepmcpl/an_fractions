package pl.animekkk.fractions.fraction.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.util.LocationUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;

public class BlockBreakListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlace(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission("fractions.cuboidbypass")) return;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction placedFraction = LocationUtil.getCurrentFraction(event.getBlock().getLocation());
        if(placedFraction == null) return;
        if(placedFraction == user.getFraction()) return;
        event.setCancelled(true);
    }

}
