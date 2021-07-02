package me.t0c.customskyblock2.data;

import me.t0c.customskyblock2.CustomSkyblock2;
import me.t0c.customskyblock2.Dong;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Entities {
    private Entities() {  }

    public static final List<EntityType> illegalEntities = initIllegalEntities();
    public static final List<EntityType> livingEntities = initLivingEntities();
    public static final List<EntityType> hostileEntities = initHostileEntities();
    public static final List<EntityType> explosiveMobs = initExplosiveMobs();

    public static EntityType getRandomLivingEntity() {
        EntityType e = livingEntities.get(new Random().nextInt(livingEntities.size()));
        if(illegalEntities.contains(e)) return getRandomLivingEntity();
        else return e;
    }
    public static EntityType getRandomHostileEntity() { return hostileEntities.get(CustomSkyblock2.instance().random().nextInt(hostileEntities.size())); }

    public static Player getRandomPlayerExcluding(Player player) {
        if(Bukkit.getServer().getOnlinePlayers().size() < 2) return null;
        Player p = getRandomPlayer();
        if(p.equals(player)) {
            return getRandomPlayerExcluding(player);
        }
        return p;
    }
    public static Player getRandomPlayer() {
        ArrayList<Player> p = new ArrayList<>(Bukkit.getOnlinePlayers());
        if(p.size() == 0) return null;
        else if(p.size() == 1) return p.get(0);
        else return p.get(new Random().nextInt(p.size()));
    }

    public static List<EntityType> initIllegalEntities() {
        return Arrays.asList(
                EntityType.ENDER_DRAGON,
                EntityType.WITHER,
                EntityType.PLAYER);
    }
    public static List<EntityType> initLivingEntities() {
        List<EntityType> entityList = new ArrayList<>();
        for(EntityType e : EntityType.values()) {
            if(e.isAlive()) entityList.add(e);
        }
        return entityList;
    }
    public static List<EntityType> initHostileEntities() {
        return Arrays.asList(EntityType.BLAZE,
                EntityType.CREEPER,
                EntityType.DROWNED,
                EntityType.ELDER_GUARDIAN,
                EntityType.ENDERMITE,
                EntityType.EVOKER,
                EntityType.GHAST,
                EntityType.GUARDIAN,
                EntityType.HUSK,
                EntityType.MAGMA_CUBE,
                EntityType.PHANTOM,
                EntityType.PILLAGER,
                EntityType.RAVAGER,
                EntityType.SHULKER,
                EntityType.SILVERFISH,
                EntityType.SKELETON,
                EntityType.SLIME,
                EntityType.SPIDER,
                EntityType.CAVE_SPIDER,
                EntityType.STRAY,
                EntityType.VEX,
                EntityType.VINDICATOR,
                EntityType.WITCH,
                EntityType.WITHER_SKELETON,
                EntityType.ZOMBIE,
                EntityType.ZOMBIE_VILLAGER,
                EntityType.ZOMBIFIED_PIGLIN,
                EntityType.ZOGLIN,
                EntityType.HOGLIN,
                EntityType.ILLUSIONER);
    }
    public static List<EntityType> initExplosiveMobs() {
        return Arrays.asList(EntityType.CREEPER,
                EntityType.GHAST);
    }
}
