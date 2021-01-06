package me.t0c.customskyblock2.miscgamemodifiers;

import me.t0c.customskyblock2.Dong;
import me.t0c.customskyblock2.data.Entities;
import me.t0c.customskyblock2.runnables.CreateEntityRunnable;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

// Randomized the entity that comes from breeding two mobs

public class EntityBreedRandomizer extends Dong implements Listener {
    public EntityBreedRandomizer() {
        registerToListener(this);
    }

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event) {
        Location l =  event.getFather().getLocation();
        new CreateEntityRunnable(Entities.getRandomLivingEntity(), l, 1);
        l.getWorld().spawnParticle(Particle.HEART,l, 20, 1, 1, 1);
        event.getEntity().remove();
    }
}
