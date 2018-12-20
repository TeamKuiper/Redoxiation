package teamKuiper.redoxiation.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import teamKuiper.redoxiation.utils.TempOreDictionary;

public class BlockBase extends Block {

	protected List<Integer> metas = new ArrayList<Integer>();
	protected List<ItemStack> variants = new ArrayList<ItemStack>();

	public BlockBase(Material materialIn) {
		super(materialIn);
	}

	public BlockBase(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
	}
	
	public void postInit() {}
	
	public Item getItem() {
		return new ItemBlock(this).setRegistryName(this.getRegistryName());
	}
	
	protected ItemStack addVariant(int meta) {
		if(metas.contains(meta))
			return null;
		ItemStack stack = new ItemStack(this, 1, meta);
		this.variants.add(stack);
		
		return stack;
	}
	
	protected ItemStack addVariant(int meta, String name) {
		if(metas.contains(meta))
			return null;
		ItemStack stack = new ItemStack(this, 1, meta);
		this.variants.add(stack);
		TempOreDictionary.tempRegister("blocks", name, stack);
		
		return stack;
	}
	
	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		items.addAll(variants);
	}
}
