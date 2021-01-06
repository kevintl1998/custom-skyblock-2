package me.t0c.customskyblock2.spawning.listeners;

import me.t0c.customskyblock2.CustomSkyblock2;
import me.t0c.customskyblock2.Dong;
import me.t0c.customskyblock2.files.playerdata.RespawnLocation;
import me.t0c.customskyblock2.runnables.TeleportRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener extends Dong implements Listener {
    public PlayerRespawnListener() {
        registerToListener(this);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        //TODO:: test if bed spawn is enabled and if bed spawn exists. If not, tp player to their bedrock
        if(!plugin.config().isEnableBedSpawn() || event.getPlayer().getBedSpawnLocation() == null) {
            Player player = event.getPlayer();
            Location l = CustomSkyblock2.instance().playerData().getData().get(player.getUniqueId().toString()).getRespawnLocation().asLocation(plugin.overworld());
            tidyRespawnLocation(l);
            l.add(0.5f, 0, 0.5f);
            if(player.getBedSpawnLocation() == null) player.setBedSpawnLocation(l,true);
            new TeleportRunnable(player, l);
        } else {
            tidyRespawnLocation(CustomSkyblock2.instance().playerData().getData().get(event.getPlayer().getUniqueId().toString()).getRespawnLocation().asLocation(plugin.overworld()));
        }
    }

    private void tidyRespawnLocation(Location l) {
        l.add(0,-1,0).getBlock().setType(Material.BEDROCK);
        l.add(0,2,0).getBlock().setType(Material.AIR);
        l.add(0,-1,0).getBlock().setType(Material.AIR);
    }

}
