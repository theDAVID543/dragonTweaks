package thedavid.dragontweaks;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.codehaus.plexus.util.FileUtils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class commander implements CommandExecutor {
    public commander(DragonTweaks plugin){
        this.plugin = plugin;
    }
    private DragonTweaks plugin;
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 0) {
        } else if (strings[0].equals("time")) {
            sender.sendMessage(plugin.task.resetTime.toString());
        } else if (strings[0].equals("now")) {
            if (!Objects.equals(Bukkit.getWorld("dragon"), null)) {
                resetDragonWorld();
                return true;
            }
        }
        return true;
    }
    public void resetDragonWorld(){
        if (!Objects.equals(Bukkit.getWorld("dragon"), null)) {
            for (Player p : Bukkit.getWorld("dragon").getPlayers()) {
                Bukkit.getWorld("dragon").getEnderDragonBattle().getBossBar().removeAll();
                if(p.getBedSpawnLocation() != null){
                    p.teleport(p.getBedSpawnLocation());
                }else{
                    p.teleport(Bukkit.getWorld("world").getSpawnLocation());
                }
            }
        }
        new BukkitRunnable(){
            @Override
            public void run() {

            }
        }.runTaskLater(plugin.instance, 20L);
        Bukkit.unloadWorld("dragon", false);
        try {
            FileUtils.deleteDirectory(new File("dragon"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            if (Objects.equals(Bukkit.getWorld("dragon"), null)) {
                break;
            }
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.instance, () -> {
            WorldCreator worldCreator = new WorldCreator("dragon");
            worldCreator.environment(World.Environment.THE_END);
            worldCreator.type(WorldType.NORMAL);
            worldCreator.generateStructures(true);
            worldCreator.createWorld();
            while (true) {
                if (!Objects.equals(Bukkit.getWorld("dragon"), null)) {
                    break;
                }
            }
            Bukkit.getWorld("dragon").getEnderDragonBattle().generateEndPortal(false);
            Bukkit.getServer().getWorld("dragon").setGameRule(GameRule.KEEP_INVENTORY,true);
        }, 100L); //20 Tick (1 Second) delay before run() is called
    }
}
