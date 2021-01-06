package me.t0c.customskyblock2.commands.csbcommand;

import me.t0c.customskyblock2.StringParser;
import me.t0c.customskyblock2.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Bonuses extends Command {

    protected Bonuses(Player player, String[] args) {
        super(player, args);
    }

    @Override
    public boolean runCommand() {
        if(args.length < 2) {
            sendMessage(player, MessageType.INFO, "Command usage: /csb bonuses [view|add|set]");
        } else {
            switch (args[1].toLowerCase()) {
                case "view":
                    viewBonuses();
                    break;
                case "add":
                    addBonuses();
                    break;
                case "set":
                    setBonuses();
                    break;
            }
        }
        return true;
    }

    private void viewBonuses() {
        if(args.length < 3) {
            sendMessage(player, MessageType.INFO, "Command usage: /csb bonuses view [player]");
        } else {
            Player target = Bukkit.getPlayer(args[2]);
            if (target != null) {
                sendMessage(player, MessageType.INFO, bonusInfoMessage(target));

            } else {
                sendMessage(player, MessageType.INFO, "That player is not online right now");
            }
        }
    }

    private void addBonuses() {
        if(args.length < 5) {
            sendMessage(player, MessageType.INFO, "Command usage: /csb bonuses add [player] [bonusType] [amount]");
        } else {
            Player target = Bukkit.getPlayer(args[2]);
            if(target != null) {
                if(StringParser.INTEGER.isStringType(args[4])) {
                    int val = Integer.parseInt(args[4]);
                    if (args[3].toLowerCase().equals("item")) {
                        me.t0c.customskyblock2.files.playerdata.Bonuses bonuses = plugin.playerData().getData().get(target.getUniqueId().toString()).getBonuses();
                        int currentVal = bonuses.getBonusItems();
                        if(currentVal + val < 0) {
                            bonuses.setBonusItems(0);

                            sendMessage(player, MessageType.INFO, "Added " + val + " to " + target.getName() + "'s item bonus for a total of " + 0);
                        } else {
                            bonuses.setBonusItems(currentVal + val);
                            sendMessage(player, MessageType.INFO, "Added " + val + " to " + target.getName() + "'s event bonus for a total of " + (currentVal + val));
                        }
                    } else if (args[3].toLowerCase().equals("event")) {
                        me.t0c.customskyblock2.files.playerdata.Bonuses bonuses = plugin.playerData().getData().get(target.getUniqueId().toString()).getBonuses();
                        int currentVal = bonuses.getBonusEvents();
                        if(currentVal + val < 0) {
                            bonuses.setBonusEvents(0);
                            sendMessage(player, MessageType.INFO, "Added " + val + " to " + target.getName() + "'s event bonus for a total of " + 0);
                        } else {
                            bonuses.setBonusEvents(currentVal + val);
                            sendMessage(player, MessageType.INFO, "Added " + val + " to " + target.getName() + "'s event bonus for a total of " + (currentVal + val));
                        }
                    } else {
                        sendMessage(player, MessageType.INFO, "Command usage: /csb bonuses add [player] [bonusType] [amount]");
                    }
                } else {
                    sendMessage(player, MessageType.INFO, "Command usage: /csb bonuses add [player] [bonusType] [amount]");
                }
            } else {
                sendMessage(player, MessageType.INFO, "That player is not online right now");
            }
        }
    }

    private void setBonuses() {
        if(args.length < 5) {
            sendMessage(player, MessageType.INFO, "Command usage: /csb bonuses add [player] [bonusType] [amount]");
        } else {
            Player target = Bukkit.getPlayer(args[2]);
            if(target != null) {
                if(StringParser.INTEGER.isStringType(args[4])) {
                    int val = Integer.parseInt(args[4]);
                    if (args[3].toLowerCase().equals("item")) {
                        me.t0c.customskyblock2.files.playerdata.Bonuses bonuses = plugin.playerData().getData().get(target.getUniqueId().toString()).getBonuses();
                        if(val < 0) {
                            bonuses.setBonusItems(0);
                            sendMessage(player, MessageType.INFO, "Set " + target.getName() + "'s item bonus to " + 0);
                        } else {
                            bonuses.setBonusItems(val);
                            sendMessage(player, MessageType.INFO, "Set " + target.getName() + "'s item bonus to " + val);
                        }
                    } else if (args[3].toLowerCase().equals("event")) {
                        me.t0c.customskyblock2.files.playerdata.Bonuses bonuses = plugin.playerData().getData().get(target.getUniqueId().toString()).getBonuses();
                        if(val < 0) {
                            bonuses.setBonusEvents(0);
                            sendMessage(player, MessageType.INFO, "Set " + target.getName() + "'s event bonus to " + 0);
                        } else {
                            bonuses.setBonusEvents(val);
                            sendMessage(player, MessageType.INFO, "Set " + target.getName() + "'s event bonus to " + val);
                        }
                    } else {
                        sendMessage(player, MessageType.INFO, "Command usage: /csb bonuses add [player] [bonusType] [amount]");
                    }
                } else {
                    sendMessage(player, MessageType.INFO, "Command usage: /csb bonuses add [player] [bonusType] [amount]");
                }
            } else {
                sendMessage(player, MessageType.INFO, "That player is not online right now");
            }
        }
    }

    private String bonusInfoMessage(Player target) {
        StringBuilder s = new StringBuilder();
        me.t0c.customskyblock2.files.playerdata.Bonuses bonuses = plugin.playerData().getData().get(target.getUniqueId().toString()).getBonuses();

        s.append(target.getName() + "'s");
        s.append(" current bonuses");
        s.append('\n');
        s.append(" - Event: ");
        s.append(bonuses.getBonusEvents());
        s.append('\n');
        s.append(" - Item: ");
        s.append(bonuses.getBonusItems());

        return s.toString();
    }
}