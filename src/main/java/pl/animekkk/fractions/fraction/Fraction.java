package pl.animekkk.fractions.fraction;

import lombok.Getter;
import lombok.Setter;
import pl.animekkk.fractions.fraction.cuboid.Cuboid;
import pl.animekkk.fractions.fraction.relation.RelationType;
import pl.animekkk.fractions.fraction.setting.FractionSettings;

import java.io.Serializable;
import java.util.*;

public class Fraction implements Serializable {

    @Getter
    private final String tag;

    @Getter
    private final String name;

    @Getter
    @Setter
    private UUID owner;

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

    @Getter
    private final Set<String> alliesInvites = new HashSet<>();

    public Fraction(UUID owner, String tag, String name, FractionSettings fractionSetting, Cuboid cuboid) {
        this.owner = owner;
        this.tag = tag;
        this.name = name;
        this.fractionSetting = fractionSetting;
        this.cuboid = cuboid;
    }

    public Fraction(UUID uuid, String tag, String name, Cuboid cuboid) {
        this(uuid, tag, name, new FractionSettings(""), cuboid);
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

    public void addAllyInvite(String tag) {
        this.alliesInvites.add(tag);
    }

    public void removeAllyInvite(String tag) {
        this.alliesInvites.remove(tag);
    }

    public boolean isAllyInvited(String tag) {
        return this.alliesInvites.contains(tag);
    }

    public RelationType getRelation(Fraction fraction) {
        if(fraction.equals(this)) return RelationType.TEAM;
        else if(fraction.isAlly(this.getTag())) return RelationType.ALLY;
        else return RelationType.ENEMY;
    }

}
