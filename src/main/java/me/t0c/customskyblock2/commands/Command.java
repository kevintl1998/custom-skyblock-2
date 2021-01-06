package me.t0c.customskyblock2.commands;

import me.t0c.customskyblock2.CSBClass;
import org.bukkit.entity.Player;

public abstract class Command extends CSBClass {
    protected Command(Player player, String[] args) {
        this.player = player;
        this.args = args;
    }

    protected final Player player;
    protected final String[] args;

    public abstract boolean runCommand();
}
