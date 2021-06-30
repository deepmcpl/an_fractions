package pl.animekkk.fractions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.animekkk.fractions.commands.CommandManager;
import pl.animekkk.fractions.fraction.commands.*;
import pl.animekkk.fractions.user.listener.PlayerJoinListener;
import pl.animekkk.fractions.user.listener.PlayerQuitListener;
import pl.animekkk.fractions.user.task.PlayerMoveTask;

public class Fractions extends JavaPlugin {

    private static Fractions instance;

    @Override
    public void onEnable() {
        instance = this;

        registerListeners();
        registerCommands();
        registerTasks();
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
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
        CommandManager.register(new UnAllyCommand());
    }

    public void registerTasks() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new PlayerMoveTask(), 10L, 10L);
    }

    public static Fractions getInstance() {
        return instance;
    }

}
