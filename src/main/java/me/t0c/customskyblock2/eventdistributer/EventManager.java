package me.t0c.customskyblock2.eventdistributer;

import me.t0c.customskyblock2.Dong;
import me.t0c.customskyblock2.eventdistributer.event.Event;
import me.t0c.customskyblock2.files.playerdata.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EventManager extends Dong {

    private EventManager() {  }

    private static final EventManager eventManager = new EventManager();

    public static EventManager getInstance() {
        return eventManager;
    }

    private final List<Event> events = new ArrayList<>();

    public void registerEvent(Event e) {
        if(e.getWeight() <= 0) {
            throw new IllegalArgumentException("Event weight must be greater than 0.");
        } else { events.add(e);}
    }

    public void event() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            Data data = plugin.playerData().getData().get(p.getUniqueId().toString());
            int bonusEvents = data.getBonuses().getBonusEvents();
            if(p.getHealth() > 0) {
                getRandomEvent().run(p);
                if(bonusEvents > 0) {
                    getRandomEvent().run(p);
                    plugin.playerData().getData().get(p.getUniqueId().toString()).getBonuses().setBonusEvents(bonusEvents - 1);
                }
            }
            data.getStats().anotherEventSurvived();
        }
    }

    private Event getRandomEvent() {
        int totalWeight = getTotalWeight();
        int randWeight = plugin.random().nextInt(totalWeight);
        int weight = 0;
        for(Event e : events) {
            weight += e.getWeight();
            if(randWeight < weight) {
                return e;
            }
        }
        return null;
    }
    private int getTotalWeight() {
        int totalWeight = 0;
        for(Event e : events) {
            totalWeight += e.getWeight();
        }
        return totalWeight;
    }

}
