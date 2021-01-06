package me.t0c.customskyblock2;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class CSBClass {

    public final CustomSkyblock2 plugin;

    public CSBClass() {
        this.plugin = CustomSkyblock2.instance();
    }

    //TODO:: implement this
    public void sendMessage(Player player, MessageType messageType, String message) {
        if(messageType.equals(MessageType.INFO)) {
            player.sendMessage(ChatColor.AQUA + "[CSB]: " + ChatColor.WHITE + message);
        } else if(messageType.equals(MessageType.WARNING)) {
            player.sendMessage(ChatColor.YELLOW + "[CSB]: " + ChatColor.WHITE + message);
        } else if(messageType.equals(MessageType.SUCCESS)) {
            player.sendMessage(ChatColor.GREEN + "[CSB]: " + ChatColor.WHITE + message);
        }
    }

    public static void message(Player player, MessageType messageType, String message) {
        if(messageType.equals(MessageType.INFO)) {
            player.sendMessage(ChatColor.AQUA + "[CSB]: " + ChatColor.WHITE + message);
        } else if(messageType.equals(MessageType.WARNING)) {
            player.sendMessage(ChatColor.YELLOW + "[CSB]: " + ChatColor.WHITE + message);
        } else if(messageType.equals(MessageType.SUCCESS)) {
            player.sendMessage(ChatColor.GREEN + "[CSB]: " + ChatColor.WHITE + message);
        }
    }

    public enum MessageType {
        INFO,
        WARNING,
        SUCCESS
    }
}
