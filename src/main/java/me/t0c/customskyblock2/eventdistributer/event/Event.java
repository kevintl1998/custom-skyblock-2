package me.t0c.customskyblock2.eventdistributer.event;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.Dong;
import org.bukkit.entity.Player;

public abstract class Event extends CSBClass {
    public abstract int getWeight();
    public abstract void run(Player player);
    public abstract String name();


}
