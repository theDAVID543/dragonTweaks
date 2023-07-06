package thedavid.dragontweaks;

import net.kyori.adventure.audience.Audience;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class commander implements CommandExecutor {
    public Integer resetTime = -1;
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 0) {
            if (!Objects.equals(Bukkit.getWorld("dragon"), null)) {
                World world = Bukkit.getWorld("dragon");
                Block block = world.getHighestBlockAt(0,0);
                int y = block.getY();
                while (true){
                    if(block.getType().equals(Material.BEDROCK)){
                        break;
                    }else{
                        y -= 1;
                        block = world.getBlockAt(0,y,0);
                    }
                }
                if(block.getType().equals(Material.BEDROCK)){
                    world.setBlockData(-2,y-3,-1, Material.END_PORTAL.createBlockData());
                    world.setBlockData(-2,y-3,0, Material.END_PORTAL.createBlockData());
                    world.setBlockData(-2,y-3,1, Material.END_PORTAL.createBlockData());

                    world.setBlockData(-1,y-3,-2, Material.END_PORTAL.createBlockData());
                    world.setBlockData(-1,y-3,-1, Material.END_PORTAL.createBlockData());
                    world.setBlockData(-1,y-3,0, Material.END_PORTAL.createBlockData());
                    world.setBlockData(-1,y-3,1, Material.END_PORTAL.createBlockData());
                    world.setBlockData(-1,y-3,2, Material.END_PORTAL.createBlockData());

                    world.setBlockData(0,y-3,-2, Material.END_PORTAL.createBlockData());
                    world.setBlockData(0,y-3,-1, Material.END_PORTAL.createBlockData());
//                    world.setBlockData(0,y-3,0, Material.END_PORTAL.createBlockData());
                    world.setBlockData(0,y-3,1, Material.END_PORTAL.createBlockData());
                    world.setBlockData(0,y-3,2, Material.END_PORTAL.createBlockData());

                    world.setBlockData(1,y-3,-2, Material.END_PORTAL.createBlockData());
                    world.setBlockData(1,y-3,-1, Material.END_PORTAL.createBlockData());
                    world.setBlockData(1,y-3,0, Material.END_PORTAL.createBlockData());
                    world.setBlockData(1,y-3,1, Material.END_PORTAL.createBlockData());
                    world.setBlockData(1,y-3,2, Material.END_PORTAL.createBlockData());

                    world.setBlockData(2,y-3,-1, Material.END_PORTAL.createBlockData());
                    world.setBlockData(2,y-3,0, Material.END_PORTAL.createBlockData());
                    world.setBlockData(2,y-3,1, Material.END_PORTAL.createBlockData());

                    world.setBlockData(0,y+1,0, Material.DRAGON_EGG.createBlockData());
                }
            }
            resetTime = 1785;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(resetTime == 0){
                        if (!Objects.equals(Bukkit.getWorld("dragon"), null)) {
                            for (Player p : Bukkit.getWorld("dragon").getPlayers()) {
                                PermissionAttachment attachment = p.addAttachment(DragonTweaks.instance);
                                attachment.setPermission("myworlds.world.lastposition",true);
                                p.performCommand("mw lastposition tp world");
                                attachment.unsetPermission("myworlds.world.lastposition");
                            }
                        }
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "mw delete dragon");
                        while (true) {
                            if (Objects.equals(Bukkit.getWorld("dragon"), null)) {
                                break;
                            }
                        }
                        Bukkit.getScheduler().scheduleSyncDelayedTask(DragonTweaks.instance, () -> {
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "mw create dragon/the_end");
                            while (true) {
                                if (!Objects.equals(Bukkit.getWorld("dragon"), null)) {
                                    break;
                                }
                            }
                            Bukkit.getServer().getWorld("dragon").setGameRule(GameRule.KEEP_INVENTORY,true);
                        }, 100L); //20 Tick (1 Second) delay before run() is called
                        resetTime = -1;
                    }else if(resetTime > 0){
                        resetTime -= 1;
                    }else {
                        resetTime = -1;
                        cancel();
                    }
                }
            }.runTaskTimer(DragonTweaks.instance,0,20L);
            return true;
        } else if (strings[0].equals("time")) {
            sender.sendMessage(resetTime.toString());
        } else if (strings[0].equals("now")) {
            if (!Objects.equals(Bukkit.getWorld("dragon"), null)) {
                resetTime = 5;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (resetTime == 0) {
                            if (!Objects.equals(Bukkit.getWorld("dragon"), null)) {
                                for (Player p : Bukkit.getWorld("dragon").getPlayers()) {
                                    PermissionAttachment attachment = p.addAttachment(DragonTweaks.instance);
                                    attachment.setPermission("myworlds.world.lastposition", true);
                                    p.performCommand("mw lastposition tp world");
                                    attachment.unsetPermission("myworlds.world.lastposition");
                                }
                            }
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "mw delete dragon");
                            while (true) {
                                if (Objects.equals(Bukkit.getWorld("dragon"), null)) {
                                    break;
                                }
                            }
                            Bukkit.getScheduler().scheduleSyncDelayedTask(DragonTweaks.instance, () -> {
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "mw create dragon/the_end");
                                while (true) {
                                    if (!Objects.equals(Bukkit.getWorld("dragon"), null)) {
                                        break;
                                    }
                                }
                                Bukkit.getServer().getWorld("dragon").setGameRule(GameRule.KEEP_INVENTORY, true);
                            }, 100L); //20 Tick (1 Second) delay before run() is called
                            resetTime = -1;
                        } else if (resetTime > 0) {
                            resetTime -= 1;
                        } else {
                            resetTime = -1;
                            cancel();
                        }
                    }
                }.runTaskTimer(DragonTweaks.instance, 0, 20L);
                return true;
            }
        }
        return true;
    }
}
