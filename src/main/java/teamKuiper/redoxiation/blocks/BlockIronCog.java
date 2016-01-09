package teamKuiper.redoxiation.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileEntityIronCog;

public class BlockIronCog extends BlockContainer {

	public BlockIronCog() {
		super(Material.iron);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setBlockName("IronCog");
		GameRegistry.registerTileEntity(TileEntityIronCog.class, Redoxiation.MODID + ".IronCog");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityIronCog();
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + Redoxiation.MODID + ".IronCog";
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
