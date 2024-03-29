package me.t0c.customskyblock2.data;

import me.t0c.customskyblock2.CustomSkyblock2;
import me.t0c.customskyblock2.Dong;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Items {

    public static List<Material> rareItems = initRareItems();
    public static List<Material> mobDrops = initMobDrops();
    public static List<Material> endMaterials = initEndMaterials();
    public static List<Material> netherMaterials = initNetherMaterials();
    public static List<Material> spawnEggs = initSpawnEggs();
    public static List<Material> illegalItems = initIllegalItems();

    public static Material getRandomMaterialBiased() {
        Material m = getRandomMaterial();
        if(rareItems.contains(m)) return getRandomMaterial();
        return m;
    }
    public static Material getRandomMaterial() {
        Material m = Material.values()[CustomSkyblock2.instance().random().nextInt(Material.values().length)];
        if(Blocks.airBlocks.contains(m) || illegalItems.contains(m)) return getRandomMaterial();
        else return m;
    }

    private static List<Material> initRareItems() {
        List<Material> l = Arrays.asList(Material.ENDER_CHEST,
                Material.SHULKER_BOX,
                Material.BLACK_SHULKER_BOX,
                Material.BLUE_SHULKER_BOX,
                Material.BROWN_SHULKER_BOX,
                Material.CYAN_SHULKER_BOX,
                Material.GRAY_SHULKER_BOX,
                Material.GREEN_SHULKER_BOX,
                Material.LIGHT_BLUE_SHULKER_BOX,
                Material.LIGHT_GRAY_SHULKER_BOX,
                Material.LIME_SHULKER_BOX,
                Material.MAGENTA_SHULKER_BOX,
                Material.ORANGE_SHULKER_BOX,
                Material.PINK_SHULKER_BOX,
                Material.PURPLE_SHULKER_BOX,
                Material.RED_SHULKER_BOX,
                Material.WHITE_SHULKER_BOX,
                Material.YELLOW_SHULKER_BOX,
                Material.COMMAND_BLOCK,
                Material.REPEATING_COMMAND_BLOCK,
                Material.CHAIN_COMMAND_BLOCK,
                Material.BARRIER,
                Material.JIGSAW,
                Material.STRUCTURE_BLOCK,
                Material.GILDED_BLACKSTONE,
                Material.NETHERITE_INGOT,
                Material.NETHERITE_BLOCK,
                Material.ANCIENT_DEBRIS,
                Material.NETHERITE_BOOTS,
                Material.NETHERITE_CHESTPLATE,
                Material.NETHERITE_LEGGINGS,
                Material.NETHERITE_HELMET,
                Material.NETHERITE_SWORD,
                Material.NETHERITE_AXE,
                Material.NETHERITE_PICKAXE,
                Material.NETHERITE_HOE,
                Material.NETHERITE_SHOVEL,
                Material.NETHER_STAR,
                Material.POINTED_DRIPSTONE,
                Material.BUDDING_AMETHYST);
        l.addAll(initSpawnEggs());
        return l;
    }
    private static List<Material> initMobDrops() {
        return Arrays.asList(Material.BLAZE_ROD,
                Material.STRING,
                Material.SPIDER_EYE,
                Material.GUNPOWDER,
                Material.ROTTEN_FLESH,
                Material.SHULKER_SHELL,
                Material.MAGMA_CREAM,
                Material.PRISMARINE_SHARD,
                Material.PRISMARINE_CRYSTALS,
                Material.ENDER_PEARL,
                Material.COD,
                Material.PORKCHOP,
                Material.LEATHER,
                Material.PHANTOM_MEMBRANE,
                Material.ARROW,
                Material.BONE,
                Material.SLIME_BALL,
                Material.EMERALD,
                Material.GLASS_BOTTLE,
                Material.GLOWSTONE_DUST,
                Material.REDSTONE,
                Material.STICK,
                Material.SUGAR,
                Material.COAL,
                Material.GOLD_NUGGET);
    }
    private static List<Material> initEndMaterials() {
        return Arrays.asList(Material.OBSIDIAN,
                Material.END_STONE,
                Material.CHORUS_FRUIT,
                Material.CHORUS_FLOWER,
                Material.DRAGON_EGG,
                Material.DRAGON_BREATH,
                Material.DRAGON_HEAD,
                Material.ELYTRA,
                Material.END_CRYSTAL,
                Material.END_ROD,
                Material.PURPUR_BLOCK,
                Material.PURPUR_PILLAR,
                Material.PURPUR_SLAB,
                Material.PURPUR_STAIRS,
                Material.END_STONE_BRICKS,
                Material.END_STONE_BRICK_SLAB,
                Material.END_STONE_BRICK_STAIRS,
                Material.END_STONE_BRICK_WALL,
                Material.ENDER_PEARL,
                Material.SHULKER_SHELL);
    }
    private static List<Material> initNetherMaterials() {
        return Arrays.asList(Material.GRAVEL,
                Material.NETHERRACK,
                Material.BASALT,
                Material.POLISHED_BASALT,
                Material.BLACKSTONE,
                Material.BLACKSTONE_STAIRS,
                Material.BLACKSTONE_SLAB,
                Material.BLACKSTONE_STAIRS,
                Material.BLACKSTONE_WALL,
                Material.POLISHED_BLACKSTONE,
                Material.POLISHED_BLACKSTONE_STAIRS,
                Material.POLISHED_BLACKSTONE_BUTTON,
                Material.POLISHED_BLACKSTONE_WALL,
                Material.POLISHED_BLACKSTONE_SLAB,
                Material.POLISHED_BLACKSTONE_PRESSURE_PLATE,
                Material.CHISELED_POLISHED_BLACKSTONE,
                Material.POLISHED_BLACKSTONE_BRICKS,
                Material.POLISHED_BLACKSTONE_BRICK_SLAB,
                Material.POLISHED_BLACKSTONE_BRICK_STAIRS,
                Material.POLISHED_BLACKSTONE_BRICK_WALL,
                Material.CRACKED_POLISHED_BLACKSTONE_BRICKS,
                Material.GILDED_BLACKSTONE,
                Material.GLOWSTONE,
                Material.BLAZE_ROD,
                Material.GOLD_INGOT,
                Material.GOLD_NUGGET,
                Material.ROTTEN_FLESH,
                Material.SOUL_LANTERN,
                Material.SOUL_SAND,
                Material.SOUL_SOIL,
                Material.PORKCHOP,
                Material.STRING,
                Material.BONE,
                Material.ARROW,
                Material.ENDER_PEARL,
                Material.GHAST_TEAR,
                Material.GUNPOWDER,
                Material.MAGMA_CREAM,
                Material.COAL,
                Material.NETHER_QUARTZ_ORE,
                Material.NETHER_GOLD_ORE,
                Material.BROWN_MUSHROOM,
                Material.RED_MUSHROOM,
                Material.MAGMA_BLOCK,
                Material.ANCIENT_DEBRIS,
                Material.BONE_BLOCK,
                Material.WARPED_FUNGUS,
                Material.CRIMSON_FUNGUS,
                Material.SHROOMLIGHT,
                Material.WARPED_STEM,
                Material.CRIMSON_STEM,
                Material.WARPED_NYLIUM,
                Material.CRIMSON_NYLIUM,
                Material.TWISTING_VINES,
                Material.WEEPING_VINES,
                Material.WARPED_ROOTS,
                Material.CRIMSON_ROOTS,
                Material.WARPED_WART_BLOCK,
                Material.NETHER_WART_BLOCK,
                Material.NETHER_SPROUTS,
                Material.NETHER_WART,
                Material.NETHER_BRICK,
                Material.NETHER_BRICKS,
                Material.NETHER_BRICK_FENCE,
                Material.NETHER_BRICK_SLAB,
                Material.NETHER_BRICK_STAIRS,
                Material.NETHER_BRICK_WALL,
                Material.CHAIN,
                Material.LANTERN,
                Material.QUARTZ,
                Material.QUARTZ_BLOCK,
                Material.SMOOTH_QUARTZ,
                Material.CRYING_OBSIDIAN,
                Material.OBSIDIAN,
                Material.SMOOTH_QUARTZ_SLAB);
    }
    private static List<Material> initSpawnEggs() {
        // Spawn eggs and buckets of fish
        return Arrays.asList(Material.BAT_SPAWN_EGG,
                Material.CAT_SPAWN_EGG,
                Material.CHICKEN_SPAWN_EGG,
                Material.COD_SPAWN_EGG,
                Material.COW_SPAWN_EGG,
                Material.DONKEY_SPAWN_EGG,
                Material.FOX_SPAWN_EGG,
                Material.HORSE_SPAWN_EGG,
                Material.MOOSHROOM_SPAWN_EGG,
                Material.MULE_SPAWN_EGG,
                Material.OCELOT_SPAWN_EGG,
                Material.PARROT_SPAWN_EGG,
                Material.PIG_SPAWN_EGG,
                Material.PUFFERFISH_SPAWN_EGG,
                Material.RABBIT_SPAWN_EGG,
                Material.SALMON_SPAWN_EGG,
                Material.SHEEP_SPAWN_EGG,
                Material.SKELETON_HORSE_SPAWN_EGG,
                Material.SQUID_SPAWN_EGG,
                Material.STRIDER_SPAWN_EGG,
                Material.TROPICAL_FISH_SPAWN_EGG,
                Material.TURTLE_SPAWN_EGG,
                Material.VILLAGER_SPAWN_EGG,
                Material.WANDERING_TRADER_SPAWN_EGG,
                Material.BEE_SPAWN_EGG,
                Material.DOLPHIN_SPAWN_EGG,
                Material.LLAMA_SPAWN_EGG,
                Material.TRADER_LLAMA_SPAWN_EGG,
                Material.PANDA_SPAWN_EGG,
                Material.POLAR_BEAR_SPAWN_EGG,
                Material.WOLF_SPAWN_EGG,
                Material.CAVE_SPIDER_SPAWN_EGG,
                Material.ENDERMAN_SPAWN_EGG,
                Material.PIGLIN_SPAWN_EGG,
                Material.SPIDER_SPAWN_EGG,
                Material.ZOMBIFIED_PIGLIN_SPAWN_EGG,
                Material.BLAZE_SPAWN_EGG,
                Material.CREEPER_SPAWN_EGG,
                Material.DROWNED_SPAWN_EGG,
                Material.ELDER_GUARDIAN_SPAWN_EGG,
                Material.ENDERMITE_SPAWN_EGG,
                Material.EVOKER_SPAWN_EGG,
                Material.GHAST_SPAWN_EGG,
                Material.GUARDIAN_SPAWN_EGG,
                Material.HUSK_SPAWN_EGG,
                Material.HOGLIN_SPAWN_EGG,
                Material.MAGMA_CUBE_SPAWN_EGG,
                Material.PHANTOM_SPAWN_EGG,
                Material.PILLAGER_SPAWN_EGG,
                Material.RAVAGER_SPAWN_EGG,
                Material.SHULKER_SPAWN_EGG,
                Material.SILVERFISH_SPAWN_EGG,
                Material.SKELETON_SPAWN_EGG,
                Material.SLIME_SPAWN_EGG,
                Material.STRAY_SPAWN_EGG,
                Material.VEX_SPAWN_EGG,
                Material.VINDICATOR_SPAWN_EGG,
                Material.WITCH_SPAWN_EGG,
                Material.WITHER_SKELETON_SPAWN_EGG,
                Material.ZOGLIN_SPAWN_EGG,
                Material.ZOMBIE_SPAWN_EGG,
                Material.ZOMBIE_VILLAGER_SPAWN_EGG,
                Material.GOAT_SPAWN_EGG,
                Material.AXOLOTL_SPAWN_EGG,
                Material.AXOLOTL_BUCKET,
                Material.COD_BUCKET,
                Material.PUFFERFISH_BUCKET,
                Material.SALMON_BUCKET,
                Material.TROPICAL_FISH_BUCKET);
    }
    private static List<Material> initIllegalItems() {
        return Arrays.asList(Material.DEBUG_STICK,
                Material.ELYTRA);
    }

}
