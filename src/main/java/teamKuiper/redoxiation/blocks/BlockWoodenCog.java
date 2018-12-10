package teamKuiper.redoxiation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileWoodenCog;

public class BlockWoodenCog extends BlockContainer {

	public BlockWoodenCog() {
		super(Material.WOOD);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setRegistryName(Redoxiation.MODID, "WoodenCog");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileWoodenCog();
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + Redoxiation.MODID + ".WoodenCog";
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean checkstate(int x, int y, int z, int st, World world) {
		return ((world.getBlock(x, y, z) == RedoxiationBlocks.woodenCog) && (((TileWoodenCog) world.getTileEntity(x, y, z)).state() != st));
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		TileWoodenCog tile = (TileWoodenCog) world.getTileEntity(x, y, z);
		int check = tile.fill(x, y, z, 0, 1);
		tile.setfill(x, y, z, check, 0, world);
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

		int check = 0;

		if (checkstate(x+1, y, z, 1, world))
		{
			((TileWoodenCog)world.getTileEntity(x+1, y, z)).fill(x+1, y, z, 0, 1);
		}
		if (checkstate(x-1, y, z, 1, world))
		{
			((TileWoodenCog)world.getTileEntity(x-1, y, z)).fill(x-1, y, z, 0, 1);
		}
		if (checkstate(x, y+1, z, 1, world))
		{
			((TileWoodenCog)world.getTileEntity(x, y+1, z)).fill(x, y+1, z, 0, 1);
		}
		if (checkstate(x, y-1, z, 1, world))
		{
			((TileWoodenCog)world.getTileEntity(x, y-1, z)).fill(x, y-1, z, 0, 1);
		}
		if (checkstate(x, y, z+1, 1, world))
		{
			((TileWoodenCog)world.getTileEntity(x, y, z+1)).fill(x, y, z+1, 0, 1);
		}
		if (checkstate(x, y, z-1, 1, world))
		{
			((TileWoodenCog)world.getTileEntity(x, y, z-1)).fill(x, y, z-1, 0, 1);
		}
		
		
		if (checkstate(x+1, y, z, 0, world))
		{
			check = ((TileWoodenCog)world.getTileEntity(x+1, y, z)).chunknumber();
			((TileWoodenCog)world.getTileEntity(x+1, y, z)).setfill(x+1, y, z, check, 0, world);
		}
		if (checkstate(x-1, y, z, 0, world))
		{
			check = ((TileWoodenCog)world.getTileEntity(x-1, y, z)).chunknumber();
			((TileWoodenCog)world.getTileEntity(x-1, y, z)).setfill(x-1, y, z, check, 0, world);
		}
		if (checkstate(x, y+1, z, 0, world))
		{
			check = ((TileWoodenCog)world.getTileEntity(x, y+1, z)).chunknumber();
			((TileWoodenCog)world.getTileEntity(x, y+1, z)).setfill(x, y+1, z, check, 0, world);
		}
		if (checkstate(x, y-1, z, 0, world))
		{
			check = ((TileWoodenCog)world.getTileEntity(x, y-1, z)).chunknumber();
			((TileWoodenCog)world.getTileEntity(x, y-1, z)).setfill(x, y-1, z, check, 0, world);
		}
		if (checkstate(x, y, z+1, 0, world))
		{
			check = ((TileWoodenCog)world.getTileEntity(x, y, z+1)).chunknumber();
			((TileWoodenCog)world.getTileEntity(x, y, z+1)).setfill(x, y, z+1, check, 0, world);
		}
		if (checkstate(x, y, z-1, 0, world))
		{
			check = ((TileWoodenCog)world.getTileEntity(x, y, z-1)).chunknumber();
			((TileWoodenCog)world.getTileEntity(x, y, z-1)).setfill(x, y, z-1, check, 0, world);
		}
		
	}

}
