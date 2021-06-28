package pl.animekkk.fractions.fraction;

import lombok.Getter;
import pl.animekkk.fractions.fraction.cuboid.Cuboid;
import pl.animekkk.fractions.fraction.settings.FractionSettings;

import java.util.UUID;

public class Fraction {

    @Getter
    private final String tag;

    @Getter
    private final String name;

    @Getter
    private final UUID owner;

    @Getter
    private final FractionSettings fractionSetting;

    @Getter
    private final Cuboid cuboid;

    public Fraction(UUID owner, String tag, String name, FractionSettings fractionSetting, Cuboid cuboid) {
        this.tag = tag;
        this.name = name;
        this.owner = owner;
        this.fractionSetting = fractionSetting;
        this.cuboid = cuboid;
    }

    public Fraction(UUID uuid, String tag, String name, Cuboid cuboid) {
        this(uuid, tag, name, new FractionSettings(), cuboid);
    }

}
