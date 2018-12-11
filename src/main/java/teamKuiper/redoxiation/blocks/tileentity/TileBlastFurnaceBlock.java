package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.items.RedoxiationGenericItems;

public class TileBlastFurnaceBlock extends TileMachineBase {

	private static String inventoryName = "BlastFurnace";
	private static Block block = RedoxiationBlocks.blastFurnaceBlock;
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
			{ new ItemStack(Blocks.IRON_ORE, 4), new ItemStack(Items.COAL, 17),
					new ItemStack(RedoxiationGenericItems.calcite, 11) },
			{ new ItemStack(RedoxiationBlocks.moltenPigironBlock, 3), new ItemStack(RedoxiationBlocks.slagBlock, 3) } };

	public TileBlastFurnaceBlock() {
		super(inventoryName, block, xMinus, yMinus, zMinus, xPlus, yPlus, zPlus, emptyPos, fuel, burnTime, recipe);
	}
}