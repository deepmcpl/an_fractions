package pl.animekkk.fractions.user.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.getUser(player.getUniqueId());
        if(user == null) {
            user = UserManager.addUser(new User(player.getUniqueId(), player.getName()));
        }
        user.setPlayer(player);
    }

}
