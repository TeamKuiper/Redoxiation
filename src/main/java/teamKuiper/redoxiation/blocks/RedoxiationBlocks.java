package teamKuiper.redoxiation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.cog.BlockCog;
import teamKuiper.redoxiation.blocks.cog.TileCog;
import teamKuiper.redoxiation.blocks.rocks.Bauxite;
import teamKuiper.redoxiation.blocks.rocks.BlockNetherOre;
import teamKuiper.redoxiation.blocks.rocks.BlockObsidianOre;
import teamKuiper.redoxiation.blocks.rocks.Cryolite;
import teamKuiper.redoxiation.blocks.rocks.Dolomite;
import teamKuiper.redoxiation.blocks.rocks.Limestone;
import teamKuiper.redoxiation.blocks.rocks.OreSulfur;
import teamKuiper.redoxiation.blocks.rocks.BlockOverworldOre;
import teamKuiper.redoxiation.blocks.rocks.BlockRock;
import teamKuiper.redoxiation.blocks.rocks.Rutile;
import teamKuiper.redoxiation.blocks.rocks.SaltRock;
import teamKuiper.redoxiation.blocks.rocks.Scheelite;
import teamKuiper.redoxiation.blocks.rocks.TNTium;
import teamKuiper.redoxiation.blocks.tileentity.TileBlastFurnaceBlock;
import teamKuiper.redoxiation.blocks.tileentity.TileIronCog;
import teamKuiper.redoxiation.blocks.tileentity.TileStoneCog;
import teamKuiper.redoxiation.blocks.tileentity.TileWoodenCog;
import teamKuiper.redoxiation.utils.ICallableWhenRegister;
import teamKuiper.redoxiation.blocks.tileentity.TileTankLead;
import teamKuiper.redoxiation.blocks.tileentity.TilePipeCopper;
import teamKuiper.redoxiation.blocks.tileentity.TileWire;

public class RedoxiationBlocks {
	
    public static final PropertyEnum<EnumFacing> FACING = PropertyEnum.create("facing", EnumFacing.class);

	public static BlockBase oreOverworld, oreNether, oreObsidian, rock, cog;
	
	public static boolean oreCopperCfg, oreTinCfg, oreLeadCfg,
			oreSilverCfg, oreNickelCfg, orePlatinumCfg, oreZincCfg,
			oreCobaltCfg, oreChromiumCfg, pitchblendCfg, rutileCfg,
			scheeliteCfg, bauxiteCfg, limestoneCfg, cryoliteCfg, oreSulfurCfg;
	
	public static BlockBase[] blocks = {oreOverworld, oreNether, oreObsidian, rock, cog};

	public static void initBlocks() {
		// Blocks
		// Normal Ore Name, Harvest level, Hardness, Resistance
		oreOverworld = new BlockOverworldOre();
		oreNether = new BlockNetherOre();
		oreObsidian = new BlockObsidianOre();
		rock = new BlockRock();
		cog = new BlockCog();
	}
	
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		// Register
		event.getRegistry().registerAll(blocks);
		for(BlockBase block : blocks) {
			block.onRegister();
		}

		// Tile Entities
        GameRegistry.registerTileEntity(TileCog.class, cog.getRegistryName());
		
		Redoxiation.logger.info("Block Registry Complete. Starting Item Registry");
	}
	
	public static void registerBlockItems(RegistryEvent.Register<Item> event) {
		for(BlockBase block : blocks) {
			event.getRegistry().registerAll(block.getItem());
		}
	}

	public static void registerRenders(ModelRegistryEvent event) {
		for(BlockBase block : blocks) {
			registerRender(block.getItem());
		}
	}
	
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
