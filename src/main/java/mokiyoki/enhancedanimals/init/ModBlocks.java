package mokiyoki.enhancedanimals.init;

import mokiyoki.enhancedanimals.blocks.EggCartonBlock;
import mokiyoki.enhancedanimals.blocks.GrowableDoubleHigh;
import mokiyoki.enhancedanimals.blocks.GrowablePlant;
import mokiyoki.enhancedanimals.blocks.PatchyMyceliumBlock;
import mokiyoki.enhancedanimals.blocks.Post;
import mokiyoki.enhancedanimals.blocks.SparseGrassBlock;
import mokiyoki.enhancedanimals.blocks.UnboundHayBlock;
import mokiyoki.enhancedanimals.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Items;
import net.minecraftforge.registries.ObjectHolder;

/**
 * Created by moki on 25/08/2018.
 */
@ObjectHolder(Reference.MODID)
public class ModBlocks {

    public static final Block POST_ACACIA = new Post(Block.Properties.create(Material.WOOD, MaterialColor.ADOBE).hardnessAndResistance(2.0F,15.0F).sound(SoundType.WOOD)).setRegistryName("post_acacia");
    public static final Block POST_BIRCH = new Post(Block.Properties.create(Material.WOOD, MaterialColor.SAND).hardnessAndResistance(2.0F,15.0F).sound(SoundType.WOOD)).setRegistryName("post_birch");
    public static final Block POST_DARK_OAK = new Post(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F,15.0F).sound(SoundType.WOOD)).setRegistryName("post_dark_oak");
    public static final Block POST_JUNGLE = new Post(Block.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(2.0F,15.0F).sound(SoundType.WOOD)).setRegistryName("post_jungle");
    public static final Block POST_OAK = new Post(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F,15.0F).sound(SoundType.WOOD)).setRegistryName("post_oak");
    public static final Block POST_SPRUCE = new Post(Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F,15.0F).sound(SoundType.WOOD)).setRegistryName("post_spruce");

    public static final Block EGG_CARTON = new EggCartonBlock(Block.Properties.create(Material.WOOL, MaterialColor.LIGHT_GRAY).hardnessAndResistance(0.0F).sound(SoundType.CLOTH).notSolid()).setRegistryName("eanimod:egg_carton");
    public static final Block UNBOUNDHAY_BLOCK = new UnboundHayBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.PLANT).notSolid()).setRegistryName("eanimod:unboundhay_block");
    public static final Block SPARSE_GRASS_BLOCK = new SparseGrassBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.DIRT).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.PLANT)).setRegistryName("eanimod:sparsegrass_block");
    public static final Block PATCHY_MYCELIUM_BLOCK = new PatchyMyceliumBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.PURPLE).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.PLANT)).setRegistryName("eanimod:patchymycelium_block");
    public static final Block GROWABLE_ALLIUM = new GrowablePlant(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT), Items.ALLIUM).setRegistryName("eanimod:growable_allium");
    public static final Block GROWABLE_AZURE_BLUET = new GrowablePlant(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT), Items.AZURE_BLUET).setRegistryName("eanimod:growable_azure_bluet");
    public static final Block GROWABLE_BLUE_ORCHID = new GrowablePlant(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT), Items.BLUE_ORCHID).setRegistryName("eanimod:growable_blue_orchid");
    public static final Block GROWABLE_CORNFLOWER = new GrowablePlant(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT), Items.CORNFLOWER).setRegistryName("eanimod:growable_cornflower");
    public static final Block GROWABLE_DANDELION = new GrowablePlant(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT), Items.DANDELION).setRegistryName("eanimod:growable_dandelion");
    public static final Block GROWABLE_OXEYE_DAISY = new GrowablePlant(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT), Items.OXEYE_DAISY).setRegistryName("eanimod:growable_oxeye_daisy");
    public static final Block GROWABLE_ROSE_BUSH = new GrowableDoubleHigh(Block.Properties.create(Material.TALL_PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT), Items.ROSE_BUSH).setRegistryName("eanimod:growable_rose_bush");
    public static final Block GROWABLE_SUNFLOWER = new GrowableDoubleHigh(Block.Properties.create(Material.TALL_PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT), Items.SUNFLOWER).setRegistryName("eanimod:growable_sunflower");

}