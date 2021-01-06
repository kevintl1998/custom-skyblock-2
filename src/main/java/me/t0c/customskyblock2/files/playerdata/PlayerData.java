package me.t0c.customskyblock2.files.playerdata;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PlayerData {
    private String playerData = "player data";

    // UUID in string form to data
    private Map<String, me.t0c.customskyblock2.files.playerdata.Data> data = new HashMap<>();

    public String toString() {
        StringBuilder s = new StringBuilder();

        for(Map.Entry<String, me.t0c.customskyblock2.files.playerdata.Data> map : data.entrySet()) {
            s.append(map.getKey()).append(" ");
            s.append(map.getValue().toString()).append(" | ");
        }

        return s.toString();
    }
}
