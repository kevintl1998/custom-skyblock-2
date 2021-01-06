package me.t0c.customskyblock2.files.playerdata;

import lombok.Data;

@Data
public class Bonuses {

    public Bonuses() {

    }

    private Bonuses(int bonusItems, int bonusEvents) {
        this.bonusItems = bonusItems;
        this.bonusEvents = bonusEvents;
    }

    public static Bonuses newInstance(CreationReason creationReason) {
        return new Bonuses();
    }

    public void addBonusItems(int amount) { bonusItems = bonusItems + amount; }
    public void addBonusEvents(int amount) { bonusEvents = bonusEvents + amount; }

    private int bonusItems = 0;
    private int bonusEvents = 0;

    public String toString() {
        return "Bonuses: { bonusItems= " + bonusItems + " bonusEvents= " + bonusEvents + " }";
    }

    public enum CreationReason {
        PLUGIN
    }

}
