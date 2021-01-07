package me.t0c.customskyblock2.commands.joinislandcommand;

import me.t0c.customskyblock2.commands.Command;
import me.t0c.customskyblock2.files.playerdata.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class JoinIsland extends Command implements Listener {

    //TODO:: remove player if they leave the game

    // map of requester to requested
    protected static final Map<Player, Player> joinIslandData = new HashMap<>();

    protected JoinIsland(Player player, String[] args) {
        super(player, args);
    }


    // /joinIsland [request|accept|deny|cancel] <player>
    @Override
    public boolean runCommand() {
        if(args.length == 2) {
            Player target;
            if((target = Bukkit.getPlayer(args[1])) != null) {
                if(!player.equals(target)) {
                    switch (args[0].toLowerCase()) {
                        case "request":
                            request(target);
                            break;
                        case "accept":
                            accept(target);
                            break;
                        case "deny":
                            deny(target);
                    }
                } else {
                    sendMessage(player, MessageType.INFO, "Cannot send a request to yourself.");
                }
            } else {
                sendMessage(player, MessageType.INFO, args[1] + " is not online or does not exist");
            }
        } else if(args.length == 1) {
            if(args[0].toLowerCase().equals("cancel")) {
                cancel();
            } else {
                sendMessage(player, MessageType.INFO, "Command syntax: /joinIsland [request|accept|deny|cancel] <player>");
            }
        } else {
            sendMessage(player, MessageType.INFO, "Command syntax: /joinIsland [request|accept|deny|cancel] <player>");
        }
        return true;
    }

    private void accept(Player target) {
        if(joinIslandData.containsKey(target)) {
            sendMessage(player, MessageType.INFO, "Accepted " + target.getName() + "'s request.");
            sendMessage(target, MessageType.INFO, player.getName() + " accepted your request. Kill yourself or use a wormhole potion to teleport to their island");

            Data playerData = plugin.playerData().getData().get(player.getUniqueId().toString());
            Data targetData = plugin.playerData().getData().get(target.getUniqueId().toString());
            targetData.getRespawnLocation().copyLocationFrom(playerData.getRespawnLocation());

            joinIslandData.remove(target);
        } else {
            sendMessage(player, MessageType.INFO, "You don't have a request from that person.");
        }
    }

    private void deny(Player target) {
        if(joinIslandData.containsKey(target)) {
            sendMessage(player, MessageType.INFO, "Denied " + target.getName() + "'s request.");
            sendMessage(target, MessageType.INFO, player.getName() + " denied your request.");
            joinIslandData.remove(target);
        } else {
            sendMessage(player, MessageType.INFO, "You don't have a request from that person.");

        }
    }

    private void request(Player target) {
        if(!joinIslandData.containsKey(player)) {
            sendMessage(player, MessageType.INFO, "requested to join " + target.getName() + "'s island.");
            sendMessage(target, MessageType.INFO, player.getName() + " has requested to join your island.");

            joinIslandData.put(player, target);
        } else {
            sendMessage(player, MessageType.INFO, "You can only request to join one person's island at a time.");
        }
    }

    private void cancel() {
        if(joinIslandData.containsKey(player)) {
            sendMessage(player, MessageType.INFO, "Cancelled the request to " + joinIslandData.get(player).getName()+ ".");
            joinIslandData.remove(player);
        } else {
            sendMessage(player, MessageType.INFO, "You do not have any island join requests.");
        }
    }

    public static boolean containsTarget(Player target) {
        if(target == null) return false;
        for(Player player : joinIslandData.values()) {
            if(target.equals(player)) {
                return true;
            }
        }
        return false;
    }

}
