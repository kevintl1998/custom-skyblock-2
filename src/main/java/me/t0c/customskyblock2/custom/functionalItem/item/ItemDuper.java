package me.t0c.customskyblock2.custom.functionalItem.item;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.CSBUsableFunctionalItem;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemDuper extends CSBClass implements CSBUsableFunctionalItem {
    @Override
    public boolean onEntityInteract(PlayerInteractEntityEvent event) {
        return false;
    }

    @Override
    public boolean onBlockInteract(PlayerInteractEvent event) {
        if(event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.AIR)) {
            return false;
        }
        ItemStack item = event.getPlayer().getInventory().getItemInOffHand().clone();
        item.setAmount(1);
        event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), item);
        return true;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Use while holding an item in your offhand to duplicate that item");
    }

    @Override
    public Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public String getName() {
        return "Item Duplicator";
    }

    @Override
    public boolean isShiny() {
        return true;
    }
}
