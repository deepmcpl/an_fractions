package pl.animekkk.fractions.fraction.util;

import org.bukkit.Location;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.FractionManager;

public class LocationUtil {

    public static int fractionDistance(Location location) {
        double distance = 9999;
        location.setY(0);
        for(Fraction fraction : FractionManager.getFractions()) {
            double fractionDistance = fraction.getCuboid().getLocation().distanceSquared(location);
            if(fractionDistance < distance) distance = fractionDistance;
        }
        return (int) distance;
    }

    public static Fraction getCurrentFraction(Location location) {
        for(Fraction fraction : FractionManager.getFractions()) {
            if(fraction.getCuboid().isCuboid(location)) return fraction;
        }
        return null;
    }

}
