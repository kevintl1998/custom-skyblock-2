package me.t0c.customskyblock2.custom.functionalItem.item;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.CSBUsableFunctionalItem;
import me.t0c.customskyblock2.data.Entities;
import me.t0c.customskyblock2.runnables.CreateEntityRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

public class MysteryMobEgg extends CSBClass implements CSBUsableFunctionalItem {

    @Override
    public boolean onEntityInteract(PlayerInteractEntityEvent event) {
        return false;
    }

    @Override
    public boolean onBlockInteract(PlayerInteractEvent event) {
        if(Bukkit.getServer().getOnlinePlayers().size() == 1) {
            event.getPlayer().sendMessage("You can't use that right now!");
            return false;
        }
        Player p = Entities.getRandomPlayer();
        new CreateEntityRunnable(Entities.getRandomLivingEntity(), p.getLocation(), 1);
        return true;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Spawns a random entity on a random player including yourself.",
                "Can only be used when there is more than 1 person online");
    }

    @Override
    public Material getMaterial() {
        return Material.PARROT_SPAWN_EGG;
    }

    @Override
    public String getName() {
        return "Mystery Mob Egg";
    }

    @Override
    public boolean isShiny() {
        return true;
    }
}
