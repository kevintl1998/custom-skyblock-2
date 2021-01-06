package me.t0c.customskyblock2.commands.csbcommand;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import me.t0c.customskyblock2.StringParser;
import me.t0c.customskyblock2.commands.Command;
import org.bukkit.entity.Player;

import java.awt.*;

public class SetConfigValue extends Command {
    protected SetConfigValue(Player player, String[] args) {
        super(player, args);
    }

    @Override
    public boolean runCommand() {
        if(args.length < 3) {
            sendMessage(player, MessageType.INFO, "Command usage: /csb set [configOption] [value]");
        } else {
            switch(args[1].toLowerCase()) {
                case "entityspawndupes":
                    modifyEntitySpawnDupes();
                    break;
                case "dupehostilesonly":
                    modifyDupeHostilesOnly();
                    break;
                case "eventdelay":
                    modifyEventDelay();
                    break;
                case "newislandcommanddelay":
                    modifyNewIslandCommandDelay();
                    break;
                case "islanddistance":
                    modifyIslandDistance();
                    break;
                case "islandgenheight":
                    modifyIslandGenHeight();
                    break;
                case "enablebedspawn":
                    modifyEnableBedSpawn();
                    break;
                case "logitemsreceivedinconsole":
                    modifyLogItemsReceivedInConsole();
                    break;
                default:
                    sendMessage(player, MessageType.INFO, "Command usage: /csb set [configOption] [value]");
            }
        }
        return true;
    }

    private void modifyEntitySpawnDupes() {
        if(StringParser.INTEGER.isStringType(args[2])) {
            int newVal = Integer.parseInt(args[2]);
            if(newVal < -1) {
                sendMessage(player, MessageType.INFO, "Cannot set entitySpawnDupes to a value less than -1");
            } else {
                plugin.config().setEntitySpawnDupes(newVal);
                sendMessage(player, MessageType.INFO, "entitySpawnDupes was set to" + newVal);
            }
        } else {
            sendMessage(player, MessageType.INFO, "Could not set entitySpawnDupes to " + args[2]);
        }
    }

    private void modifyDupeHostilesOnly() {
        if(args[2].toLowerCase().equals("true")) {
            plugin.config().setDupeHostilesOnly(true);
            sendMessage(player, MessageType.INFO, "dupeHostilesOnly was set to true");
        } else if(args[2].toLowerCase().equals("false")) {
            plugin.config().setDupeHostilesOnly(false);
            sendMessage(player, MessageType.INFO, "dupeHostilesOnly was set to false");
        } else {
            sendMessage(player, MessageType.INFO, "dupeHostilesOnly can only be 'true' or 'false'");
        }
    }

    private void modifyEventDelay() {
        if(StringParser.INTEGER.isStringType(args[2])) {
            int newVal = Integer.parseInt(args[2]);
            if(newVal < 1) {
                sendMessage(player, MessageType.INFO, "eventDelay must be greater than 0.");
            } else {
                plugin.config().setEventDelay(newVal);
                sendMessage(player, MessageType.INFO, "eventDelay was set to " + args[2]);
                sendMessage(player, MessageType.INFO, "Restart the event timer using '/csb restartEventTimer' to apply this to the server.");
            }
        } else {
            sendMessage(player, MessageType.INFO, "Could not set eventDelay to " + args[2]);
        }
    }

    private void modifyNewIslandCommandDelay() {
        if(StringParser.INTEGER.isStringType(args[2])) {
            int newVal = Integer.parseInt(args[2]);
            if(newVal < 0) {
                sendMessage(player, MessageType.INFO, "newIslandCommandDelay must be greater or equal to 0.");
            } else {
                plugin.config().setNewIslandCommandDelay(newVal);
                sendMessage(player, MessageType.INFO, "newIslandCommandDelay was set to " + args[2]);
            }
        } else {
            sendMessage(player, MessageType.INFO, "Could not set newIslandCommandDelay to " + args[2]);
        }
    }

    private void modifyIslandDistance() {
        if(StringParser.INTEGER.isStringType(args[2])) {
            int newVal = Integer.parseInt(args[2]);
            if(newVal < 1) {
                sendMessage(player, MessageType.INFO, "islandDistance must be greater than 0.");
            } else {
                plugin.config().setIslandDistance(newVal);
                sendMessage(player, MessageType.INFO, "islandDistance was set to " + args[2]);
            }
        } else {
            sendMessage(player, MessageType.INFO, "Could not set islandDistance to " + args[2]);
        }
    }

    private void modifyIslandGenHeight() {
        if(StringParser.INTEGER.isStringType(args[2])) {
            int newVal = Integer.parseInt(args[2]);
            if(newVal > 255 || newVal < 0) {
                sendMessage(player, MessageType.INFO, "islandGenHeight must be between 0 and 255");
            } else {
                plugin.config().setIslandGenHeight(newVal);
            }
        } else {
            sendMessage(player, MessageType.INFO, "Could not set islandGenHeight to " + args[2]);
        }
    }

    private void modifyEnableBedSpawn() {
        if(args[2].toLowerCase().equals("true")) {
            plugin.config().setEnableBedSpawn(true);
            sendMessage(player, MessageType.INFO, "enableBedSpawn was set to true");
        } else if(args[2].toLowerCase().equals("false")) {
            plugin.config().setEnableBedSpawn(false);
            sendMessage(player, MessageType.INFO, "enableBedSpawn was set to false");
        } else {
            sendMessage(player, MessageType.INFO, "enableBedSpawn can only be 'true' or 'false'");
        }
    }

    private void modifyLogItemsReceivedInConsole() {
        if(args[2].toLowerCase().equals("true")) {
            plugin.config().setLogItemsReceivedInConsole(true);
            sendMessage(player, MessageType.INFO, "logItemsReceivedInConsole was set to true");
            sendMessage(player, MessageType.WARNING, "This feature is currently not working because I'm too lazy to make it.");
        } else if(args[2].toLowerCase().equals("false")) {
            plugin.config().setLogItemsReceivedInConsole(false);
            sendMessage(player, MessageType.INFO, "logItemsReceivedInConsole was set to false");
            sendMessage(player, MessageType.WARNING, "This feature is currently not working because I'm too lazy to make it.");
        } else {
            sendMessage(player, MessageType.INFO, "logItemsReceivedInConsole can only be 'true' or 'false'");
        }
    }
}
