package me.t0c.customskyblock2;

import me.t0c.customskyblock2.commands.bonusescommand.BonusesCommand;
import me.t0c.customskyblock2.commands.csbcommand.CSBCommand;
import me.t0c.customskyblock2.commands.joinislandcommand.JoinIslandCommand;
import me.t0c.customskyblock2.commands.newislandcommand.NewIslandCommand;
import me.t0c.customskyblock2.custom.functionalItem.FunctionalItem;
import me.t0c.customskyblock2.custom.functionalItem.item.wormholepotion.WormholeCommand;
import me.t0c.customskyblock2.custom.functionalItem.item.wormholepotion.WormholeListener;
import me.t0c.customskyblock2.files.eventdata.EventData;
import me.t0c.customskyblock2.files.eventdata.FuncItemData;
import me.t0c.customskyblock2.miscgamemodifiers.*;
import me.t0c.customskyblock2.eventdistributer.event.events.GiveCustomItemEvent;
import me.t0c.customskyblock2.eventdistributer.event.events.GiveItemEvent;
import me.t0c.customskyblock2.eventdistributer.event.events.SpawnMobEvent;
import me.t0c.customskyblock2.files.handler.ConfigHandler;
import me.t0c.customskyblock2.files.config.Config;
import me.t0c.customskyblock2.files.playerdata.PlayerData;
import me.t0c.customskyblock2.custom.CustomUtilityManager;
import me.t0c.customskyblock2.eventdistributer.EventManager;
import me.t0c.customskyblock2.spawning.listeners.PlayerDeathListener;
import me.t0c.customskyblock2.spawning.listeners.PlayerJoinListener;
import me.t0c.customskyblock2.spawning.listeners.PlayerRespawnListener;
import me.t0c.customskyblock2.spawning.listeners.PlayerUseElytra;
import me.t0c.customskyblock2.worldGen.VoidWorldGenerator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Random;

public final class CustomSkyblock2 extends JavaPlugin implements Listener {

    private Config config = new Config();
    //private WorldData worldData = new WorldData();
    private PlayerData playerData = new PlayerData();
    private EventData eventData = new EventData();

    private final Random random = new Random();

    //Change these names to represent what the worlds actually do.
    private World overworld;
    private World nether;
    private World end;

    private static CustomSkyblock2 instance;

    private static CustomUtilityManager customUtilityManager;
    private static EventManager eventManager;
    private static BukkitTask eventTaskTimer;

    @Override
    public void onEnable() {

        //configs
        config = new ConfigHandler(this).getConfig(Config.class);
        //worldData = new ConfigHandler(this).getConfig(WorldData.class);
        playerData = new ConfigHandler(this).getConfig(PlayerData.class);
        eventData = new ConfigHandler(this).getConfig(EventData.class);

        //Generate event data:
        if(eventData == null) {
            generateEventDataDefault();
            getLogger().warning("Could not get eventData.yml");
            getLogger().warning("Generating new eventData.yml...");
        }

        //init variables
        instance = this;
        customUtilityManager = CustomUtilityManager.getInstance();
        eventManager = EventManager.getInstance();

        //commands
        initCommands();

        //listeners
        initBukkitListeners();

        //events
        initEvents();
        startEventTimer();
    }

    @Override
    public void onDisable() {
        savePlayerData();
        saveConfig();
        WormholeListener.onServerClose();
    }

    public void reloadEventData() {
        eventData = new ConfigHandler(this).getConfig(EventData.class);
    }

    /* BUKKIT STUFF */

    private void initCommands() {
        new CSBCommand(this);
        new NewIslandCommand(this);
        new BonusesCommand(this);
        new WormholeCommand();
        new JoinIslandCommand();
    }

    private void initBukkitListeners() {
        Bukkit.getPluginManager().registerEvents(this,this);
        new DisableNetherPortals();
        new DupeMobSpawns();
        new EntityBreedRandomizer();
        new PlayerDeathListener();
        new PlayerJoinListener();
        new PlayerRespawnListener();
        new WormholeListener();
        new PlayerUseElytra();
    }

    /* CUSTOM EVENT STUFF */

    private void startEventTimer() {
        eventTaskTimer = Bukkit.getScheduler().runTaskTimer(this, eventManager::event, (this.config().getEventDelay() / 2), this.config().getEventDelay());
    }
    public void restartEventTimer() {
        Bukkit.getScheduler().cancelTask(eventTaskTimer.getTaskId());
        Bukkit.getScheduler().runTaskTimer(this, eventManager::event, (this.config().getEventDelay() / 2), this.config().getEventDelay());
    }

    private void initEvents() {
        eventManager.registerEvent(new GiveItemEvent());
        eventManager.registerEvent(new SpawnMobEvent());
        eventManager.registerEvent(new GiveCustomItemEvent());
    }

    private void generateEventDataDefault() {
        eventData = new EventData();
        eventData.setData(new HashMap<>());

        for(FunctionalItem f : FunctionalItem.getItemList()) {
            eventData.getData().put(f.name(), new FuncItemData());
        }
        saveEventData();
    }

    /* GETTERS */

    public Config config() { return config; }
    public PlayerData playerData() { return playerData; }
    public EventData eventData() { return eventData; }

    public World overworld() { return overworld; }
    public World nether() { return nether; }
    public World end() { return end; }

    public static CustomSkyblock2 instance() { return instance; }

    public Random random() { return random; }

    public void saveConfig() { new ConfigHandler(this).saveConfig(config); }
    public void savePlayerData() {
        new ConfigHandler(this).saveConfig(playerData);
    }
    public void saveEventData() { new ConfigHandler(this).saveConfig(eventData); }

    /* CHUNK GENERATOR */

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        if(id.equals("voidWorld")) {
            return new VoidWorldGenerator();
        }
        return null;
    }

    /* ERROR MESSAGES */

    private String worldGetErrorMessage() {
        StringBuilder s = new StringBuilder();

        s.append("Failed to get the world(s):\n");
        if(overworld == null) {
            s.append("- overworld\n");
        }
        if(nether == null) {
            s.append("- nether\n");
        }
        if(end == null) {
            s.append("- end\n");
        }
        s.append("Disabling plugin...");

        return s.toString();
    }

    /* LISTENERS */

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        overworld = Bukkit.getWorld(config.getOverworld());
        nether = Bukkit.getWorld(config.getNether());
        end = Bukkit.getWorld(config.getEnd());
        if(overworld == null || nether == null || end == null) {
            Bukkit.getLogger().warning(worldGetErrorMessage());
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        } else {
            Bukkit.getLogger().info("*******************************");
            Bukkit.getLogger().info("----- Loaded Successfully -----");
            Bukkit.getLogger().info("*******************************");
        }
    }

}