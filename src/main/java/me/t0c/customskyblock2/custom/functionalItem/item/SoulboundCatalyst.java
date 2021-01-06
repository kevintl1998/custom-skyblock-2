package me.t0c.customskyblock2.custom.functionalItem.item;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.CSBUsableFunctionalItem;
import me.t0c.customskyblock2.files.playerdata.Data;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class SoulboundCatalyst extends CSBClass implements CSBUsableFunctionalItem {
    @Override
    public List<String> getDescription() {
        return Arrays.asList("Use to keep your items on death one time.");
    }

    @Override
    public Material getMaterial() {
        return Material.EXPERIENCE_BOTTLE;
    }

    @Override
    public String getName() {
        return "Soulbound";
    }

    @Override
    public boolean isShiny() {
        return true;
    }

    @Override
    public boolean onEntityInteract(PlayerInteractEntityEvent event) {
        return false;
    }

    @Override
    public boolean onBlockInteract(PlayerInteractEvent event) {
        Data data = plugin.playerData().getData().get(event.getPlayer().getUniqueId().toString());

        if(!data.isKeepItemsOnDeath()) {
            data.setKeepItemsOnDeath(true);
            event.getPlayer().sendMessage("Your items will be kept on death.");
            return true;
        } else {
            event.getPlayer().sendMessage("you can't use that right now.");
            return false;
        }
    }
}
