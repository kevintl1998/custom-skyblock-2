package me.t0c.customskyblock2.commands.csbcommand;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.CustomSkyblock2;
import me.t0c.customskyblock2.custom.functionalItem.FunctionalItem;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class CSBCommand extends CSBClass implements CommandExecutor, TabCompleter {

    // commands:
    // /CSB newIsland
    // /CSB set [configOption] [value]

    public CSBCommand(JavaPlugin plugin) {
        PluginCommand command = plugin.getCommand("CSB");
        if(command != null) {
            command.setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            if(args.length == 0) {
                return false;
            }
                switch (args[0].toLowerCase()) {
                    case "newisland":
                        return new NewIsland(player, args).runCommand();
                    case "set":
                        return new SetConfigValue(player, args).runCommand();
                    case "bonuses":
                        return new Bonuses(player, args).runCommand();
                    case "funcitem":
                        return new FuncItem(player, args).runCommand();
                    case "reloadeventdata":
                        return new ReloadEventData(player, args).runCommand();
                    case "listconfig":
                        return new ListConfig(player, args).runCommand();
                    case "restarteventtimer":
                        plugin.restartEventTimer();
                        sendMessage(player, MessageType.INFO, "Restarted event timer with an eventDelay of " + plugin.config().getEventDelay() + " ticks.");
                        return true;
                    default:
                        return false;
                }
            }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        List<String> potential = new ArrayList<>();

        if(args.length == 1) {
            potential = Arrays.asList("newIsland", "set", "bonuses", "reloadEventData", "funcItem", "listConfig", "restartEventTimer");
        } else {
            switch(args[0].toLowerCase()) {
                case "newisland":
                    return null;
                case "bonuses":
                    potential = bonusesTabComplete(args);
                    break;
                case "set":
                    potential = setConfigValueTabComplete(args);
                    break;
                case "funcitem":
                    potential = funcItemTabComplete(args);
                    break;
                case "reloadeventdata":
                    potential = reloadEventDataTabComplete(args);
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

    private List<String> setConfigValueTabComplete(String[] args) {
        if(args.length == 2) {
            return Arrays.asList("dupeHostilesOnly", "enableBedSpawn", "entitySpawnDupes", "eventDelay", "islandDistance", "islandGenHeight", "logItemsReceivedInConsole", "newIslandCommandDelay");
        }
        return new ArrayList<>();
    }

    private List<String> bonusesTabComplete(String[] args) {
        if(args.length == 2) {
            return Arrays.asList("add", "set", "view");
        } else if(args.length == 3) {
            return null;
        } else if(args.length == 4) {
            return Arrays.asList("event", "item");
        }else if(args.length == 5) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    private List<String> funcItemTabComplete(String[] args) {
        if(args.length == 2) {
            return Collections.singletonList("get");
        } else if(args.length == 3) {
            if(args[1].toLowerCase().equals("get")) {
                return FunctionalItem.getFuncItemNames();
            }
        }
        return Collections.emptyList();
    }

    private List<String> reloadEventDataTabComplete(String[] args) {
        return new ArrayList<>();
    }

}