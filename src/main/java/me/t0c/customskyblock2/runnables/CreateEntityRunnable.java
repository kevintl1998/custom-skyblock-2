package me.t0c.customskyblock2.runnables;

import me.t0c.customskyblock2.CustomSkyblock2;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

public class CreateEntityRunnable extends BukkitRunnable {
    private final EntityType entityType;
    private final Location location;
    public CreateEntityRunnable(EntityType entityType, Location location, long delay) {
        this.entityType = entityType;
        this.location = location;
        runTaskLater(CustomSkyblock2.instance(), delay);
    }

    @Override
    public void run() {
        location.getWorld().spawnEntity(location, entityType);
    }
}
