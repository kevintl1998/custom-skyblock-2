package me.t0c.customskyblock2.eventdistributer.event.events;

import me.t0c.customskyblock2.custom.functionalItem.FunctionalItem;
import me.t0c.customskyblock2.eventdistributer.event.Event;
import me.t0c.customskyblock2.files.eventdata.FuncItemData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GiveCustomItemEvent extends Event {

    @Override
    public int getWeight() {
        int retVal = 0;

        for(FuncItemData f : plugin.eventData().getData().values()) {
            retVal += f.getWeight();
        }

        return retVal;
    }

    @Override
    public void run(Player player) {
        int totalWeight = getWeight();
        int randWeight = plugin.random().nextInt(totalWeight);
        int weight = 0;

        if(FunctionalItem.getItemList() == null) {
            Bukkit.getLogger().warning("FunctionalItem.getItemList() is null");
            FunctionalItem.resetItemList();
        }

        for(FunctionalItem f : FunctionalItem.getItemList()) {
            weight += plugin.eventData().getData().get(f.name()).getWeight();
            if(randWeight < weight) {
                player.getInventory().addItem(f.getItem());
                return;
            }
        }
        plugin.getLogger().warning("A custom item could not be chosen for " + player.getName());
    }

    @Override
    public String name() {
        return "GiveCustomItemEvent";
    }
}
