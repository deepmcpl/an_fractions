package pl.animekkk.fractions.fraction.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.Fractions;
import pl.animekkk.fractions.commands.Command;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BaseCommand extends Command {

    private final Set<UUID> teleporting = new HashSet<>();

    public BaseCommand() {
        super("baza", "", "/baza", new String[0], "");
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        User user = UserManager.getUser(player.getUniqueId());
        Fraction fraction = user.getFraction();
        if(fraction == null) return ChatUtil.sendMessage(player, "&7Nie należysz do żadnej frakcji.");
        if(teleporting.contains(user.getUuid())) return ChatUtil.sendMessage(player, "&7Jesteś już w trakcie teleportacji!");
        Location location = fraction.getCuboid().getBase();
        createTeleportTask(player, location, player.getLocation(), 5);
        teleporting.add(user.getUuid());
        return ChatUtil.sendMessage(player, "&7Zostaniesz przeteleportowany za &35 sekund&7.");
    }

    public void createTeleportTask(Player player, Location location, Location firstLocation, int seconds) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(Fractions.getInstance(), () -> {
            if(!player.isOnline()) {
                teleporting.remove(player.getUniqueId());
                return;
            }
            if(seconds < 1) {
                Bukkit.getScheduler().runTask(Fractions.getInstance(), () -> {
                    ChatUtil.sendMessage(player, "&7Zostałeś przeteleportowany do bazy swojej frakcji.");
                    player.teleport(location);
                    teleporting.remove(player.getUniqueId());
                });
                return;
            }
            if(player.getLocation().getBlockX() != firstLocation.getBlockX() || player.getLocation().getBlockZ() != firstLocation.getBlockZ()) {
                ChatUtil.sendMessage(player, "&7Poruszyłeś się. Teleportacja przerwana.");
                teleporting.remove(player.getUniqueId());
                return;
            }
            ChatUtil.sendMessage(player, "&7Zostaniesz przeteleportowany za: &3" + seconds + "s");
            createTeleportTask(player, location, firstLocation, seconds-1);
        }, 20L);
    }
}
