package teamKuiper.redoxiation.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.items.RedoxiationGenericItems;

public class SaltRock extends Block {

	String name = "saltRock";

	public SaltRock() {
		super(Material.rock);
		setBlockName(Redoxiation.MODID + "." + name);
		setBlockTextureName(Redoxiation.MODID + ":" + name);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setHarvestLevel("pickaxe", 1);
		setHardness(2.0F);
		setResistance(10.0F);

	}

	public Item getItemDropped(int metadata, Random random, int fortune) {
		return RedoxiationGenericItems.SaltChunk;
	}

	public int quantityDropped(Random random) {
		return 2 + random.nextInt(2);
	}

	public int quantityDroppedWithBonus(int fortune, Random random) {

		if (fortune > 0) {
			int j = random.nextInt(fortune + 5) - 1;
			if (j > 0) {
				return j++;
			}
		}
		return 3;
	}

}
