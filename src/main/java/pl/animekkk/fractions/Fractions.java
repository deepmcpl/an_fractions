package pl.animekkk.fractions;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.animekkk.fractions.commands.CommandManager;
import pl.animekkk.fractions.fraction.Fraction;
import pl.animekkk.fractions.fraction.FractionManager;
import pl.animekkk.fractions.fraction.FractionsConfig;
import pl.animekkk.fractions.fraction.commands.*;
import pl.animekkk.fractions.fraction.cuboid.Cuboid;
import pl.animekkk.fractions.fraction.listener.*;
import pl.animekkk.fractions.fraction.placeholders.FractionNameExpansion;
import pl.animekkk.fractions.fraction.setting.FractionSettings;
import pl.animekkk.fractions.fraction.task.ExpireTask;
import pl.animekkk.fractions.fraction.task.SaveTask;
import pl.animekkk.fractions.user.User;
import pl.animekkk.fractions.user.UserManager;
import pl.animekkk.fractions.user.listener.PlayerJoinListener;
import pl.animekkk.fractions.user.listener.PlayerQuitListener;
import pl.animekkk.fractions.user.task.PlayerMoveTask;
import pl.mikigal.config.ConfigAPI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;

public class Fractions extends JavaPlugin {

    private static Fractions instance;
    private static FractionsConfig fractionsConfig;

    @Override
    public void onEnable() {
        instance = this;
        fractionsConfig = ConfigAPI.init(FractionsConfig.class, this);

        File file = new File(getDataFolder().getAbsolutePath());
        if(!file.exists()) file.mkdirs();

        loadFractions();
        loadUsers();

        registerListeners();
        registerCommands();
        registerTasks();
        registerExpansions();
    }

    @Override
    public void onDisable() {
        saveUsers();
        saveFractions();
    }

    public void loadUsers() {
        try {
            File usersData = new File(getDataFolder().getAbsolutePath() + File.separator + "users.json");
            if(!usersData.exists()) return;
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(String.join("", Files.readAllLines(usersData.toPath()))).getAsJsonArray();
            for(JsonElement element : array) {
                JsonObject object = element.getAsJsonObject();
                User user =
                        new User(UUID.fromString(object.get("uuid").getAsString()), object.get("name").getAsString());
                user.setFraction(FractionManager.getFraction(object.get("fraction").getAsString()));
                UserManager.addUser(user);
            }
            getLogger().log(Level.INFO, "Users loaded!");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void saveUsers() {
        try {
            Collection<User> users = UserManager.getUsers();
            if(users.size() < 1) return;
            File usersData = new File(getDataFolder().getAbsolutePath() + File.separator + "users.json");
            if(!usersData.exists()) usersData.createNewFile();
            JsonArray array = new JsonArray();
            for(User user : users) {
                JsonObject object = new JsonObject();
                object.addProperty("uuid", user.getUuid().toString());
                object.addProperty("name", user.getName());
                object.addProperty("fraction", (user.getFraction() == null ? "nulllll" : user.getFraction().getTag()));
                array.add(object);
            }
            Files.write(usersData.toPath(), Collections.singleton(array.toString()));
            getLogger().log(Level.INFO, "Users saved!");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void loadFractions() {
        try {
            File fractionsData = new File(getDataFolder().getAbsolutePath() + File.separator + "fractions.json");
            if(!fractionsData.exists()) return;
            JsonArray array = new JsonParser().parse(String.join("", Files.readAllLines(fractionsData.toPath()))).getAsJsonArray();
            for(JsonElement element : array) {
                JsonObject object = element.getAsJsonObject();
                Fraction fraction =
                        new Fraction(UUID.fromString(object.get("owner").getAsString()),
                                object.get("tag").getAsString(),
                                object.get("name").getAsString(),
                                new FractionSettings(object.get("settings").getAsString()),
                                new Cuboid(object.get("cuboid").getAsString()));
                for(String member : object.get("members").getAsString().split(";")) {
                    fraction.addMember(UUID.fromString(member));
                }
                fraction.setExpireDate(Long.parseLong(object.get("expireDate").getAsString()));
                for(String allies : object.get("allies").getAsString().split(";")) {
                    fraction.addAlly(allies);
                }
                FractionManager.addFraction(fraction);
            }
            getLogger().log(Level.INFO, "Fractions loaded!");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void saveFractions() {
        try {
            Collection<Fraction> fractions = FractionManager.getFractions();
            if(fractions.size() < 1) return;
            File fractionsData = new File(getDataFolder().getAbsolutePath() + File.separator + "fractions.json");
            if(!fractionsData.exists()) fractionsData.createNewFile();
            JsonArray array = new JsonArray();
            for(Fraction fraction : fractions) {
                JsonObject object = new JsonObject();
                object.addProperty("tag", fraction.getTag());
                object.addProperty("name", fraction.getName());
                object.addProperty("owner", fraction.getOwner().toString());
                object.addProperty("settings", fraction.getFractionSetting().toString());
                object.addProperty("cuboid", fraction.getCuboid().toString());
                String members = "";
                for(UUID uuid : fraction.getMembers()) {
                    members += ";" + uuid.toString();
                }
                object.addProperty("members", members.replaceFirst(";", ""));
                object.addProperty("expireDate", fraction.getExpireDate());
                String allies = "";
                for(String ally : fraction.getAllies()) {
                    allies += ";" + ally;
                }
                object.addProperty("allies", allies);
                array.add(object);
            }
            Files.write(fractionsData.toPath(), Collections.singleton(array.toString()));
            getLogger().log(Level.INFO, "Fractions saved!");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);

        //Listeners for settings
        if(!getFractionsConfig().getSettingsEnabled()) return;
        pluginManager.registerEvents(new InventoryCloseListener(), this);
        pluginManager.registerEvents(new BlockFromToListener(), this);
        pluginManager.registerEvents(new CreatureSpawnListener(), this);
        pluginManager.registerEvents(new EntityDamageByEntityListener(), this);
        pluginManager.registerEvents(new EntityExplodeListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new PlayerBucketEmptyListener(), this);
    }

    private void registerCommands() {
        CommandManager.register(new AllyCommand());
        CommandManager.register(new BaseCommand());
        CommandManager.register(new CreateCommand());
        CommandManager.register(new ExtendCommand());
        CommandManager.register(new FractionsCommand());
        CommandManager.register(new InviteCommand());
        CommandManager.register(new JoinCommand());
        CommandManager.register(new KickCommand());
        CommandManager.register(new LeaveCommand());
        CommandManager.register(new OwnerCommand());
        CommandManager.register(new SetBaseCommand());
        CommandManager.register(new SettingsCommand());
        CommandManager.register(new UnAllyCommand());
    }

    public void registerTasks() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new PlayerMoveTask(), 10L, 10L);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new ExpireTask(), 60 * 20L, 60 * 20L);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new SaveTask(), 300 * 20L, 300 * 20L);
    }

    private void registerExpansions() {
        new FractionNameExpansion().register();
    }

    public static Fractions getInstance() {
        return instance;
    }

    public static FractionsConfig getFractionsConfig() {
        return fractionsConfig;
    }

}
