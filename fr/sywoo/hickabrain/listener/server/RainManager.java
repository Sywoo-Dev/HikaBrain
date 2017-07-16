package fr.sywoo.hickabrain.listener.server;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.sywoo.hickabrain.Main;

public class RainManager implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onWeatherChange(WeatherChangeEvent e) {
        final World w = e.getWorld();
        if (!w.hasStorm()) {
            e.setCancelled(true);
        }
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            public void run() {
                try {
                    if (w.hasStorm()) {
                        w.setStorm(false);
                        rainCheck();
                    }
                } catch (Exception localException) {
                }
            }
        }, 5L);
    }

    private void rainCheck() {
        for (World w : Main.getInstance().getServer().getWorlds()) {
            if (w.hasStorm()) {
                w.setStorm(false);
            }
        }
    }

}
