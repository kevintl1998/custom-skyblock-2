package me.t0c.customskyblock2.custom.functionalItem.item.wormholepotion;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.FunctionalItem;
import me.t0c.customskyblock2.runnables.TeleportRunnable;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WormholeCommand extends CSBClass implements CommandExecutor, TabCompleter {

    public WormholeCommand() {
        PluginCommand command = plugin.getCommand("wh");
        if(command != null) {
            command.setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            if(args.length == 2) { // /wh [accept|deny|request] <player>
                Player selected;
                if((selected = Bukkit.getPlayer(args[1])) != null) {
                    //command sender is a target && selected requested a tp
                    if (WormholeData.containsTarget(player) && WormholeData.teleportRequestData.containsKey(selected)) {
                        if(args[0].toLowerCase().equals("accept")) {
                            acceptTeleportRequest(selected, player);
                        } else if(args[0].toLowerCase().equals("deny")) {
                            denyTeleportRequest(selected, player);
                        } else {
                            sendMessage(player, MessageType.INFO, "Command syntax: /wh [accept|deny] <player>");
                        }
                            //command sender requests tp
                    } else if(WormholeData.teleportRequestData.containsKey(player)) {
                        if(args[0].toLowerCase().equals("request")) {
                            if(!player.equals(selected)) {
                                requestTeleport(player, selected);
                            } else {
                                sendMessage(player, MessageType.INFO, "You cannot send a teleport request to yourself");
                            }
                        } else {
                            sendMessage(player, MessageType.INFO, "Command syntax: /wh request <player>");
                        }
                    } else {
                        if(args[0].toLowerCase().equals("accept") || args[0].toLowerCase().equals("deny")) {
                            sendMessage(player, MessageType.INFO, "There is no pending teleport request from that player");
                        } else {
                            sendMessage(player, MessageType.INFO, "You can't request a teleport right now");
                        }
                    }
                } else {
                    sendMessage(player, MessageType.INFO, "That player is not online or does not exist");
                }
            } else if(args.length == 1) { // /wh cancel || wrong syntax
                if(args[0].toLowerCase().equals("cancel") && !WormholeData.hasTarget(player)) {
                    sendMessage(player, MessageType.INFO, "Teleport cancelled.");
                    player.getWorld().dropItem(player.getLocation(), FunctionalItem.WORMHOLE_POTION.getItem());
                    WormholeData.teleportRequestData.remove(player);
                } else {
                    sendMessage(player, MessageType.INFO, "Command syntax: /wh [accept|deny|request|cancel]");
                }
            } else {
                sendMessage(player, MessageType.INFO, "Command syntax: /wh [accept|deny|request|cancel]");
            }
        } else {
            sender.sendMessage("This command can only be executed by a player");
        }

        return true;
    }

    private void acceptTeleportRequest(Player player, Player target) {
        sendMessage(player, MessageType.INFO, "Teleporting you to " + target.getName());
        sendMessage(target, MessageType.INFO, "Teleporting " + player.getName() + " to you");
        new TeleportRunnable(player, target.getLocation());
        WormholeData.teleportRequestData.get(player).runnable.cancel();
        WormholeData.teleportRequestData.remove(player);
    }

    private void denyTeleportRequest(Player player, Player target) {
        WormholeData.teleportRequestData.remove(player);
        sendMessage(player, MessageType.INFO, target.getName() + " denied your teleport request");
        WormholeData.teleportRequestData.get(player).runnable.cancel();
        player.getWorld().dropItem(player.getLocation(), FunctionalItem.WORMHOLE_POTION.getItem());
    }

    private void requestTeleport(Player player, Player target) {
        WormholeRequestTimerRunnable runnable = new WormholeRequestTimerRunnable(player, target);
        sendMessage(player, MessageType.INFO, "Sent a teleport request to " + target.getName());
        sendMessage(target, MessageType.INFO, player.getName() + " has requested to teleport to you. Do '/wh [accept|deny] <player>' to accept or deny the request.");


        WormholeData.teleportRequestData.get(player).target = target;
        WormholeData.teleportRequestData.get(player).runnable = runnable;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> potential = new ArrayList<>();

        Player player;

        if(sender instanceof Player) {
            player = (Player) sender;
        } else {
            return new ArrayList<>();
        }

        if(args.length == 1) {
            potential = Arrays.asList("accept", "cancel", "deny", "request");
        } else if(args.length == 2) {
            potential = null;
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