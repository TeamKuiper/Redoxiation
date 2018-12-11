package teamKuiper.redoxiation.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileConverterBlock;

public class ConverterBlock extends MachineBlockBase {
	
	protected ConverterBlock() {
		super(Material.ROCK, TileConverterBlock.class);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setRegistryName(Redoxiation.MODID, "converterBlock");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileConverterBlock();
	}

}
