package me.t0c.customskyblock2.files.eventdata;

import lombok.Data;
import me.t0c.customskyblock2.custom.functionalItem.FunctionalItem;

import java.util.HashMap;
import java.util.Map;

@Data
public class EventData {

    private Map<String, FuncItemData> data = new HashMap<>();

    private int normalItemEventWeight = 700;

    private boolean isNormalItemEventEnabled = true;

    private int spawnMobEventWeight = 5;

    private boolean isSpawnMobEventEnabled = true;

    private int itemBonusAmount = 3;

    private int eventBonusAmount = 3;

    private boolean enableExplosiveMobsInSpawnMobEvent = false;

}
