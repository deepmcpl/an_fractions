package pl.animekkk.fractions.fraction;

import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.util.ChatUtils;

import java.util.Collection;
import java.util.HashMap;

public class FractionManager {

    private static final HashMap<String, Fraction> fractions = new HashMap<String, Fraction>();

    public static Fraction getFraction(String name) {
        return fractions.get(name);
    }

    public static boolean exist(String name) {
        return fractions.containsKey(name);
    }

    public static Fraction addFraction(Fraction fraction) {
        fractions.put(fraction.getTag(), fraction);
        return fraction;
    }

    public static Collection<Fraction> getFractions() {
        return fractions.values();
    }

    //TODO In ally task check is fraction exist.
    public static void deleteFraction(Fraction fraction) {
        fractions.remove(fraction.getTag());
        fraction.getMembers().forEach(uuid -> {
            User user = UserManager.getUser(uuid);
            user.setFraction(null);
            if(user.isOnline()) ChatUtils.sendMessage(user.getPlayer(), "&7Your fraction has been deleted.");
        });
    }

}
