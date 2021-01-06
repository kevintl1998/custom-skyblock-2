package me.t0c.customskyblock2.custom.functionalItem;

import me.t0c.customskyblock2.custom.functionalItem.BaseFunctionalItem;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public interface CSBConsumableFunctionalItem extends BaseFunctionalItem {

    boolean onPlayerItemConsume(PlayerItemConsumeEvent event);

}
