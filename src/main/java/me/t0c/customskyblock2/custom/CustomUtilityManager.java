package me.t0c.customskyblock2.custom;

import me.t0c.customskyblock2.Dong;
import me.t0c.customskyblock2.custom.functionalItem.CSBConsumableFunctionalItem;
import me.t0c.customskyblock2.custom.functionalItem.CSBUsableFunctionalItem;
import me.t0c.customskyblock2.custom.functionalItem.FunctionalItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.*;

import java.util.*;

public class CustomUtilityManager extends Dong implements Listener {

    private CustomUtilityManager() {
        initFunctionalItems();
        registerToListener(this);
    }

    private static CustomUtilityManager customUtilityManager = new CustomUtilityManager();

    public static CustomUtilityManager getInstance() {
        return customUtilityManager;
    }

    private List<FunctionalItem> consumableItems = new ArrayList<>();
    private List<FunctionalItem> usableItems = new ArrayList<>();

    /* for custom functional items */

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        if (event.getItem() != null && !event.getItem().getType().equals(Material.AIR)) {
            if (event.getHand().equals(EquipmentSlot.HAND)) {
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (!event.getClickedBlock().getType().isInteractable() || event.getPlayer().isSneaking()))) {
                    ItemStack i = event.getPlayer().getEquipment().getItemInMainHand();
                    if (isUsableItem(i)) {
                        if (executeBlockInteractFunction(event)) {
                            i.setAmount(i.getAmount() - 1);
                        }
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    private boolean executeBlockInteractFunction(PlayerInteractEvent event) {
        ItemStack item = event.getPlayer().getEquipment().getItemInMainHand();
        for(FunctionalItem fItem : usableItems) {
            if(item.isSimilar(fItem.getItem())) {
                return ((CSBUsableFunctionalItem)fItem.getItemData()).onBlockInteract(event);
            }
        }
        return false;
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        if(event.getHand().equals(EquipmentSlot.HAND)) {
            ItemStack i = event.getPlayer().getEquipment().getItemInMainHand();
            if(isUsableItem(i)) {
                if(executeEntityInteractFunction(event)) {
                    i.setAmount(i.getAmount() - 1);
                }
                event.setCancelled(true);
            }
        }
    }
    private boolean executeEntityInteractFunction(PlayerInteractEntityEvent event) {
        ItemStack item = event.getPlayer().getEquipment().getItemInMainHand();
        for(FunctionalItem fItem : usableItems) {
            if(item.isSimilar(fItem.getItem())) {
                return ((CSBUsableFunctionalItem)fItem.getItemData()).onEntityInteract(event);
            }
        }
        return false;
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event) {
        // event.getItem() does not return the address of the item that the player is actually consuming
        ItemStack i = event.getItem();
        if(isConsumableItem(i)) {
            PlayerInventory playerInv = event.getPlayer().getInventory();
            ItemStack itemInPlayerInventory = ((playerInv.getItemInMainHand().getType().equals(Material.AIR)) ? playerInv.getItemInOffHand() : playerInv.getItemInMainHand());
            if(executeItemConsumeFunction(event)) {
                itemInPlayerInventory.setAmount(itemInPlayerInventory.getAmount() - 1);
            }
            event.setCancelled(true);
        }
    }
    private boolean executeItemConsumeFunction(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        for(FunctionalItem fItem : consumableItems) {
            if(item.isSimilar(fItem.getItem())) {
                return ((CSBConsumableFunctionalItem)fItem.getItemData()).onPlayerItemConsume(event);
            }
        }
        return false;
    }

    public void initFunctionalItems() {

        for(FunctionalItem item : FunctionalItem.getItemList()) {
            if(item.getItemType().equals(FunctionalItem.ItemType.USABLE)) {
                usableItems.add(item);
            } else {
                consumableItems.add(item);
            }
        }
    }

    public boolean isUsableItem(ItemStack item) {
         if(item == null) return false;
         for(FunctionalItem i : usableItems) {
             if(item.isSimilar(i.getItem())) return true;
         }
         return false;
    }

    public boolean isConsumableItem(ItemStack item) {
        if(item == null) return false;
        for(FunctionalItem i : consumableItems) {
            if(item.isSimilar(i.getItem())) return true;
        }
        return false;
    }

}