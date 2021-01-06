package me.t0c.customskyblock2.commands.csbcommand;

import me.t0c.customskyblock2.commands.Command;
import me.t0c.customskyblock2.custom.functionalItem.FunctionalItem;
import org.bukkit.entity.Player;

public class FuncItem extends Command {
    public FuncItem(Player player, String[] args) {
        super(player, args);
    }

    @Override
    public boolean runCommand() {
        if(args.length < 2) {
            sendMessage(player, MessageType.INFO, "Command usage: /csb funcItem get");
        } else {
            switch (args[1].toLowerCase()) { // /csb funcItem [get|set]
                case "get":
                    get();
                    break;
            }
        }
        return true;
    }

    private void get() {
        if(args.length < 3) {
            sendMessage(player, MessageType.INFO, "Command usage: /csb funcItem get <funcItem>");
        } else {
            FunctionalItem f = FunctionalItem.getValue(args[2]);
            if(f == null) {
                sendMessage(player, MessageType.INFO, "That is not a valid functional item");
            } else {
                player.getInventory().addItem(f.getItem());
            }
        }
    }
}
