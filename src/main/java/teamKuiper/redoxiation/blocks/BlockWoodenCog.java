package teamKuiper.redoxiation.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileEntityWoodenCog;

public class BlockWoodenCog extends BlockContainer {

	public BlockWoodenCog() {
		super(Material.wood);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setBlockName("WoodenCog");
		GameRegistry.registerTileEntity(TileEntityWoodenCog.class, Redoxiation.MODID + ".WoodenCog");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityWoodenCog();
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + Redoxiation.MODID + ".WoodenCog";
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

	public boolean checkstate(int x, int y, int z, int st, World world) {
		return ((world.getBlock(x, y, z) == RedoxiationBlocks.WoodenCog) && (((TileEntityWoodenCog) world.getTileEntity(x, y, z)).state() != st));
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		TileEntityWoodenCog tile = (TileEntityWoodenCog) world.getTileEntity(x, y, z);
		int check = tile.fill(x, y, z, 0, 1);
		tile.setfill(x, y, z, check, 0, world);
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

		int check = 0;

		if (checkstate(x+1, y, z, 1, world))
		{
			((TileEntityWoodenCog)world.getTileEntity(x+1, y, z)).fill(x+1, y, z, 0, 1);
		}
		if (checkstate(x-1, y, z, 1, world))
		{
			((TileEntityWoodenCog)world.getTileEntity(x-1, y, z)).fill(x-1, y, z, 0, 1);
		}
		if (checkstate(x, y+1, z, 1, world))
		{
			((TileEntityWoodenCog)world.getTileEntity(x, y+1, z)).fill(x, y+1, z, 0, 1);
		}
		if (checkstate(x, y-1, z, 1, world))
		{
			((TileEntityWoodenCog)world.getTileEntity(x, y-1, z)).fill(x, y-1, z, 0, 1);
		}
		if (checkstate(x, y, z+1, 1, world))
		{
			((TileEntityWoodenCog)world.getTileEntity(x, y, z+1)).fill(x, y, z+1, 0, 1);
		}
		if (checkstate(x, y, z-1, 1, world))
		{
			((TileEntityWoodenCog)world.getTileEntity(x, y, z-1)).fill(x, y, z-1, 0, 1);
		}
		
		
		if (checkstate(x+1, y, z, 0, world))
		{
			check = ((TileEntityWoodenCog)world.getTileEntity(x+1, y, z)).chunknumber();
			((TileEntityWoodenCog)world.getTileEntity(x+1, y, z)).setfill(x+1, y, z, check, 0, world);
		}
		if (checkstate(x-1, y, z, 0, world))
		{
			check = ((TileEntityWoodenCog)world.getTileEntity(x-1, y, z)).chunknumber();
			((TileEntityWoodenCog)world.getTileEntity(x-1, y, z)).setfill(x-1, y, z, check, 0, world);
		}
		if (checkstate(x, y+1, z, 0, world))
		{
			check = ((TileEntityWoodenCog)world.getTileEntity(x, y+1, z)).chunknumber();
			((TileEntityWoodenCog)world.getTileEntity(x, y+1, z)).setfill(x, y+1, z, check, 0, world);
		}
		if (checkstate(x, y-1, z, 0, world))
		{
			check = ((TileEntityWoodenCog)world.getTileEntity(x, y-1, z)).chunknumber();
			((TileEntityWoodenCog)world.getTileEntity(x, y-1, z)).setfill(x, y-1, z, check, 0, world);
		}
		if (checkstate(x, y, z+1, 0, world))
		{
			check = ((TileEntityWoodenCog)world.getTileEntity(x, y, z+1)).chunknumber();
			((TileEntityWoodenCog)world.getTileEntity(x, y, z+1)).setfill(x, y, z+1, check, 0, world);
		}
		if (checkstate(x, y, z-1, 0, world))
		{
			check = ((TileEntityWoodenCog)world.getTileEntity(x, y, z-1)).chunknumber();
			((TileEntityWoodenCog)world.getTileEntity(x, y, z-1)).setfill(x, y, z-1, check, 0, world);
		}
		
	}

}
