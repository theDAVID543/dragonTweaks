package thedavid.dragontweaks;

import org.bukkit.scheduler.BukkitRunnable;

public class task {
    public task(DragonTweaks plugin){
        this.plugin = plugin;
    }
    DragonTweaks plugin;
    public Integer resetTime = -1;

    public void dragonTimerTask(){
        resetTime = 1785;
        new BukkitRunnable(){
            @Override
            public void run() {
                if(resetTime == 0){
                    plugin.commander.resetDragonWorld();
                    resetTime = -1;
                }else if(resetTime > 0){
                    resetTime -= 1;
                }else {
                    resetTime = -1;
                    cancel();
                }
            }
        }.runTaskLater(plugin.instance, 20L);
    }
}
