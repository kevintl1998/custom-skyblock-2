package me.t0c.customskyblock2.custom.functionalItem.item.wormholepotion;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class WormholeData {

    protected static final Map<Player, WormholeData> teleportRequestData = new HashMap<>();

    protected WormholeRequestTimerRunnable runnable = null;
    protected Player target = null;

    public static boolean hasTarget(Player player) {
        WormholeData data = teleportRequestData.get(player);
        if(data != null) {
            return data.target != null;
        }
        return false;
    }

    public static boolean containsTarget(Player target) {
        for(WormholeData data : teleportRequestData.values()) {
            if(target.equals(data.target)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsRunnable(WormholeRequestTimerRunnable runnable) {
        for(WormholeData data : teleportRequestData.values()) {
            if(runnable.equals(data.runnable)) {
                return true;
            }
        }
        return false;
    }
}
