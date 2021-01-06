package me.t0c.customskyblock2.files.config;

import lombok.Data;
import me.t0c.customskyblock2.CustomSkyblock2;

@Data
public class NextSpawn {
    private int x = 0;
    private int z = 0;
    private String direction = "s";
    private int dirCount = 1;
    private int maxDirCount = 1;

    public void updateNewSpawn() {
        // update x/z
        if(direction.toLowerCase().equals("s")) {
            setZ(z + CustomSkyblock2.instance().config().getIslandDistance());
        } else if(direction.toLowerCase().equals("w")) {
            setX(x - CustomSkyblock2.instance().config().getIslandDistance());
        } else if(direction.toLowerCase().equals("n")) {
            setZ(z - CustomSkyblock2.instance().config().getIslandDistance());
        } else if(direction.toLowerCase().equals("e")) {
            setX(x + CustomSkyblock2.instance().config().getIslandDistance());
        } else {
            CustomSkyblock2.instance().getLogger().warning("Direction in config is messed up");
        }

        //update dirCount
        setDirCount(dirCount + 1);

        //update maxDirCount/direction if necessary
        if(dirCount >= maxDirCount) {
            dirCount = 0;
            if(direction.toLowerCase().equals("s")) {
                setDirection("w");
            } else if(direction.toLowerCase().equals("w")) {
                setDirection("n");
                setMaxDirCount(maxDirCount + 1);
            } else if(direction.toLowerCase().equals("n")) {
                setDirection("e");
            } else if(direction.toLowerCase().equals("e")) {
                setDirection("s");
                setMaxDirCount(maxDirCount + 1);
            } else {
                setDirection("s");
                setMaxDirCount(maxDirCount + 1);
            }
        }
    }
}
