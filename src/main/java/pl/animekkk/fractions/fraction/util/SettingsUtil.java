package pl.animekkk.fractions.fraction.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.item.ItemBuilder;
import pl.animekkk.fractions.fraction.setting.FractionSetting;
import pl.animekkk.fractions.user.util.ChatUtil;

public class SettingsUtil {

    private static final ItemStack EMPTY =
            new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setTitle(ChatUtil.format("&r")).build();

    private static final ItemStack ENABLED =
            new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setTitle(ChatUtil.format("&aEnabled")).build();

    private static final ItemStack DISABLED =
            new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setTitle(ChatUtil.format("&cDisabled")).build();

    public static void createMenu(Player player, Fraction fraction) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatUtil.format("&7Settings - &3" + fraction.getTag() +
                "&7. " + (fraction.getOwner().equals(player.getUniqueId()) ? "You can" : "You can't") + " edit it."));
        for(int i = 0; i < 54; i++) {
            inventory.setItem(i, EMPTY);
        }
        inventory.setItem(12,
                new ItemBuilder(Material.STONE_SWORD).setTitle("&7Mobs damage").addLore(getString(FractionSetting.MOBS_DAMAGE, fraction)).build());
        inventory.setItem(13,
                new ItemBuilder(Material.SKELETON_SKULL).setTitle("&7Mobs spawning").addLore(getString(FractionSetting.MOBS_SPAWN, fraction)).build());
        inventory.setItem(14,
                new ItemBuilder(Material.SHIELD).setTitle("&7Player damage").addLore(getString(FractionSetting.PLAYER_DAMAGE, fraction)).build());
        inventory.setItem(21, getProperItem(FractionSetting.MOBS_DAMAGE, fraction));
        inventory.setItem(22, getProperItem(FractionSetting.MOBS_SPAWN, fraction));
        inventory.setItem(23, getProperItem(FractionSetting.PLAYER_DAMAGE, fraction));
        //23,22,21
        inventory.setItem(30,
                new ItemBuilder(Material.TNT).setTitle("&7Explosions").addLore(getString(FractionSetting.EXPLOSIONS, fraction)).build());
        inventory.setItem(31,
                new ItemBuilder(Material.WATER_BUCKET).setTitle("&7Water spread").addLore(getString(FractionSetting.WATER_SPREAD, fraction)).build());
        inventory.setItem(32,
                new ItemBuilder(Material.NETHERITE_BOOTS).setTitle("&7Other players enter cuboid").addLore(getString(FractionSetting.ENTER_CUBOID, fraction)).build());
        inventory.setItem(39, getProperItem(FractionSetting.EXPLOSIONS, fraction));
        inventory.setItem(40, getProperItem(FractionSetting.WATER_SPREAD, fraction));
        inventory.setItem(41, getProperItem(FractionSetting.ENTER_CUBOID, fraction));
        player.openInventory(inventory);
    }

    private static String getString(FractionSetting setting, Fraction fraction) {
        String text = "&7This option is: ";
        if(fraction.getFractionSetting().hasSetting(setting)) text += "&aenabled&7.";
        else text += "&cdisabled&7.";
        return text;
    }

    public static ItemStack getProperItem(FractionSetting setting, Fraction fraction) {
        if(fraction.getFractionSetting().hasSetting(setting)) return ENABLED;
        return DISABLED;
    }
}
