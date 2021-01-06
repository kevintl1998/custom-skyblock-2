package me.t0c.customskyblock2.files.playerdata;

import lombok.Data;
import me.t0c.customskyblock2.CustomSkyblock2;
import org.bukkit.Location;
import org.bukkit.World;

@Data
public class RespawnLocation {
    public RespawnLocation() {  }
    public RespawnLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    private double x;
    private double y;
    private double z;

    public void copyLocationFrom(RespawnLocation respawnLocation) {
        this.x = respawnLocation.x;
        this.y = respawnLocation.y;
        this.z = respawnLocation.z;
    }

    public static boolean isRespawnLocation(Location l) {
        for(me.t0c.customskyblock2.files.playerdata.Data d : CustomSkyblock2.instance().playerData().getData().values()) {
            if ((int) d.getRespawnLocation().x == l.getBlockX() && (int) d.getRespawnLocation().y == l.getBlockY() && (int) d.getRespawnLocation().z == l.getBlockZ()) {
                return true;
            }
        }
        return false;
    }

    public Location asLocation() { return asLocation(CustomSkyblock2.instance().overworld()); }

    public Location asLocation(World world) {
        return new Location(world, x, y, z);
    }

    public String toString() {
        return "RespawnLocation: { x=" + (int)x + ", y=" + (int)y + ", z=" + (int)z + " }";
    }
}
