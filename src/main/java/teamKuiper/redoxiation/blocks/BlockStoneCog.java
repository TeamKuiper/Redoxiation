package teamKuiper.redoxiation.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileEntityStoneCog;

public class BlockStoneCog extends BlockContainer {

	public BlockStoneCog() {
		super(Material.rock);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setBlockName("StoneCog");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityStoneCog();
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + Redoxiation.MODID + ".StoneCog";
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
