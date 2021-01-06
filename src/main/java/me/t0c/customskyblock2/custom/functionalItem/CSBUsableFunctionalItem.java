package me.t0c.customskyblock2.custom.functionalItem;

import me.t0c.customskyblock2.custom.functionalItem.BaseFunctionalItem;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface CSBUsableFunctionalItem extends BaseFunctionalItem {

    boolean onEntityInteract(PlayerInteractEntityEvent event);
    boolean onBlockInteract(PlayerInteractEvent event);

}
