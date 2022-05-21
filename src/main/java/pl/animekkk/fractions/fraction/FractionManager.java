package pl.animekkk.fractions.fraction;

import org.bukkit.Bukkit;
import pl.animekkk.fractions.fraction.util.TagUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtil;

import java.util.Collection;
import java.util.HashMap;

public class FractionManager {

    private static final HashMap<String, Fraction> fractions = new HashMap<String, Fraction>();

    public static Fraction getFraction(String name) {
        return fractions.get(name);
    }

    public static boolean isExisting(String name) {
        return fractions.containsKey(name);
    }

    public static Fraction addFraction(Fraction fraction) {
        fractions.put(fraction.getTag(), fraction);
        Bukkit.getOnlinePlayers().forEach(TagUtil::updateBoard);
        return fraction;
    }

    public static Collection<Fraction> getFractions() {
        return fractions.values();
    }

    public static void deleteFraction(Fraction fraction) {
        fractions.remove(fraction.getTag());
        fraction.getMembers().forEach(uuid -> {
            User user = UserManager.getUser(uuid);
            user.setFraction(null);
            if(user.isOnline()) ChatUtil.sendMessage(user.getPlayer(), "&7Twoja frakcja została usunięta.");
        });
        getFractions().forEach(otherFraction -> {
            if(otherFraction.isAlly(fraction.getTag())) otherFraction.removeAlly(fraction.getTag());
        });
        Bukkit.getOnlinePlayers().forEach(TagUtil::updateBoard);
    }

}
