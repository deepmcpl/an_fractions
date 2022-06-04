package pl.animekkk.fractions.fraction.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.animekkk.fractions.Fractions;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.util.RelationUtil;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;

public class FractionNameExpansion extends PlaceholderExpansion implements Relational {

    @Override
    public @NotNull String getIdentifier() {
        return "fraction";
    }

    @Override
    public @NotNull String getAuthor() {
        return "animekkk";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player one, Player two, String identifier) {
        if(identifier.equals("name")) {
            if(one == null || two == null) return "";
            User oneUser = UserManager.getUser(one.getUniqueId());
            User twoUser = UserManager.getUser(two.getUniqueId());
            Fraction fraction = oneUser.getFraction();
            if(fraction == null) return "";
            return RelationUtil.getRelation(oneUser, twoUser).getColor() + fraction.getTag() + " ";
        }
        return "";
    }
}
