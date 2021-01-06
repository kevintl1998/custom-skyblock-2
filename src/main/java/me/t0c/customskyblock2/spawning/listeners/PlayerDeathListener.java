package me.t0c.customskyblock2.spawning.listeners;

import me.t0c.customskyblock2.Dong;
import me.t0c.customskyblock2.files.playerdata.Bonuses;
import me.t0c.customskyblock2.files.playerdata.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener extends Dong implements Listener {
    public PlayerDeathListener() {
        registerToListener(this);
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Data data = plugin.playerData().getData().get(event.getEntity().getUniqueId().toString());
        data.getStats().playerDied();
        if(data.isKeepItemsOnDeath()) {
            data.setBonuses(Bonuses.newInstance(Bonuses.CreationReason.DEATH));
            event.setKeepInventory(true);
            data.setKeepItemsOnDeath(false);
        }
    }
}
