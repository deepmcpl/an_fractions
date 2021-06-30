package pl.animekkk.fractions.fraction.cuboid;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

public class Cuboid {

    @Getter
    private final Location location;

    @Getter
    @Setter
    private int size;

    @Getter
    @Setter
    private Location base;

    private final int maxX, minX, maxZ, minZ;

    public Cuboid(Location location, int size, Location base) {
        this.location = location;
        this.size = size;
        this.base = base;

        int half = size / 2;

        this.maxX = location.getBlockX() + half;
        this.minX = location.getBlockX() - half;
        this.maxZ = location.getBlockZ() + half;
        this.minZ = location.getBlockZ() - half;
    }

    public Cuboid(Location location, int size) {
        this(location, size, location);
    }

    public boolean isCuboid(Location location)
    {
        int locationX = location.getBlockX();
        int locationZ = location.getBlockZ();
        return locationX < this.maxX && locationX > this.minX && locationZ < this.maxZ && locationZ > this.minZ;
    }
}
