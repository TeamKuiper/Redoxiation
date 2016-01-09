package teamKuiper.redoxiation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import teamKuiper.redoxiation.Redoxiation;

public class RedoxiationOre extends Block {

	public RedoxiationOre(String name, int harvestlevel, float hardness, float resistance) {

		super(Material.rock);

		setBlockName(Redoxiation.MODID + "." + name);
		setBlockTextureName(Redoxiation.MODID + ":" + name);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setHarvestLevel("pickaxe", harvestlevel);
		setHardness(hardness);
		setResistance(resistance);
	}
}
