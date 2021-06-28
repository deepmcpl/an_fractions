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

    private int maxX, minX, maxZ, minZ;

    public Cuboid(Location location, int size) {
        this.location = location;
        this.size = size;

        this.maxX = location.getBlockX() + 25;
        this.minX = location.getBlockX() - 25;
        this.maxZ = location.getBlockZ() + 25;
        this.minZ = location.getBlockZ() - 25;
    }

    public boolean isCuboid(Location location)
    {
        int locationX = location.getBlockX();
        int locationZ = location.getBlockZ();
        return locationX < this.maxX && locationX > this.minX && locationZ < this.maxZ && locationZ > this.minZ;
    }
}
