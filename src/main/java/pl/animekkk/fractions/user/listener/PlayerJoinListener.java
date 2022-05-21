package pl.animekkk.fractions.user.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.util.DateUtil;
import pl.animekkk.fractions.fraction.util.TagUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.getUser(player.getUniqueId());
        if(user == null) {
            user = UserManager.addUser(new User(player.getUniqueId(), player.getName()));
        }
        user.setPlayer(player);
        TagUtil.createBoard(player);
        TagUtil.updateAll();
        Fraction fraction = user.getFraction();
        if(fraction == null) return;
        if(fraction.getExpireDate() - System.currentTimeMillis() < 259200000L) {
            ChatUtil.sendMessage(player, "&7Twoja frakcja niedługo wygaśnie. (&3" + DateUtil.parse(fraction.getExpireDate()) +")\n" +
                    "&7Wpisz &3/przedluz&7, aby przedłużyć ważnosc swojej frakcji na kolejne &33 dni&7.");
        }
    }

}
