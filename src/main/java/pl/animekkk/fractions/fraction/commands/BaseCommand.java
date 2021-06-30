package pl.animekkk.fractions.fraction.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import pl.animekkk.fractions.Fractions;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BaseCommand extends Command {

    private final Set<UUID> teleporting = new HashSet<>();

    public BaseCommand() {
        super("base", "", "/base", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtils.sendMessage(player, "&7You don't belong to any fraction.");
        if(teleporting.contains(user.getUuid())) return ChatUtils.sendMessage(player, "&7You are already trying to teleport.");
        Location location = fraction.getCuboid().getBase();
        createTeleportTask(player, location, player.getLocation(), 5);
        teleporting.add(user.getUuid());
        return ChatUtils.sendMessage(player, "&7You will be teleported in &35 seconds&7.");
    }

    public void createTeleportTask(Player player, Location location, Location firstLocation, int seconds) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(Fractions.getInstance(), () -> {
            if(!player.isOnline()) {
                teleporting.remove(player.getUniqueId());
                return;
            }
            if(seconds < 1) {
                Bukkit.getScheduler().runTask(Fractions.getInstance(), () -> {
                    ChatUtils.sendMessage(player, "&7You have been teleported to your fraction base.");
                    player.teleport(location);
                    teleporting.remove(player.getUniqueId());
                });
                return;
            }
            if(player.getLocation().getBlockX() != firstLocation.getBlockX() || player.getLocation().getBlockZ() != firstLocation.getBlockZ()) {
                ChatUtils.sendMessage(player, "&7You have moved. Teleportation canceled.");
                teleporting.remove(player.getUniqueId());
                return;
            }
            ChatUtils.sendMessage(player, "&7You will be teleported in: &3" + seconds + "s");
            createTeleportTask(player, location, firstLocation, seconds-1);
        }, 20L);
    }
}
