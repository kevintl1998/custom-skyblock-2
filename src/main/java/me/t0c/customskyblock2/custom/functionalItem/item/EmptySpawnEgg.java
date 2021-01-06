package me.t0c.customskyblock2.custom.functionalItem.item;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.CSBUsableFunctionalItem;
import me.t0c.customskyblock2.data.Entities;
import org.bukkit.Material;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EmptySpawnEgg extends CSBClass implements CSBUsableFunctionalItem {

    @Override
    public boolean onEntityInteract(PlayerInteractEntityEvent event) {
        if(!event.getRightClicked().getType().isAlive()
                && Entities.illegalEntities.contains(event.getRightClicked().getType())) {
        } else {
            Material m = entityToSpawnEgg(event.getRightClicked().getType());
            if(m != null) {
                Map<Integer, ItemStack> unadded = event.getPlayer().getInventory().addItem(new ItemStack(m));
                if (unadded.size() > 0) {
                    for (ItemStack i : unadded.values()) {
                        event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), i);
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onBlockInteract(PlayerInteractEvent event) {
        return false;
    }

    private Material entityToSpawnEgg(EntityType entityType) {
        return Material.getMaterial(entityType.name().toUpperCase() + "_SPAWN_EGG");
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Right click any living entity to create a spawn egg of that entity.");
    }

    @Override
    public Material getMaterial() {
        return Material.GHAST_SPAWN_EGG;
    }

    @Override
    public String getName() {
        return "Empty Spawn Egg";
    }

    @Override
    public boolean isShiny() {
        return true;
    }
}
