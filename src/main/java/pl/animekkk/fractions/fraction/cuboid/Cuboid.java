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

    public Cuboid(Location location, int size) {
        this.location = location;
        this.size = size;
    }
}
