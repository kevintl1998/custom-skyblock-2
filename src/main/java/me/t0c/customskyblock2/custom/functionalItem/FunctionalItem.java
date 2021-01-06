package me.t0c.customskyblock2.custom.functionalItem;

import me.t0c.customskyblock2.custom.BaseCustomType;
import me.t0c.customskyblock2.custom.functionalItem.item.*;
import me.t0c.customskyblock2.custom.functionalItem.item.wormholepotion.WormholePotion;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FunctionalItem implements BaseCustomType {

    MYSTERY_MOB_EGG(new MysteryMobEgg(), ItemType.USABLE),

    EMPTY_SPAWN_EGG(new EmptySpawnEgg(), ItemType.USABLE),

    BLOCK_DELETER(new BlockRemover(), ItemType.USABLE),

    ITEM_DUPER(new ItemDuper(), ItemType.USABLE),

    SOULBOUND_CATALYST(new SoulboundCatalyst(), ItemType.USABLE),

    CUSTOM_TRADER_SPAWNER(new CustomTraderSpawner(), ItemType.USABLE),

    //PLAYER_REMOVER(new PlayerRemover(), ItemType.USABLE),

    ITEM_BONUS(new ItemBonus(), ItemType.CONSUMABLE),

    EVENT_BONUS(new EventBonus(), ItemType.CONSUMABLE),

    RECALL_POTION(new RecallPotion(), ItemType.CONSUMABLE),

    WORMHOLE_POTION(new WormholePotion(), ItemType.CONSUMABLE)
    ;

    FunctionalItem(BaseFunctionalItem itemData, ItemType itemType) {
        this.itemData = itemData;
        this.itemType = itemType;
        item = new ItemStack(itemData.getMaterial(), 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + itemData.getName());
        if(itemData.isShiny()) {
            meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        for(String s : itemData.getDescription()) {
            s = ChatColor.DARK_PURPLE + s;
        }

        meta.setLore(itemData.getDescription());
        item.setItemMeta(meta);
    }

    private final BaseFunctionalItem itemData;
    private final ItemType itemType;
    private final ItemStack item;

    public BaseFunctionalItem getItemData() { return itemData; }
    public ItemType getItemType() { return itemType; }
    public ItemStack getItem() { return item; }

    private static final List<String> funcItemNames = initFunctionalItemNames();
    private static List<FunctionalItem> itemList = Arrays.asList(FunctionalItem.values());
    public static List<FunctionalItem> getItemList() { return itemList; }
    public static void resetItemList() { itemList = Arrays.asList(FunctionalItem.values()); }

    public static List<String> getFuncItemNames() { return funcItemNames; }

    public static boolean isFunctionalItem(ItemStack item) {
        if(item == null) return false;
        for(FunctionalItem i : itemList) {
            if (item.isSimilar(i.getItem())) return true;
        }
        return false;
    }

    public static FunctionalItem getValue(String name) {
        FunctionalItem f;
        try {
            f = valueOf(FunctionalItem.class, name);
        } catch (Exception ignored) {
            return null;
        }
        return f;
    }

    private static List<String> initFunctionalItemNames() {
        List<String> retVal = new ArrayList<>();
        for(FunctionalItem f : FunctionalItem.values()) {
            retVal.add(f.name());
        }
        return  retVal;
    }

    public enum ItemType {
        USABLE,
        CONSUMABLE
    }
}
