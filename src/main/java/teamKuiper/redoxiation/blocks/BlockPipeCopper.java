package teamKuiper.redoxiation.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TilePipeCopper;

public class BlockPipeCopper extends BlockPipeBase {
	
	public BlockPipeCopper() {
		super();
		setRegistryName(Redoxiation.MODID, "pipeCopper");
		setCreativeTab(Redoxiation.tabRedoxiation);
		setHarvestLevel("pickaxe", 1);
		setHardness(2.0F);
		setResistance(10.0F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TilePipeCopper();
	}
}
