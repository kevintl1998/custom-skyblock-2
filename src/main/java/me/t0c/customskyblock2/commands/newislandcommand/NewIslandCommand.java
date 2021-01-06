package me.t0c.customskyblock2.commands.newislandcommand;
import me.t0c.customskyblock2.spawning.NewIslandFactory;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Command that any player can use to move them to a new island
 *
 * Process:
 * Player executes command
 * All the player's Items get dropped onto the ground where they are standing
 * player gets teleported to new island
 *
 * Items in enderchest will not get deleted.
 *
 */

public class NewIslandCommand implements CommandExecutor {



    public NewIslandCommand(JavaPlugin plugin) {

        PluginCommand command = plugin.getCommand("newIsland");
        if(command != null) {
            command.setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(sender instanceof Player) {
            return new NewIsland((Player) sender, args).runCommand();
        } else {
            sender.sendMessage("This command can only be run by a player");
        }
        return true;
    }




}
