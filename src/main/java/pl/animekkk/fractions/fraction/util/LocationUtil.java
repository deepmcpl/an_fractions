package pl.animekkk.fractions.fraction.util;

import org.bukkit.Location;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.FractionManager;

public class LocationUtil {

    public static int fractionDistance(Location location) {
        double distance = 9999;
        //TODO When create fraction set fraction location to 0 because of this
        location.setY(0);
        for(Fraction fraction : FractionManager.getFractions()) {
            double fractionDistance = fraction.getCuboid().getLocation().distanceSquared(location);
            if(fractionDistance < distance) distance = fractionDistance;
        }
        return (int) distance;
    }

}
