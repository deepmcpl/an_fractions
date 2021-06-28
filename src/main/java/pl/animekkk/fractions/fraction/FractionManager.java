package pl.animekkk.fractions.fraction;

import java.util.HashMap;

public class FractionManager {

    private static final HashMap<String, Fraction> fractions = new HashMap<String, Fraction>();

    public static Fraction getFraction(String name) {
        return fractions.get(name);
    }

    public static boolean exist(String name) {
        return fractions.containsKey(name);
    }

}
