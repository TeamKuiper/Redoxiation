package teamKuiper.redoxiation.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.blocks.tileentity.TilePipeBase;

public class BlockPipeBase extends BlockContainer {

	public BlockPipeBase() {
		super(Material.iron);

		float pixel = 1F / 16F;
		this.setBlockBounds(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2,
				1 - 11 * pixel / 2);
		this.useNeighborBrightness = true;
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

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TilePipeBase();
	}
}
