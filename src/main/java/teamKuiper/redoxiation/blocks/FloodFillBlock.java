package teamKuiper.redoxiation.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileFloodFillBlock;

public class FloodFillBlock extends BlockContainer {

	public FloodFillBlock() {
		super(Material.wood);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setBlockName("FloodFillBlock");
		GameRegistry.registerTileEntity(TileFloodFillBlock.class, Redoxiation.MODID + ".FloodFillBlock");
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {

	}

	public void onBlockAdded(World world, int x, int y, int z) {
		TileFloodFillBlock tile = (TileFloodFillBlock) world.getTileEntity(x, y, z);
		int print = tile.fill(x, y, z, 0, 1);
		tile.fill(x, y, z, 0, 0);
		System.out.println("[INFO/REDOXIATION]" + " : " + print);
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

		if (checkstate(x + 1, y, z, 1, world)) {
			int print = ((TileFloodFillBlock) world.getTileEntity(x + 1, y, z)).fill(x + 1, y, z, 0, 1);
			System.out.println("[INFO/REDOXIATION]" + " : " + print);
		}
		if (checkstate(x - 1, y, z, 1, world)) {
			int print = ((TileFloodFillBlock) world.getTileEntity(x - 1, y, z)).fill(x - 1, y, z, 0, 1);
			System.out.println("[INFO/REDOXIATION]" + " : " + print);
		}
		if (checkstate(x, y + 1, z, 1, world)) {
			int print = ((TileFloodFillBlock) world.getTileEntity(x, y + 1, z)).fill(x, y + 1, z, 0, 1);
			System.out.println("[INFO/REDOXIATION]" + " : " + print);
		}
		if (checkstate(x, y - 1, z, 1, world)) {
			int print = ((TileFloodFillBlock) world.getTileEntity(x, y - 1, z)).fill(x, y - 1, z, 0, 1);
			System.out.println("[INFO/REDOXIATION]" + " : " + print);
		}
		if (checkstate(x, y, z + 1, 1, world)) {
			int print = ((TileFloodFillBlock) world.getTileEntity(x, y, z + 1)).fill(x, y, z + 1, 0, 1);
			System.out.println("[INFO/REDOXIATION]" + " : " + print);
		}
		if (checkstate(x, y, z - 1, 1, world)) {
			int print = ((TileFloodFillBlock) world.getTileEntity(x, y, z - 1)).fill(x, y, z - 1, 0, 1);
			System.out.println("[INFO/REDOXIATION]" + " : " + print);
		}

		if (checkstate(x + 1, y, z, 0, world)) {
			((TileFloodFillBlock) world.getTileEntity(x + 1, y, z)).fill(x + 1, y, z, 0, 0);
		}
		if (checkstate(x - 1, y, z, 0, world)) {
			((TileFloodFillBlock) world.getTileEntity(x - 1, y, z)).fill(x - 1, y, z, 0, 0);
		}
		if (checkstate(x, y + 1, z, 0, world)) {
			((TileFloodFillBlock) world.getTileEntity(x, y + 1, z)).fill(x, y + 1, z, 0, 0);
		}
		if (checkstate(x, y - 1, z, 0, world)) {
			((TileFloodFillBlock) world.getTileEntity(x, y - 1, z)).fill(x, y - 1, z, 0, 0);
		}
		if (checkstate(x, y, z + 1, 0, world)) {
			((TileFloodFillBlock) world.getTileEntity(x, y, z + 1)).fill(x, y, z + 1, 0, 0);
		}
		if (checkstate(x, y, z - 1, 0, world)) {
			((TileFloodFillBlock) world.getTileEntity(x, y, z - 1)).fill(x, y, z - 1, 0, 0);
		}

	}

	public boolean checkstate(int x, int y, int z, int st, World world) {
		return ((world.getBlock(x, y, z) == RedoxiationBlocks.FloodFillBlock) && (((TileFloodFillBlock) world.getTileEntity(x, y, z)).state() != st));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileFloodFillBlock();
	}
}
