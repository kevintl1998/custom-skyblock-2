package me.t0c.customskyblock2.runnables;

import me.t0c.customskyblock2.CustomSkyblock2;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateItemStackLater extends BukkitRunnable {

    private final ItemStack[] items;

    public UpdateItemStackLater(int delay, ItemStack... items) {
        this.items = items;
        runTaskLater(CustomSkyblock2.instance(), delay);
    }

    @Override
    public void run() {
        for(ItemStack i : items) {
            if(i != null) {
                System.out.println(i.getAmount());
                i.setAmount(i.getAmount() - 1);
                System.out.println(i.getAmount());
            }
        }
    }
}
