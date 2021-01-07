package me.t0c.customskyblock2.commands.joinislandcommand;

import me.t0c.customskyblock2.CSBClass;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
            if(args.length > 1) {
                Player player = (Player) sender;
                switch (args[0].toLowerCase()) {
                    case "request":
                    case "accept":
                    case "deny":
                    case "cancel":
                        return new JoinIsland(player, args).runCommand();
                    default:
                        sendMessage(player, MessageType.INFO, "Command syntax: /joinIsland [request|accept|deny|cancel]");
                }
                return true;
            } else {
                sendMessage((Player)sender, MessageType.INFO, "Command syntax: /joinIsland [request|accept|deny|cancel]");
                return true;
            }
        } else {
            sender.sendMessage("Only players can use this command");
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> potential = new ArrayList<>();

        if(!(sender instanceof Player)) {
            return potential;
        }

        Player player = (Player)sender;

        if(args.length == 1) {
            potential = Arrays.asList("accept", "cancel", "deny", "request");
        } else if(args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "request":
                case "accept":
                case "deny":
                    potential = null;
                    break;
            }
        }

        if(potential == null) {
            return null;
        } else if(potential.size() == 0) {
            return potential;
        }

        List<String> result = new ArrayList<>();
        for(String s : potential) {
            if(s.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                result.add(s);
        }
        Collections.sort(result);
        return result;
    }
}
