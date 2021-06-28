package pl.animekkk.fractions.user.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static boolean sendMessage(Player player, String message) {
        player.sendMessage(format(message));
        return true;
    }

    public static boolean sendMessage(CommandSender commandSender, String message) {
        commandSender.sendMessage(format(message));
        return true;
    }

    public static boolean broadcastMessage(String message) {
        Bukkit.getServer().broadcastMessage(format(message));
        return true;
    }

    public static void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(message)));
    }

}
