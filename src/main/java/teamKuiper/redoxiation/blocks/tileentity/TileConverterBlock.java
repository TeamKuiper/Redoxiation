package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class TileConverterBlock extends TileMachineBase {
	
	private static String inventoryName = "Converter";
	private static Block block = RedoxiationBlocks.converterBlock;
	private static int[][] emptyPos = { { 0, 1, 0 } };
	private static int xMinus = 1;
	private static int yMinus = 0;
	private static int zMinus = 1;
	private static int xPlus = 1;
	private static int yPlus = 2;
	private static int zPlus = 1;
	private static Item[] fuel = { Item.getItemFromBlock(RedoxiationBlocks.hotAirBlock) };
	private static int[] burnTime = { 1600 };
	private static ItemStack[][] recipe = {
			{new ItemStack(RedoxiationBlocks.moltenPigironBlock, 1)},
			{new ItemStack(RedoxiationBlocks.moltenPigironBlock, 3)}};

	public TileConverterBlock() {
		super(inventoryName, block, xMinus, yMinus, zMinus, xPlus, yPlus, zPlus, emptyPos, fuel, burnTime, recipe);
	}
}