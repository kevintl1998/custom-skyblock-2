package me.t0c.customskyblock2.eventdistributer.event.events;

import me.t0c.customskyblock2.eventdistributer.event.Event;
import me.t0c.customskyblock2.data.Items;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GiveItemEvent extends Event {

    @Override
    public int getWeight() {
        return plugin.eventData().getNormalItemEventWeight();
    }

    @Override
    public void run(Player player) {
        int bonusItems = plugin.playerData().getData().get(player.getUniqueId().toString()).getBonuses().getBonusItems();
        Inventory i = player.getInventory();
        i.addItem(new ItemStack(Items.getRandomMaterialBiased(),1));
        if(bonusItems > 0) {
            i.addItem(new ItemStack(Items.getRandomMaterialBiased(),1));
            plugin.playerData().getData().get(player.getUniqueId().toString()).getBonuses().setBonusItems(bonusItems - 1);

        }
    }
}
