package me.t0c.customskyblock2.commands.csbcommand;

import me.t0c.customskyblock2.CustomSkyblock2;
import me.t0c.customskyblock2.commands.Command;
import me.t0c.customskyblock2.spawning.NewIslandFactory;
import org.bukkit.Bukkit;
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
        Player p;
        if(args.length < 2) {
            sendMessage(player, MessageType.INFO, "Command usage: /csb newIsland <player>");
        } else if((p = Bukkit.getPlayer(args[1])) != null) {

            dropPlayersItems(p);
            new NewIslandFactory(p, true);
            plugin.playerData().getData().get(p.getUniqueId().toString()).updateLastNewIslandCreation(plugin.overworld().getFullTime());

        } else {
            sendMessage(player, MessageType.INFO, args[1] + " is not online or does not exist.");
        }
        return true;
    }

    //TODO: MAKE SURE THIS DROPS ALL THE PLAYER'S ITEMS!
    private void dropPlayersItems(Player p) {
        World w = p.getWorld();
        Location dropLoc = p.getLocation();
        for(ItemStack i : p.getInventory().getContents()) {
            if(i != null) w.dropItem(dropLoc, i);
        }
        p.getInventory().clear();
    }

}
