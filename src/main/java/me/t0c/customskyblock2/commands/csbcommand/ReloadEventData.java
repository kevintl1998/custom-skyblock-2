package me.t0c.customskyblock2.commands.csbcommand;

import me.t0c.customskyblock2.commands.Command;
import org.bukkit.entity.Player;

public class ReloadEventData extends Command {
    public ReloadEventData(Player player, String[] args) {
        super(player, args);
    }

    @Override
    public boolean runCommand() {
        plugin.reloadEventData();
        sendMessage(player, MessageType.INFO, "EventData was reloaded.");
        return true;
    }

}
