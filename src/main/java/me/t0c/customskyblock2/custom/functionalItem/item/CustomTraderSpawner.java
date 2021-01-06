package me.t0c.customskyblock2.custom.functionalItem.item;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.CSBUsableFunctionalItem;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class CustomTraderSpawner extends CSBClass implements CSBUsableFunctionalItem {

    @Override
    public boolean onEntityInteract(PlayerInteractEntityEvent event) {
        return false;
    }

    @Override
    public boolean onBlockInteract(PlayerInteractEvent event) {
        event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.WANDERING_TRADER);
        sendMessage(event.getPlayer(), MessageType.SUCCESS, "Summoned wandering trader at your location.");
        return true;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Use to summon a wandering trader that",
                            "sells body pillows or custom items");
    }

    @Override
    public Material getMaterial() {
        return Material.WANDERING_TRADER_SPAWN_EGG;
    }

    @Override
    public String getName() {
        return "Custom Trader spawner";
    }

    @Override
    public boolean isShiny() {
        return true;
    }
}
