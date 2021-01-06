package me.t0c.customskyblock2.custom.functionalItem.item;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.CSBConsumableFunctionalItem;
import me.t0c.customskyblock2.files.playerdata.RespawnLocation;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Arrays;
import java.util.List;

public class RecallPotion extends CSBClass implements CSBConsumableFunctionalItem {

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Drink this potion to return to your spawn point");
    }

    @Override
    public Material getMaterial() {
        return Material.POTION;
    }

    @Override
    public String getName() {
        return "Recall Potion";
    }

    @Override
    public boolean isShiny() {
        return true;
    }

    @Override
    public boolean onPlayerItemConsume(PlayerItemConsumeEvent event) {
        RespawnLocation r = plugin.playerData().getData().get(event.getPlayer().getUniqueId().toString()).getRespawnLocation();
        if(r != null) {
            event.getPlayer().teleport(r.asLocation(plugin.overworld()).add(0.5, 0, 0.5));
        } else {
            Location l = event.getPlayer().getBedSpawnLocation();
            if(l != null) {
                event.getPlayer().teleport(l);
            } else {
                event.getPlayer().sendMessage("Can't find a respawn point");
                return false;
            }
        }

        return true;
    }

}
