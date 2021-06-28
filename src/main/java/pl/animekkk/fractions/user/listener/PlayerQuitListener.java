package pl.animekkk.fractions.user.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.tasks.CuboidInfoTask;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.getUser(player.getUniqueId());
        if(user != null) {
            user.setPlayer(null);
        }
        CuboidInfoTask.seenOnCuboid.remove(player.getUniqueId());
    }

}
