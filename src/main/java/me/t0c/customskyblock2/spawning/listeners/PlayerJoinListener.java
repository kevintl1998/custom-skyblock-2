package me.t0c.customskyblock2.spawning.listeners;

import me.t0c.customskyblock2.Dong;
import me.t0c.customskyblock2.spawning.NewIslandFactory;
import me.t0c.customskyblock2.files.playerdata.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends Dong implements Listener {
    public PlayerJoinListener() {
        registerToListener(this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!event.getPlayer().hasPlayedBefore()) {
            System.out.println("new player joined");
            plugin.playerData().getData().put(event.getPlayer().getUniqueId().toString(), new Data());
            new NewIslandFactory(event.getPlayer(), false);
            plugin.savePlayerData();
            plugin.saveConfig();
        }
    }

}