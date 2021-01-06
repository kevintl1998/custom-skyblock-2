package me.t0c.customskyblock2.custom.functionalItem;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public interface BaseFunctionalItem {
    List<String> getDescription();
    Material getMaterial();
    String getName();
    boolean isShiny();
}
