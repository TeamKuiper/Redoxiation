package teamKuiper.redoxiation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockBase extends Block {

	protected ItemStack[] variants = new ItemStack[] {new ItemStack(this)};
	
	public BlockBase(Material materialIn) {
		super(materialIn);
	}

	public BlockBase(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
	}
	
	public void onRegister() {}
	
	public Item getItem() {
		return Item.getItemFromBlock(this);
	}
	
	public ItemStack[] getVariants() {
		return variants;
	}
	
	public ItemStack getVariant(int index) {
		if(index < 0 || variants.length <= index)
			return null;
		return variants[index];
	}
	
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		for (int i = 0; i < variants.length; i++) {
			items.add(variants[i]);
		}
	}
}
