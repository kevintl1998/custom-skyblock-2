package me.t0c.customskyblock2.data;

import me.t0c.customskyblock2.CustomSkyblock2;
import me.t0c.customskyblock2.Dong;
import org.bukkit.Material;

import java.util.*;

public class Blocks {
    /* Stores lists of blocks in categories that are relevant to this plugin */

    public static final List<Material> illegalBlocks = initIllegalBlocks();
    public static final List<Material> airBlocks = initAirBlocks();
    public static final List<Material> unbreakableBlocks = initUnbreakableBlocks();
    public static final List<Material> rareBlocks = initRareBlocks();
    public static final List<Material> blocks = initBlocks();
    public static final List<Material> ores = initOres();
    public static final List<Material> infinityWoodBlockOptions = initInfinityWoodBlockOptions();
    public static final List<Material> bedBlocks = initBeds();
    public static final List<Material> liquids = initLiquids();
    public static final List<Material> illegalSpawnBlocks = initIllegalSpawnBlocks();
    public static final List<Material> guiBlocks = initGuiBlocks();
    public static final List<Material> shulkerBoxes = initShulkerBoxes();

    /* random getters */
    public static Material getRandomBlock() {
        Material m = blocks.get(CustomSkyblock2.instance().random().nextInt(blocks.size()));
        if(illegalBlocks.contains(m)) return getRandomBlock();
        else return m;
    }

    public static Material getRandomBlockBiased() {
        Material m = getRandomBlock();
        if(rareBlocks.contains(m)) {
            return getRandomBlock();
        } else return m;
    }
    public static Material getRandomBed() {
        return bedBlocks.get(CustomSkyblock2.instance().random().nextInt(bedBlocks.size()));
    }

    public static Material getRandomInfinityWoodBlock() {
        return infinityWoodBlockOptions.get(CustomSkyblock2.instance().random().nextInt(infinityWoodBlockOptions.size()));
    }
    public static Material getRandomInfinityWoodBlockBiased() {
        Material m = getRandomInfinityWoodBlock();
        if(!unbreakableBlocks.contains(m)) {
            return getRandomInfinityWoodBlock();
        }
        return m;
    }

    /* Initializers */
    private static List<Material> initBlocks() {
        List<Material> mList = new ArrayList<>();
        for(Material m : Material.values()) {
            if(m.isBlock()) mList.add(m);
        }
        return mList;
    }
    private static List<Material> initRareBlocks() {
        return Arrays.asList(
                Material.ENDER_CHEST,
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
                Material.GILDED_BLACKSTONE,
                Material.DRAGON_EGG,
                Material.ELYTRA);
    }
    private static List<Material> initUnbreakableBlocks() {
        return Arrays.asList(
                Material.COMMAND_BLOCK,
                Material.CHAIN_COMMAND_BLOCK,
                Material.REPEATING_COMMAND_BLOCK,
                Material.COMMAND_BLOCK,
                Material.BEDROCK,
                Material.BARRIER,
                Material.JIGSAW,
                Material.STRUCTURE_BLOCK,
                Material.FIRE,
                Material.WATER,
                Material.LAVA,
                Material.BUBBLE_COLUMN,
                Material.NETHER_PORTAL,
                Material.END_GATEWAY,
                Material.END_PORTAL_FRAME,
                Material.END_PORTAL_FRAME,
                Material.DRAGON_EGG,
                Material.AIR,
                Material.VOID_AIR,
                Material.CAVE_AIR);
    }
    private static List<Material> initAirBlocks() {
        return Arrays.asList(
                Material.AIR,
                Material.VOID_AIR,
                Material.VOID_AIR);
    }
    private static List<Material> initIllegalBlocks() {
        return Arrays.asList(
                Material.AIR,
                Material.STRUCTURE_VOID,
                Material.CAVE_AIR,
                Material.VOID_AIR,
                Material.WATER,
                Material.LAVA,
                Material.MOVING_PISTON,
                Material.NETHERITE_BLOCK);
    }
    private static List<Material> initOres() {
        return Arrays.asList(Material.COAL_ORE,
                Material.IRON_ORE,
                Material.GOLD_ORE,
                Material.LAPIS_ORE,
                Material.REDSTONE_ORE,
                Material.DIAMOND_ORE,
                Material.EMERALD_ORE,
                Material.NETHER_QUARTZ_ORE,
                Material.NETHER_GOLD_ORE,
                Material.ANCIENT_DEBRIS);
    }
    private static List<Material> initInfinityWoodBlockOptions() {
        return Arrays.asList(Material.PETRIFIED_OAK_SLAB,
                Material.OAK_SLAB,
                Material.SPRUCE_SLAB,
                Material.ACACIA_SLAB,
                Material.JUNGLE_SLAB,
                Material.BIRCH_SLAB,
                Material.DARK_OAK_SLAB,
                Material.OAK_DOOR,
                Material.SPRUCE_DOOR,
                Material.ACACIA_DOOR,
                Material.JUNGLE_DOOR,
                Material.BIRCH_DOOR,
                Material.DARK_OAK_DOOR,
                Material.OAK_FENCE,
                Material.SPRUCE_FENCE,
                Material.ACACIA_FENCE,
                Material.JUNGLE_FENCE,
                Material.BIRCH_FENCE,
                Material.DARK_OAK_FENCE,
                Material.OAK_FENCE_GATE,
                Material.SPRUCE_FENCE_GATE,
                Material.ACACIA_FENCE_GATE,
                Material.JUNGLE_FENCE_GATE,
                Material.BIRCH_FENCE_GATE,
                Material.DARK_OAK_FENCE_GATE,
                Material.OAK_BUTTON,
                Material.SPRUCE_BUTTON,
                Material.ACACIA_BUTTON,
                Material.JUNGLE_BUTTON,
                Material.BIRCH_BUTTON,
                Material.DARK_OAK_BUTTON,
                Material.OAK_PRESSURE_PLATE,
                Material.SPRUCE_PRESSURE_PLATE,
                Material.ACACIA_PRESSURE_PLATE,
                Material.JUNGLE_PRESSURE_PLATE,
                Material.BIRCH_PRESSURE_PLATE,
                Material.DARK_OAK_PRESSURE_PLATE,
                Material.OAK_TRAPDOOR,
                Material.SPRUCE_TRAPDOOR,
                Material.ACACIA_TRAPDOOR,
                Material.JUNGLE_TRAPDOOR,
                Material.BIRCH_TRAPDOOR,
                Material.DARK_OAK_TRAPDOOR,
                Material.OAK_STAIRS,
                Material.SPRUCE_STAIRS,
                Material.ACACIA_STAIRS,
                Material.JUNGLE_STAIRS,
                Material.BIRCH_STAIRS,
                Material.DARK_OAK_STAIRS,
                Material.OAK_SIGN,
                Material.SPRUCE_SIGN,
                Material.ACACIA_SIGN,
                Material.JUNGLE_SIGN,
                Material.BIRCH_SIGN,
                Material.DARK_OAK_SIGN,
                Material.OAK_WOOD,
                Material.SPRUCE_WOOD,
                Material.ACACIA_WOOD,
                Material.JUNGLE_WOOD,
                Material.BIRCH_WOOD,
                Material.DARK_OAK_WOOD,
                Material.STRIPPED_OAK_WOOD,
                Material.STRIPPED_SPRUCE_WOOD,
                Material.STRIPPED_ACACIA_WOOD,
                Material.STRIPPED_JUNGLE_WOOD,
                Material.STRIPPED_BIRCH_WOOD,
                Material.STRIPPED_DARK_OAK_WOOD,
                Material.STRIPPED_OAK_LOG,
                Material.STRIPPED_SPRUCE_WOOD,
                Material.STRIPPED_ACACIA_WOOD,
                Material.STRIPPED_JUNGLE_WOOD,
                Material.STRIPPED_BIRCH_WOOD,
                Material.STRIPPED_DARK_OAK_WOOD,
                Material.OAK_LOG,
                Material.SPRUCE_LOG,
                Material.ACACIA_LOG,
                Material.JUNGLE_LOG,
                Material.BIRCH_LOG,
                Material.DARK_OAK_LOG,
                Material.OAK_PLANKS,
                Material.SPRUCE_PLANKS,
                Material.ACACIA_PLANKS,
                Material.JUNGLE_PLANKS,
                Material.BIRCH_PLANKS,
                Material.DARK_OAK_PLANKS,
                Material.WARPED_STEM,
                Material.WARPED_PRESSURE_PLATE,
                Material.WARPED_FENCE,
                Material.WARPED_BUTTON,
                Material.WARPED_STAIRS,
                Material.WARPED_TRAPDOOR,
                Material.WARPED_FENCE_GATE,
                Material.WARPED_SIGN,
                Material.WARPED_DOOR,
                Material.WARPED_SLAB,
                Material.WARPED_HYPHAE,
                Material.STRIPPED_WARPED_STEM,
                Material.STRIPPED_WARPED_HYPHAE,
                Material.WARPED_PLANKS,
                Material.CRIMSON_STEM,
                Material.CRIMSON_PRESSURE_PLATE,
                Material.CRIMSON_FENCE,
                Material.CRIMSON_BUTTON,
                Material.CRIMSON_STAIRS,
                Material.CRIMSON_TRAPDOOR,
                Material.CRIMSON_FENCE_GATE,
                Material.CRIMSON_SIGN,
                Material.CRIMSON_DOOR,
                Material.CRIMSON_SLAB,
                Material.CRIMSON_HYPHAE,
                Material.STRIPPED_CRIMSON_HYPHAE,
                Material.STRIPPED_WARPED_STEM,
                Material.CRIMSON_PLANKS,
                Material.BEDROCK,
                Material.BARRIER,
                Material.STRUCTURE_BLOCK,
                Material.JIGSAW,
                Material.COMMAND_BLOCK,
                Material.REPEATING_COMMAND_BLOCK,
                Material.CHAIN_COMMAND_BLOCK);
    }
    private static List<Material> initBeds() {
        return Arrays.asList(Material.BLACK_BED,
                Material.BLUE_BED,
                Material.BROWN_BED,
                Material.CYAN_BED,
                Material.GRAY_BED,
                Material.GREEN_BED,
                Material.LIGHT_BLUE_BED,
                Material.LIGHT_GRAY_BED,
                Material.LIME_BED,
                Material.MAGENTA_BED,
                Material.ORANGE_BED,
                Material.PINK_BED,
                Material.PURPLE_BED,
                Material.RED_BED,
                Material.YELLOW_BED,
                Material.WHITE_BED);
    }
    private static List<Material> initLiquids() {
        return Arrays.asList(Material.WATER,
                Material.LAVA);
    }
    private static List<Material> initIllegalSpawnBlocks() {
        List<Material> ret = new ArrayList<>(unbreakableBlocks);
        ret.addAll(Arrays.asList(Material.OBSIDIAN,
                Material.CRYING_OBSIDIAN,
                Material.ANCIENT_DEBRIS,
                Material.RESPAWN_ANCHOR,
                Material.ENDER_CHEST,
                Material.NETHERITE_BLOCK));
        return ret;


    }
    private static List<Material> initGuiBlocks() {
        return Arrays.asList(Material.CHEST,
                Material.TRAPPED_CHEST,
                Material.CRAFTING_TABLE,
                Material.ENDER_CHEST,
                Material.BREWING_STAND,
                Material.FURNACE,
                Material.BLAST_FURNACE,
                Material.SMOKER,
                Material.ENCHANTING_TABLE,
                Material.ANVIL,
                Material.CHIPPED_ANVIL,
                Material.DAMAGED_ANVIL,
                Material.SHULKER_BOX,
                Material.WHITE_SHULKER_BOX,
                Material.ORANGE_SHULKER_BOX,
                Material.MAGENTA_SHULKER_BOX,
                Material.LIGHT_BLUE_SHULKER_BOX,
                Material.YELLOW_SHULKER_BOX,
                Material.LIME_SHULKER_BOX,
                Material.PINK_SHULKER_BOX,
                Material.GRAY_SHULKER_BOX,
                Material.LIGHT_GRAY_SHULKER_BOX,
                Material.CYAN_SHULKER_BOX,
                Material.PURPLE_SHULKER_BOX,
                Material.BLUE_SHULKER_BOX,
                Material.GREEN_SHULKER_BOX,
                Material.RED_SHULKER_BOX,
                Material.BLACK_SHULKER_BOX,
                Material.LOOM,
                Material.BARREL,
                Material.CARTOGRAPHY_TABLE,
                Material.GRINDSTONE,
                Material.SMITHING_TABLE,
                Material.STONECUTTER);
    }
    private static List<Material> initShulkerBoxes() {
        return Arrays.asList(Material.SHULKER_BOX,
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
                Material.YELLOW_SHULKER_BOX);
    }
}
