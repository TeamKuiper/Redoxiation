package teamKuiper.redoxiation.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileBlastFurnaceBlock;

public class BlastFurnaceBlock extends MachineBlockBase {
	
	String name = "blastFurnaceBlock";

	public BlastFurnaceBlock() {
		super(Material.WOOD, TileBlastFurnaceBlock.class);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setRegistryName(Redoxiation.MODID, "redoxiation." + name);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileBlastFurnaceBlock();
	}

}