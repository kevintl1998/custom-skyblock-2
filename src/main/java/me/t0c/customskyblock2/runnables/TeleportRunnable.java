package me.t0c.customskyblock2.runnables;

import me.t0c.customskyblock2.CustomSkyblock2;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportRunnable extends BukkitRunnable {
    private final Player player;
    private final Location location;


    public TeleportRunnable(Player player, Location location) {
        this.player = player;
        this.location = location;
        runTaskLater(CustomSkyblock2.instance(), 1);
    }

    public void run() {
        player.teleport(location);
    }
}
