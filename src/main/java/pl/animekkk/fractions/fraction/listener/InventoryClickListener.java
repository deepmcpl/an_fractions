package pl.animekkk.fractions.fraction.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.setting.FractionSetting;
import pl.animekkk.fractions.fraction.setting.FractionSettings;
import pl.animekkk.fractions.fraction.util.SettingsUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

public class InventoryClickListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if(event.getView().getTitle().startsWith(ChatUtil.format("&7Settings - &3"))) {
            Player player = (Player) event.getWhoClicked();
            User user = UserManager.getUser(player.getUniqueId());
            event.setCancelled(true);
            if(user == null) {
                player.closeInventory();
                return;
            }
            Fraction fraction = user.getFraction();
            if(fraction == null) {
                player.closeInventory();
                return;
            }
            if(!fraction.getOwner().equals(player.getUniqueId())) return;
            FractionSettings settings = fraction.getFractionSetting();
            event.setCancelled(true);
            if(event.getView().getTopInventory() != event.getClickedInventory()) return;
            if(event.getSlot() == 12) settings.toggleSetting(FractionSetting.MOBS_DAMAGE);
            else if(event.getSlot() == 13) settings.toggleSetting(FractionSetting.MOBS_SPAWN);
            else if(event.getSlot() == 14) settings.toggleSetting(FractionSetting.PLAYER_DAMAGE);
            else if(event.getSlot() == 30) settings.toggleSetting(FractionSetting.EXPLOSIONS);
            else if(event.getSlot() == 31) settings.toggleSetting(FractionSetting.WATER_SPREAD);
            else if(event.getSlot() == 32) settings.toggleSetting(FractionSetting.ENTER_CUBOID);
            else return;
            player.closeInventory();
            SettingsUtil.createMenu(player, fraction);
        }
    }

}
