package me.t0c.customskyblock2.miscgamemodifiers;

import me.t0c.customskyblock2.Dong;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

 //Duplicates naturally spawning hostile mobs.

public class DupeMobSpawns extends Dong implements Listener {

    public DupeMobSpawns() {
        registerToListener(this);
    }

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if(event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL)) {
            int entitySpawnDupes = plugin.config().getEntitySpawnDupes();
            if(entitySpawnDupes > -1) {
                if(!plugin.config().isDupeHostilesOnly() || event.getEntity() instanceof Monster) {
                    duplicateEntity(event.getEntity(), entitySpawnDupes);
                }
            } else {
                event.setCancelled(true);
            }
        }
    }

    private void duplicateEntity(LivingEntity entity, int amount) {
        for(int i = 0; i < amount; i++) {
            entity.getWorld().spawnEntity(entity.getLocation(), entity.getType());
        }
    }
}