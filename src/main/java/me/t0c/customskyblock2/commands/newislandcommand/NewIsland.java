package me.t0c.customskyblock2.commands.newislandcommand;

import me.t0c.customskyblock2.commands.Command;
import me.t0c.customskyblock2.files.playerdata.Data;
import me.t0c.customskyblock2.spawning.NewIslandFactory;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NewIsland extends Command {
    protected NewIsland(Player player, String[] args) {
        super(player, args);
    }

    @Override
    public boolean runCommand() {
        World world = plugin.overworld();
        Data data = plugin.playerData().getData().get(player.getUniqueId().toString());
        if(world == null) {
            world = player.getWorld();
        }

        if(data.getLastNewIslandCreation() + plugin.config().getNewIslandCommandDelay() < world.getFullTime()) {
            data.updateLastNewIslandCreation(world.getFullTime());
            dropPlayersItems();

            new NewIslandFactory(player, true);

        } else {
            sendMessage(player, MessageType.INFO, waitMessage((data.getLastNewIslandCreation() + plugin.config().getNewIslandCommandDelay()) - world.getFullTime()));
        }
        return true;
    }

    private void dropPlayersItems() {
        World w = player.getWorld();
        Location dropLoc = player.getLocation();
        for(ItemStack i : player.getInventory().getContents()) {
            if(i != null)
                w.dropItem(dropLoc, i);
        }
        player.getInventory().clear();
    }

    private static final long MINUTE_IN_TICKS = 1200;

    private String waitMessage(long waitTime) {
        StringBuilder s = new StringBuilder();

        s.append("You need to wait ");

        if(MINUTE_IN_TICKS > waitTime) { //less than a minute left to wait
            s.append(waitTime / 20);
            s.append(" seconds ");
        } else {
            s.append(waitTime / MINUTE_IN_TICKS);
            s.append(" minute(s) ");
        }
        s.append("before using that command again.");

        return s.toString();
    }
}
