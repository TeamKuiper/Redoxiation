package teamKuiper.redoxiation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileCog;

public class Cog extends BlockContainer {

    String name = "cog";

    public static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(1, 1, 1, 1, 1, 1);
    public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.3125f, 0.9375f, 0.3125f, 0.6875f, 1.0f, 0.6875f);
    public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.3125f, 0.0f, 0.3125f, 0.6875f, 0.0625f, 0.6875f);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.3125f, 0.3125f, 0.0f, 0.6875f, 0.6875f, 0.0625f);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.3125f, 0.3125f, 0.9375f, 0.6875f, 0.6875f, 1.0f);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.9375f, 0.3125f, 0.3125f, 1.0f, 0.6875f, 0.6875f);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0f, 0.3125f, 0.3125f, 0.0625f, 0.6875f, 0.6875f);

    public Cog() {
        super(Material.ROCK);
        setRegistryName(Redoxiation.MODID, Redoxiation.MODID + "." + name);
        setCreativeTab(Redoxiation.tabRedoxiation);
        setHarvestLevel("pickaxe", 2);
        setHardness(2.0F);
        setResistance(10.0F);
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return  new TileCog();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing direction = state.getValue(RedoxiationBlocks.FACING);
        AxisAlignedBB boundingBox = DEFAULT_AABB;
        
        switch(direction) {
        case UP:
        	boundingBox = UP_AABB;
        	break;
        case DOWN:
        	boundingBox = DOWN_AABB;
        	break;
        case NORTH:
        	boundingBox = NORTH_AABB;
        	break;
        case SOUTH:
        	boundingBox = SOUTH_AABB;
        	break;
        case EAST:
        	boundingBox = EAST_AABB;
        	break;
        case WEST:
        	boundingBox = WEST_AABB;
        	break;
        }
        return boundingBox;
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

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        return side;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
    	EnumFacing oppositeSide = side.getOpposite();
    	BlockPos blockPos = new BlockPos(pos.getX() + oppositeSide.getFrontOffsetX(), pos.getY() + oppositeSide.getFrontOffsetY(), pos.getZ() + oppositeSide.getFrontOffsetZ());
        return world.isSideSolid(blockPos, side);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
    	int x = pos.getX();
    	int y = pos.getY();
    	int z = pos.getZ();
    	
        return world.isSideSolid(new BlockPos(x - 1, y, z), EnumFacing.EAST) ||
                world.isSideSolid(new BlockPos(x + 1, y, z), EnumFacing.WEST) ||
                world.isSideSolid(new BlockPos(x, y, z - 1), EnumFacing.SOUTH) ||
                world.isSideSolid(new BlockPos(x, y, z + 1), EnumFacing.NORTH) ||
                world.isSideSolid(new BlockPos(x, y - 1, z), EnumFacing.UP) ||
                world.isSideSolid(new BlockPos(x, y + 1, z), EnumFacing.DOWN);
    }

    @Override
    public void onNeighborChange(IBlockAccess worldIn, BlockPos pos, BlockPos neighbor) {
    	if(worldIn instanceof World) {
    		World world = (World) worldIn;
	        if (!this.canPlaceBlockAt(world, pos)) {
	            this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
	            world.setBlockToAir(pos);
	        } else {
	            EnumFacing side = world.getBlockState(pos).getValue(FACING);
	            if (!canPlaceBlockOnSide(world, pos, side)) {
	                this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
	                world.setBlockToAir(pos);
	            }
	        }
    	}
    }



}
