package me.t0c.customskyblock2.commands.joinislandcommand;

import me.t0c.customskyblock2.CSBClass;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.List;

public class JoinIslandCommand extends CSBClass implements CommandExecutor, TabCompleter {



    public JoinIslandCommand() {
        PluginCommand command = plugin.getCommand("joinIsland");
        if(command != null) {
            command.setExecutor(this);
        }
    }

    // /joinIsland [request|accept|deny|cancel]

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            switch (args[0].toLowerCase()) {
                case "request":
                case "accept":
                case "deny":
                case "cancel":
                    return new JoinIsland(player, args).runCommand();
                default:
                    sendMessage(player,MessageType.INFO, "Command syntax: /joinIsland [request|accept|deny|cancel]");
            }
            return true;
        } else {
            sender.sendMessage("Only players can use this command");
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
