package me.t0c.customskyblock2.custom.functionalItem.item;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.CSBUsableFunctionalItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class PlayerRemover extends CSBClass implements CSBUsableFunctionalItem {

    @Override
    public boolean onEntityInteract(PlayerInteractEntityEvent event) {
        if(event.getRightClicked() instanceof Player) {
            event.getRightClicked().remove();
            return true;
        }
        return false;
    }

    @Override
    public boolean onBlockInteract(PlayerInteractEvent event) {
        return false;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("breaks any player you right click lol");
    }

    @Override
    public Material getMaterial() {
        return Material.NETHER_STAR;
    }

    @Override
    public String getName() {
        return "Player Remover";
    }

    @Override
    public boolean isShiny() {
        return true;
    }
}
