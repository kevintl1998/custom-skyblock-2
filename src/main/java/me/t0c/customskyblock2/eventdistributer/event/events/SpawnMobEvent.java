package me.t0c.customskyblock2.eventdistributer.event.events;

import me.t0c.customskyblock2.eventdistributer.event.Event;
import me.t0c.customskyblock2.data.Blocks;
import me.t0c.customskyblock2.data.Entities;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpawnMobEvent extends Event {

    private static final PotionEffect slowfall = new PotionEffect(PotionEffectType.SLOW_FALLING,10, 0, true, false);
    private static final int MIN_SPAWN_DISTANCE = 2;
    private static final int MAX_ATTEMPTS = 4;

    //protected int initWeight() {
        //return 5;
    //}

    @Override
    public int getWeight() {
        return plugin.eventData().getSpawnMobEventWeight();
    }

    @Override
    public void run(Player player) {
        //spawn mob 3 blocks above the player and give it slowfalling

        EntityType entityType = Entities.getRandomLivingEntity();
        if(!plugin.eventData().isEnableExplosiveMobsInSpawnMobEvent()) {
            while (Entities.explosiveMobs.contains(entityType)) {
            }
        }
        Entity entity = player.getWorld().spawnEntity(determineSpawnLocation(player), entityType);
        ((LivingEntity)entity).addPotionEffect(slowfall);
    }

    private Location determineSpawnLocation(Player p) {
        Location l = p.getLocation().clone();
        l.add(0, MIN_SPAWN_DISTANCE, 0);
        for(int i = 0; i < MAX_ATTEMPTS; i++) {
            if(Blocks.airBlocks.contains(l.getBlock().getType())) {
                return l;
            } else {
                l.add(0,1,0);
            }
        }
        return l;
    }

}
