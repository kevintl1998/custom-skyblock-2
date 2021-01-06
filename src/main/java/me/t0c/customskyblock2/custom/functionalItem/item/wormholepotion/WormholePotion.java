package me.t0c.customskyblock2.custom.functionalItem.item.wormholepotion;

import javafx.util.Pair;
import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.CSBConsumableFunctionalItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WormholePotion extends CSBClass implements CSBConsumableFunctionalItem {

    /*
     * Player = player requesting the teleport
     * target = the player being requested to teleport to
     */

    //public static Map<Player, Pair<Player, BukkitRunnable>> getTeleportRequestData() { return teleportRequestData; }


    //teleport requests last for 30 seconds each
    //can only have 1 pending teleport request at a time
    //removes the potion as soon as the player drinks it
    //  gives it back if the teleport request was ignored/declined

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Teleports you to a player of your choice ",
                            "when consumed. However, the player you are",
                            "teleporting to must allow it.");
    }

    @Override
    public Material getMaterial() {
        return Material.POTION;
    }

    @Override
    public String getName() {
        return "Wormhole Potion";
    }

    @Override
    public boolean isShiny() {
        return true;
    }

    @Override
    public boolean onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if(Bukkit.getOnlinePlayers().size() > 0) {
            return consumedPotion(event.getPlayer());
        } else {
            event.getPlayer().sendMessage("This item can only be used when multiple players are online");
            return false;
        }
    }

    private boolean consumedPotion(Player player) {
        if(!WormholeData.teleportRequestData.containsKey(player)) {
            WormholeData.teleportRequestData.put(player, new WormholeData());
            player.sendMessage("To request a teleport: '/wh request <player>'. To cancel the teleport: '/wh cancel'");
            return true;
        } else {
            player.sendMessage("You cannot use this item right now.");
            return false;
        }
    }

}
