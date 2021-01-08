package me.t0c.customskyblock2.miscgamemodifiers;

import me.t0c.customskyblock2.Dong;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class DisablePortalsInNether extends Dong implements Listener {

    public DisablePortalsInNether() {
        registerToListener(this);
    }

    @EventHandler
    public void onPortalEnterInNether(PlayerTeleportEvent event) {
        if(event.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
            Player player = event.getPlayer();
            if(player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                sendMessage(player, MessageType.INFO, "You cannot use nether portals in the nether. To get back use the command: ");
                event.setCancelled(true);
            } else {
                sendMessage(player, MessageType.INFO, "To get back to your island use the command: ");
            }
        }
    }
}
