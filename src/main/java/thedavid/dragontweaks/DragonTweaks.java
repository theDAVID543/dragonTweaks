package thedavid.dragontweaks;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class DragonTweaks extends JavaPlugin implements CommandExecutor {
    public JavaPlugin instance;
    public commander commander;
    public task task;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        commander = new commander(this);
        task = new task(this);
        if (Bukkit.getPluginCommand("resetend") != null) {
            Bukkit.getPluginCommand("resetend").setExecutor(commander);
        }
        task.dragonTimerTask();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
