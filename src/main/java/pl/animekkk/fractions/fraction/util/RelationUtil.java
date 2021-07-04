package pl.animekkk.fractions.fraction.util;

import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.relation.RelationType;
import pl.animekkk.fractions.user.User;

public class RelationUtil {

    public static RelationType getRelation(User user, User user2) {
        if(user.getFraction() != null && user2.getFraction() != null) return user.getFraction().getRelation(user2.getFraction());
        return RelationType.ENEMY;
    }

    public static RelationType getRelation(User user, Fraction fraction) {
        if(user.getFraction() != null) return user.getFraction().getRelation(fraction);
        return RelationType.ENEMY;
    }

    public static RelationType getRelation(Fraction fraction, Fraction fraction2) {
        return fraction.getRelation(fraction2);
    }

}
