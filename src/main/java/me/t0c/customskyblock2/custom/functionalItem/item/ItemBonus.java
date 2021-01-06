package me.t0c.customskyblock2.custom.functionalItem.item;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.custom.functionalItem.CSBConsumableFunctionalItem;
import me.t0c.customskyblock2.files.playerdata.Bonuses;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Arrays;
import java.util.List;

public class ItemBonus extends CSBClass implements CSBConsumableFunctionalItem {

    @Override
    public boolean onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player p = event.getPlayer();
        Bonuses b = plugin.playerData().getData().get(p.getUniqueId().toString()).getBonuses();

        int amount = plugin.eventData().getItemBonusAmount();
        b.addBonusItems(amount);
        plugin.savePlayerData();
        p.sendMessage("Your random block bonus is now " + b.getBonusItems() + ".");
        return true;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList("Eat to temporarily increase the amount of ",
                             "items you get when receiving random items.");
    }

    @Override
    public Material getMaterial() {
        return Material.COOKIE;
    }

    @Override
    public String getName() {
        return "Item Bonus Upgrade";
    }

    @Override
    public boolean isShiny() {
        return true;
    }
}
