package me.t0c.customskyblock2.spawning;

import me.t0c.customskyblock2.CSBClass;
import me.t0c.customskyblock2.CustomSkyblock2;
import me.t0c.customskyblock2.custom.functionalItem.FunctionalItem;
import me.t0c.customskyblock2.data.Blocks;
import me.t0c.customskyblock2.files.config.NextSpawn;
import me.t0c.customskyblock2.files.playerdata.RespawnLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Random;

public class NewIslandFactory extends CSBClass {

    private final Player player;
    private final boolean hasPlayedBefore;
    private final Location location;
    private final Material startBlock;

    public NewIslandFactory(Player player, boolean hasPlayedBefore) {
        this.player = player;
        this.hasPlayedBefore = hasPlayedBefore;
        NextSpawn nextSpawn = plugin.config().getNextSpawn();
        this.location = new Location(plugin.overworld(), nextSpawn.getX(), plugin.config().getIslandGenHeight(),nextSpawn.getZ());
        this.startBlock = Material.BEDROCK;
        generateNewIsland();
        sendPlayerToNewIsland();
    }

    private void generateNewIsland() {
        Bukkit.getLogger().info("generated island at: " + location.toString());
        location.getBlock().setType(startBlock);
    }

    private void sendPlayerToNewIsland() {
        Bukkit.getLogger().info("Sent player to new island");

        location.setY(location.getY() + 1);
        RespawnLocation r = new RespawnLocation(location.getX(), location.getY(), location.getZ());

        player.teleport(location.add(0.5f, 0, 0.5f));

        player.setHealth(20f);
        player.setFoodLevel(20);
        player.setExhaustion(0);
        player.setSaturation(10);


        if(plugin.config().isGiveWormholeOnNewSpawn()) {
            player.getInventory().addItem(FunctionalItem.WORMHOLE_POTION.getItem());
        }

        plugin.playerData().getData().get(player.getUniqueId().toString()).setRespawnLocation(r);

        plugin.config().getNextSpawn().updateNewSpawn();
    }

    private void populateAreaAroundIsland(Location l) {
        int minDis = 3;
        int maxDis = hasPlayedBefore ? 20 : 10;
        int blockCountMin = hasPlayedBefore ? 20 : 10;
        int blockCountMax = hasPlayedBefore ? 40 : 15;

        if(minDis > maxDis) {
            int temp = minDis;
            minDis = maxDis;
            maxDis = temp;
        }
        if(blockCountMin > blockCountMax) {
            int temp = blockCountMin;
            blockCountMin = blockCountMax;
            blockCountMax = temp;
        }

        int blockCount = randomNumber(blockCountMin, blockCountMax);
        for(int i = 0; i < blockCount; i++) {
            int x = randomNumber(minDis, maxDis);
            int y = randomNumber(minDis, maxDis);
            int z = randomNumber(minDis, maxDis);
            boolean negX = new Random().nextBoolean();
            boolean negY = new Random().nextBoolean();
            boolean negZ = new Random().nextBoolean();
            if(negX) x = -x;
            if(negY) y = -y;
            if(negZ) z = -z;
            l.setX(l.getX() + x);
            l.setY(l.getY() + y);
            l.setZ(l.getZ() + z);
            l.getBlock().setType(Blocks.getRandomBlockBiased());
        }
    }

    public static int randomNumber(int min, int max) {
        if(min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        return CustomSkyblock2.instance().random().nextInt(max - min) + min;
    }
}