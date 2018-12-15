package teamKuiper.redoxiation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.cog.BlockCog;
import teamKuiper.redoxiation.blocks.cog.TileCog;
import teamKuiper.redoxiation.blocks.rocks.BlockNetherOre;
import teamKuiper.redoxiation.blocks.rocks.BlockObsidianOre;
import teamKuiper.redoxiation.blocks.rocks.BlockOverworldOre;
import teamKuiper.redoxiation.blocks.rocks.BlockRock;
import teamKuiper.redoxiation.render.RenderCog;

public class RedoxiationBlocks {
	
    public static final PropertyEnum<EnumFacing> FACING = PropertyEnum.create("facing", EnumFacing.class);

	public static BlockBase oreOverworld, oreNether, oreObsidian, rock, cog;
	
	public static RenderCog renderCog;
	
	public static BlockBase[] blocks;

	public static void initBlocks() {
		// Blocks
		// Normal Ore Name, Harvest level, Hardness, Resistance
		oreOverworld = new BlockOverworldOre();
		oreNether = new BlockNetherOre();
		oreObsidian = new BlockObsidianOre();
		rock = new BlockRock();
		cog = new BlockCog();
		
		blocks = new BlockBase[] {oreOverworld, oreNether, oreObsidian, rock, cog};
		for(BlockBase block : blocks) {
			block.init();
		}
	}
	
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		// Register
		event.getRegistry().registerAll(blocks);

		// Tile Entities
        GameRegistry.registerTileEntity(TileCog.class, cog.getRegistryName());
		
        renderCog = new RenderCog();
        ClientRegistry.bindTileEntitySpecialRenderer(TileCog.class, renderCog);
        
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
