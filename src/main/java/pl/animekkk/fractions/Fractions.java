package pl.animekkk.fractions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.animekkk.fractions.commands.CommandManager;
import pl.animekkk.fractions.fraction.commands.CreateCommand;
import pl.animekkk.fractions.fraction.commands.FractionsCommand;
import pl.animekkk.fractions.user.listener.PlayerJoinListener;
import pl.animekkk.fractions.user.listener.PlayerQuitListener;
import pl.animekkk.fractions.user.tasks.CuboidInfoTask;

public class Fractions extends JavaPlugin {

    @Override
    public void onEnable() {
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
        CommandManager.register(new FractionsCommand());
        CommandManager.register(new CreateCommand());
    }

    public void registerTasks() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new CuboidInfoTask(), 20L, 20L);
    }
}
