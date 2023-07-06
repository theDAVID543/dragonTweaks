package thedavid.dragontweaks;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class DragonTweaks extends JavaPlugin implements CommandExecutor {
    public static JavaPlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        if (Bukkit.getPluginCommand("resetend") != null) {
            Bukkit.getPluginCommand("resetend").setExecutor(new commander());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
