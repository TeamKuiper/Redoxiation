package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.items.RedoxiationGenericItems;

public class TileBlastFurnaceBlock extends MultiblockTileEntityBase {
	
	private String inventoryName = "BlastFurnace";
	private static Block block = RedoxiationBlocks.blastFurnaceBlock;
	private static int[][] emptyPos = {{0, 1, 0}};
	private static int xMinus = 1;
	private static int yMinus = 0;
	private static int zMinus = 1;
	private static int xPlus = 1;
	private static int yPlus = 2;
	private static int zPlus = 1;
	private static Item[] fuel = {Item.getItemFromBlock(RedoxiationBlocks.hotAirBlock)};
	private static ItemStack[][] recipe = {{new ItemStack(Blocks.iron_ore, 4), new ItemStack(Items.coal, 17), new ItemStack(RedoxiationGenericItems.Calcite, 11)}, {new ItemStack(RedoxiationBlocks.moltenPigironBlock, 3), new ItemStack(RedoxiationBlocks.slagBlock, 3)}};
	
	public TileBlastFurnaceBlock() {
		super(block, xMinus, yMinus, zMinus, xPlus, yPlus, zPlus, emptyPos, fuel, recipe);
	}
	

	// returns the smelting result for the given stack. Returns null if the
	// given stack can not be smelted
	@Override
	public ItemStack getSmeltingResultForItem(ItemStack stack) {
		Item item = stack.getItem();
		if (item == Item.getItemFromBlock(Blocks.iron_ore)
				|| item == Items.coal
				|| item == RedoxiationGenericItems.Calcite) {
			return new ItemStack(RedoxiationBlocks.moltenPigironBlock);
		}
		return null;
	}

	// returns the number of ticks the given item will burn. Returns 0 if the
	// given item is not a valid fuel
	@Override
	public short getItemBurnTime(ItemStack stack) {
		int burntime = 0;
		if (stack.getItem() == Item
				.getItemFromBlock(RedoxiationBlocks.hotAirBlock)) {
			burntime = 1600;
		}
		return (short) MathHelper.clamp_int(burntime, 0, Short.MAX_VALUE);
	}

	// Return true if the given stack is allowed to be inserted in the given
	// slot
	// Unlike the vanilla furnace, we allow anything to be placed in the fuel
	// slots
	@Override
	public boolean isItemValidForFuelSlot(ItemStack itemStack) {
		Item item = itemStack.getItem();
		return item == Item.getItemFromBlock(RedoxiationBlocks.hotAirBlock);
	}

	// Return true if the given stack is allowed to be inserted in the given
	// slot
	// Unlike the vanilla furnace, we allow anything to be placed in the fuel
	// slots
	@Override
	public boolean isItemValidForInputSlot(ItemStack itemStack) {
		Item item = itemStack.getItem();
		return item == Item.getItemFromBlock(Blocks.iron_ore)
				|| item == Items.coal
				|| item == RedoxiationGenericItems.Calcite;
	}
	
	// will add a key for this container to the lang file so we can name it in
	// the GUI
	@Override
	public String getInventoryName() {
		return inventoryName;
	}
}