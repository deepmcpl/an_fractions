package pl.animekkk.fractions.fraction;

import lombok.Getter;
import lombok.Setter;
import pl.animekkk.fractions.fraction.cuboid.Cuboid;
import pl.animekkk.fractions.fraction.settings.FractionSettings;
import pl.animekkk.fractions.user.User;

import java.util.*;

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

    @Getter
    private final List<UUID> members = new ArrayList<>();

    @Getter
    private final List<UUID> invites = new ArrayList<>();

    @Getter
    @Setter
    private long expireDate;

    @Getter
    private final Set<String> allies = new HashSet<>();

    public Fraction(UUID owner, String tag, String name, FractionSettings fractionSetting, Cuboid cuboid) {
        this.owner = owner;
        this.tag = tag;
        this.name = name;
        this.fractionSetting = fractionSetting;
        this.cuboid = cuboid;
    }

    public Fraction(UUID uuid, String tag, String name, Cuboid cuboid) {
        this(uuid, tag, name, new FractionSettings(), cuboid);
    }

    public void addMember(UUID uuid) {
        this.members.add(uuid);
    }

    public void removeMember(UUID uuid) {
        this.members.remove(uuid);
    }

    public boolean isMember(UUID uuid) {
        return this.members.contains(uuid);
    }

    public void addAlly(String tag) {
        this.allies.add(tag);
    }

    public void removeAlly(String tag) {
        this.allies.remove(tag);
    }

    public boolean isAlly(String tag) {
        return this.allies.contains(tag);
    }

    public void addInvite(UUID uuid) {
        this.invites.add(uuid);
    }

    public void removeInvite(UUID uuid) {
        this.invites.remove(uuid);
    }

    public boolean isInvited(UUID uuid) {
        return this.invites.contains(uuid);
    }

}
