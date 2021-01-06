package me.t0c.customskyblock2.miscgamemodifiers;

import me.t0c.customskyblock2.Dong;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class DisableNetherPortals extends Dong implements Listener {

    public DisableNetherPortals() {
        registerToListener(this);
    }

    @EventHandler
    public void onNetherPortalCreation(PortalCreateEvent event) {
        if(event.getReason().equals(PortalCreateEvent.CreateReason.NETHER_PAIR)) {
            if(event.getEntity() instanceof Player) {
                event.getEntity().sendMessage(portalMessage());
            }
            event.setCancelled(true);
        }
    }

    private String portalMessage() {
        return "Portals are disabled.";
    }
}
