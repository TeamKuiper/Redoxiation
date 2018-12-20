package teamKuiper.redoxiation.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.items.RedoxiationItems;
import teamKuiper.redoxiation.utils.TempOreDictionary;

public class RedoxiationRegistryHandler {

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		RedoxiationBlocks.initBlocks();
		RedoxiationBlocks.registerBlocks(event);
		RedoxiationBlocks.postInitBlocks();
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		RedoxiationBlocks.registerBlockItems(event);
		TempOreDictionary.register("blocks");
		RedoxiationItems.initItems();
		RedoxiationItems.registerItems(event);
		TempOreDictionary.register("items");
	}

	@SubscribeEvent
	public void registerRenders(ModelRegistryEvent event) {
		RedoxiationBlocks.registerRenders(event);
	}
}
