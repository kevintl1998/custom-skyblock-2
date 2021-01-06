package me.t0c.customskyblock2.custom.functionalItem.item.wormholepotion;

import me.t0c.customskyblock2.Dong;
import me.t0c.customskyblock2.custom.functionalItem.FunctionalItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class WormholeListener extends Dong implements Listener {

    public WormholeListener() {
        registerToListener(this);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if(WormholeData.containsTarget(event.getPlayer())) { //if a target leaves the game
            WormholeData.teleportRequestData.keySet()
                    .removeIf(player -> WormholeData.teleportRequestData.get(player).target.equals(event.getPlayer()));

        } else { // if player leaves
            if(WormholeData.teleportRequestData.get(event.getPlayer()) != null) {
                Player target;
                if ((target = WormholeData.teleportRequestData.get(event.getPlayer()).target) != null) {
                    sendMessage(target, MessageType.INFO, "The teleport request from " + event.getPlayer().getName() + " was cancelled.");
                    WormholeData.teleportRequestData.remove(event.getPlayer());
                }
            }

        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        BukkitRunnable runnable;
        if(WormholeData.hasTarget(event.getEntity())) {
            WormholeData data = WormholeData.teleportRequestData.get(event.getEntity());
            sendMessage(data.target, MessageType.INFO, "The teleport request from " + event.getEntity().getName() + " was cancelled.");
            data.runnable.cancel();
        }
        WormholeData.teleportRequestData.remove(event.getEntity());
    }

    public static void onServerClose() {
        for(Player player : WormholeData.teleportRequestData.keySet()) {
            WormholeRequestTimerRunnable runnable = WormholeData.teleportRequestData.get(player).runnable;
            if(runnable != null) runnable.cancel();
            player.getWorld().dropItem(player.getLocation(), FunctionalItem.WORMHOLE_POTION.getItem());
        }
    }

}
