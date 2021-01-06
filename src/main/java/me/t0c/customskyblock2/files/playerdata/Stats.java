package me.t0c.customskyblock2.files.playerdata;

import lombok.Data;

@Data
public class Stats {
    private int totalEvents = 0;
    private int eventsSurvived = 0;

    public int getEventsSurvived() {
        return eventsSurvived;
    }
    public void setEventsSurvived(int eventsSurvived) {
        this.eventsSurvived = eventsSurvived;
    }
    public int getTotalEvents() {
        return totalEvents;
    }
    public void setTotalEvents(int totalEvents) {
        this.totalEvents = totalEvents;
    }

    public void anotherEventSurvived() {
        eventsSurvived++;
        totalEvents++;
    }
    public void playerDied() {
        eventsSurvived = 0;
    }

    public String toString() {
        return "Stats: { totalEvents= " + totalEvents + " eventsSurvived= " + eventsSurvived + " }";
    }
}
