package me.t0c.customskyblock2.spawning.listeners;

import me.t0c.customskyblock2.Dong;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

public class PlayerUseElytra extends Dong implements Listener {

    public PlayerUseElytra() {
        registerToListener(this);
    }


    @EventHandler
    public void onElytraUse(EntityToggleGlideEvent event) {
        if(event.isGliding()) {
            if(event.getEntity() instanceof Player) {
                Player player = (Player)event.getEntity();
                if(!player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
