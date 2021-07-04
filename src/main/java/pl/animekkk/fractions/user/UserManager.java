package pl.animekkk.fractions.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class UserManager {

    private static final HashMap<UUID, User> users = new HashMap<UUID, User>();

    public static User getUser(UUID uuid) {
        return users.get(uuid);
    }

    public static boolean exist(UUID uuid) {
        return users.containsKey(uuid);
    }

    public static User addUser(User user) {
        users.put(user.getUuid(), user);
        return user;
    }

    public static User getUserByName(String name) {
        for(User user : users.values()) {
            if(user.getName().equalsIgnoreCase(name)) return user;
        }
        return null;
    }

    public static Collection<User> getUsers() {
        return users.values();
    }

}
