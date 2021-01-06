package me.t0c.customskyblock2.files.playerdata;

import lombok.Data;
import me.t0c.customskyblock2.CustomSkyblock2;

@Data
public class Bonuses{

    public Bonuses() {

    }

    private Bonuses(int bonusItems, int bonusEvents) {
        this.bonusItems = bonusItems;
        this.bonusEvents = bonusEvents;
    }

    public static Bonuses newInstance(CreationReason creationReason) {
        if(creationReason.equals(CreationReason.DEATH) || creationReason.equals(CreationReason.PLUGIN)) {
            return new Bonuses();
        } else if(creationReason.equals(CreationReason.NEW_ISLAND)) {
            Bonuses b = new Bonuses();
            b.bonusItems = CustomSkyblock2.instance().config().getNewIslandItemBonus();
            b.bonusEvents = CustomSkyblock2.instance().config().getNewIslandEventBonus();
            return b;
        }
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
        DEATH,
        NEW_ISLAND,
        PLUGIN
    }

}
