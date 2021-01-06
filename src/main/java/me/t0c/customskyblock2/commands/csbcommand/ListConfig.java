package me.t0c.customskyblock2.commands.csbcommand;

import me.t0c.customskyblock2.commands.Command;
import me.t0c.customskyblock2.files.config.Config;
import org.bukkit.entity.Player;

public class ListConfig extends Command {
    public ListConfig(Player player, String[] args) {
        super(player, args);
    }

    @Override
    public boolean runCommand() {
        Config config = plugin.config();
        sendMessage(player, MessageType.INFO, "Config Values:");
        player.sendMessage(" - entitySpawnDupes: " + config.getEntitySpawnDupes());
        player.sendMessage(" - dupeHostilesOnly: " + config.isDupeHostilesOnly());
        player.sendMessage(" - logItemsReceivedInConsole: " + config.isLogItemsReceivedInConsole());
        player.sendMessage(" - eventDelay: " + config.getEventDelay());
        player.sendMessage(" - newIslandCommandDelay: " + config.getNewIslandCommandDelay());
        player.sendMessage(" - islandDistance: " + config.getIslandDistance());
        player.sendMessage(" - islandGenHeight: " + config.getIslandGenHeight());
        player.sendMessage(" - enableBedSpawn: " + config.isEnableBedSpawn());
        player.sendMessage(" - overworld: " + config.getOverworld());
        player.sendMessage(" - nether: " + config.getNether());
        player.sendMessage(" - end: " + config.getEnd());
        return true;
    }
}
