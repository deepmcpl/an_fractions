package pl.animekkk.fractions.user.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.util.DateUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtils;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.getUser(player.getUniqueId());
        if(user == null) {
            user = UserManager.addUser(new User(player.getUniqueId(), player.getName()));
        }
        user.setPlayer(player);
        Fraction fraction = user.getFraction();
        if(fraction == null) return;
        if(fraction.getExpireDate() - System.currentTimeMillis() < 259200000L) {
            ChatUtils.sendMessage(player, "&7Your fraction is going to expire. (&3" + DateUtil.parse(fraction.getExpireDate()) +")\n" +
                    "&7Type: /extend");
        }
    }

}
