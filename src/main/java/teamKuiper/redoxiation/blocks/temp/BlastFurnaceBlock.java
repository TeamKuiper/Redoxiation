package teamKuiper.redoxiation.blocks.temp;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileBlastFurnaceBlock;

public class BlastFurnaceBlock extends MachineBlockBase {

	public BlastFurnaceBlock() {
		super(Material.WOOD, TileBlastFurnaceBlock.class);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setRegistryName(Redoxiation.MODID, "blastFurnaceBlock");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileBlastFurnaceBlock();
	}

}