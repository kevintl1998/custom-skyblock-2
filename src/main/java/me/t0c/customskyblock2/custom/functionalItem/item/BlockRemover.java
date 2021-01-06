package me.t0c.customskyblock2.custom.functionalItem.item;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.CSBUsableFunctionalItem;
import me.t0c.customskyblock2.data.Blocks;
import me.t0c.customskyblock2.files.playerdata.RespawnLocation;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class BlockRemover extends CSBClass implements CSBUsableFunctionalItem {
    @Override
    public boolean onEntityInteract(PlayerInteractEntityEvent event) {
        return false;
    }

    @Override
    public boolean onBlockInteract(PlayerInteractEvent event) {
        if(event.getClickedBlock() != null) {
            if (!Blocks.airBlocks.contains(event.getMaterial())) {
                if (!RespawnLocation.isRespawnLocation(event.getClickedBlock().getLocation().clone().add(0,1,0))) {
                    event.getClickedBlock().setType(Material.AIR);
                    return true;
                } else {
                    sendMessage(event.getPlayer(), MessageType.INFO, "Could not remove this block.");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Right click any block to remove it.",
                             "Does not work on the bedrock at your spawn point");
    }

    @Override
    public Material getMaterial() {
        return Material.BARRIER;
    }

    @Override
    public String getName() {
        return "Block Remover";
    }

    @Override
    public boolean isShiny() {
        return true;
    }
}
