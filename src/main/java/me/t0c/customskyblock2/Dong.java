package me.t0c.customskyblock2;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.lang.reflect.Method;

public class Dong extends CSBClass {

    public void registerToListener(Listener l) {
        Bukkit.getServer().getPluginManager().registerEvents(l, plugin);
    }
}
