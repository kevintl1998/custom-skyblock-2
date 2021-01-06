package me.t0c.customskyblock2.runnables;

import me.t0c.customskyblock2.CustomSkyblock2;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class PlaceBlockLater extends BukkitRunnable {

    private final Material material;
    private final Location location;

    public PlaceBlockLater(Material material, Location location, long delay) {
        this.material = material;
        this.location = location;
        runTaskLater(CustomSkyblock2.instance(), delay);
    }

    @Override
    public void run() {
        location.getWorld().getBlockAt(location).setType(material);
    }
}
