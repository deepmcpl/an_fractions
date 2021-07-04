package pl.animekkk.fractions.fraction.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.FractionManager;

import java.util.Arrays;

public class LocationUtil {
    
    public static boolean canCreateInLocation(Location location) {
        for(Fraction fraction : FractionManager.getFractions()) {
            if(isInLocation(fraction.getCuboid().getLocation(), location, 60)) return false;
        }
        return true;
    }

    private static boolean isInLocation(Location location, Location location1, int distance) {
        int distanceX = Math.abs(location.getBlockX() - location1.getBlockX());
        int distanceZ = Math.abs(location.getBlockZ() - location1.getBlockZ());
        return (distanceX <= distance) && (distanceZ <= distance);
    }

    public static Fraction getCurrentFraction(Location location) {
        for(Fraction fraction : FractionManager.getFractions()) {
            if(fraction.getCuboid().isCuboid(location)) return fraction;
        }
        return null;
    }

    public static String locationToString(Location loc)
    {
        return loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getPitch() + ":" + loc.getYaw();
    }

    public static Location locationFromString(String str) {
        String[] str2loc = str.split(":");
        Location loc = new Location(Bukkit.getWorlds().get(0), 0, 0, 0, 0F, 0F);
        loc.setX(Double.parseDouble(str2loc[0]));
        loc.setY(Double.parseDouble(str2loc[1]));
        loc.setZ(Double.parseDouble(str2loc[2]));
        if (str2loc.length == 5) {
            loc.setPitch(Float.parseFloat(str2loc[3]));
            loc.setYaw(Float.parseFloat(str2loc[4]));
        }
        return loc;
    }

}
