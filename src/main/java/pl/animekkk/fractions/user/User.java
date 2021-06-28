package pl.animekkk.fractions.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import pl.animekkk.fractions.fraction.Fraction;

import java.util.UUID;

public class User {

    @Getter
    private final UUID uuid;

    @Getter
    private final String name;

    @Getter
    @Setter
    private Player player;

    @Getter
    @Setter
    private Fraction fraction;

    public User(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public boolean isOnline() {
        return this.player == null;
    }

}
