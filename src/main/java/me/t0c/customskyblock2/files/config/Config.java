package me.t0c.customskyblock2.files.config;

import lombok.Data;

@Data
public class Config {
    private int entitySpawnDupes = 3;
    private boolean dupeHostilesOnly = true;
    private boolean logItemsReceivedInConsole = false;
    private int eventDelay = 1200;
    private long newIslandCommandDelay = 36000;
    private int islandDistance = 1250;
    private int islandGenHeight = 100;
    private boolean enableBedSpawn = true;
    private boolean giveWormholeOnNewSpawn = true;
    private int newIslandItemBonus = 10;
    private int newIslandEventBonus = 5;
    private NextSpawn nextSpawn = new NextSpawn();
    private String overworld = "world";
    private String nether = "world_nether";
    private String end = "world_the_end";
}
