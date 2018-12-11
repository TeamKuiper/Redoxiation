package teamKuiper.redoxiation.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileIronCog;

public class BlockIronCog extends BlockContainer {

	public BlockIronCog() {
		super(Material.IRON);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setRegistryName(Redoxiation.MODID, "ironCog");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileIronCog();
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + Redoxiation.MODID + ".ironCog";
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

}
