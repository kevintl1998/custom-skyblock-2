package me.t0c.customskyblock2.custom.functionalItem.item.wormholepotion;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.CustomSkyblock2;
import me.t0c.customskyblock2.custom.functionalItem.FunctionalItem;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WormholeRequestTimerRunnable extends BukkitRunnable {

    private static final int THIRTY_SECONDS = 30 * 20;

    private final Player player;
    private final Player target;

    public WormholeRequestTimerRunnable(Player player, Player target) {
        this.player = player;
        this.target = target;
        runTaskLater(CustomSkyblock2.instance(), THIRTY_SECONDS);
    }

    @Override
    public void run() {
        CSBClass.message(target, CSBClass.MessageType.INFO, "The teleport request from " + player.getName() + " has expired.");
        CSBClass.message(player, CSBClass.MessageType.INFO, target.getName() + " didn't respond to your teleport request in time.");
        player.getWorld().dropItem(player.getLocation(), FunctionalItem.WORMHOLE_POTION.getItem());
        WormholeData.teleportRequestData.remove(player);
    }
}
