package me.t0c.customskyblock2.commands.bonusescommand;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.CustomSkyblock2;
import me.t0c.customskyblock2.files.playerdata.Bonuses;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BonusesCommand extends CSBClass implements CommandExecutor {

    public BonusesCommand(JavaPlugin plugin) {
        PluginCommand command = plugin.getCommand("bonuses");
        if(command != null) {
            command.setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            sendMessage(player, MessageType.INFO, message(player));
        }
        return true;
    }

    private String message(Player player) {
        StringBuilder s = new StringBuilder();
        Bonuses bonuses = CustomSkyblock2.instance().playerData().getData().get(player.getUniqueId().toString()).getBonuses();

        s.append("Your current bonuses");
        s.append('\n');
        s.append("Event: ");
        s.append(bonuses.getBonusEvents());
        s.append('\n');
        s.append("Item: ");
        s.append(bonuses.getBonusItems());


        return s.toString();
    }
}
