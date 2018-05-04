package teamKuiper.redoxiation.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileConverterBlock;

public class ConverterBlock extends MachineBlockBase {

	String name = "converterBlock";
	
	protected ConverterBlock() {
		super(Material.rock, TileConverterBlock.class);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setBlockName("redoxiation." + name);
		setBlockTextureName(Redoxiation.MODID + ":" + name);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileConverterBlock();
	}

}
