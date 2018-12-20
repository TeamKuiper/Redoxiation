package teamKuiper.redoxiation.blocks.cog;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.BlockBase;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.utils.IBlockBoundCustomizable;

public class BlockCog extends BlockBase implements IBlockBoundCustomizable {
/*
    public static final PropertyEnum<CogType> DOWN = PropertyEnum.create("down", CogType.class);
    public static final PropertyEnum<CogType> UP = PropertyEnum.create("up", CogType.class);
    public static final PropertyEnum<CogType> NORTH = PropertyEnum.create("north", CogType.class);
    public static final PropertyEnum<CogType> SOUTH = PropertyEnum.create("south", CogType.class);
    public static final PropertyEnum<CogType> WEST = PropertyEnum.create("west", CogType.class);
    public static final PropertyEnum<CogType> EAST = PropertyEnum.create("east", CogType.class);
    public static final PropertyEnum<?>[] SIDE_PROPERTIES = {DOWN, UP, NORTH, SOUTH, WEST, EAST};*/
    
    //public static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(1, 1, 1, 1, 1, 1);
    public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.3125f, 0.0f, 0.3125f, 0.6875f, 0.0625f, 0.6875f);
    public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.3125f, 0.9375f, 0.3125f, 0.6875f, 1.0f, 0.6875f);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.3125f, 0.3125f, 0.9375f, 0.6875f, 0.6875f, 1.0f);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.3125f, 0.3125f, 0.0f, 0.6875f, 0.6875f, 0.0625f);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0f, 0.3125f, 0.3125f, 0.0625f, 0.6875f, 0.6875f);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.9375f, 0.3125f, 0.3125f, 1.0f, 0.6875f, 0.6875f);
    public static final AxisAlignedBB[] AABBs = {DOWN_AABB, UP_AABB, NORTH_AABB, SOUTH_AABB, WEST_AABB, EAST_AABB};
    
    public BlockCog() {
        super(Material.ROCK);
        setUnlocalizedName("cog");
        setRegistryName(Redoxiation.MODID, "cog");
        setCreativeTab(Redoxiation.tabRedoxiation);
        setHarvestLevel("pickaxe", 2);
        setHardness(2.0F);
        setResistance(10.0F);
    }
    
    @Override
    public Item getItem() {
    	return new ItemBlockCog();
    }
    
    @Override
    public AxisAlignedBB[] getDrawAABBs(EntityPlayer player, World world, BlockPos pos) {
    	return new AxisAlignedBB[] {AABBs[getLookingSide(player, world, pos).getIndex()]};
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state){
        return new TileCog();
    }

    /*@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing direction = state.getValue(RedoxiationBlocks.FACING);
        AxisAlignedBB boundingBox = DEFAULT_AABB;
        
        switch(direction) {
        case DOWN:
        	boundingBox = DOWN_AABB;
        	break;
        case UP:
        	boundingBox = UP_AABB;
        	break;
        case NORTH:
        	boundingBox = NORTH_AABB;
        	break;
        case SOUTH:
        	boundingBox = SOUTH_AABB;
        	break;
        case WEST:
        	boundingBox = WEST_AABB;
        	break;
        case EAST:
        	boundingBox = EAST_AABB;
        	break;
        }
        return boundingBox;
    }*/
    

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
	
	//Sets collision boxes
	@Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		TileCog cog = getCog(worldIn, pos);
		for(EnumFacing side : EnumFacing.values()) {
			if(cog.getCogType(side) != CogType.NONE) {
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBs[side.getIndex()]);
			}
		}
    }

	//Returns true if there is no cog in the given side and the block of the given side is solid.
    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
    	EnumFacing oppositeSide = side.getOpposite();
    	BlockPos blockPos = new BlockPos(pos.getX() + oppositeSide.getFrontOffsetX(), pos.getY() + oppositeSide.getFrontOffsetY(), pos.getZ() + oppositeSide.getFrontOffsetZ());
        return world.isSideSolid(pos, side) && 
        		(world.getBlockState(blockPos).getBlock() != RedoxiationBlocks.cog || getCog(world, blockPos).getCogType(oppositeSide) == CogType.NONE);
    }

    /*@Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
    	IBlockState state = world.getBlockState(pos);
    	if(state.getBlock() != RedoxiationBlocks.cog) return false;
    	int x = pos.getX();
    	int y = pos.getY();
    	int z = pos.getZ();
    	
        return world.isSideSolid(new BlockPos(x, y + 1, z), EnumFacing.DOWN) ||
                world.isSideSolid(new BlockPos(x, y - 1, z), EnumFacing.UP) ||
                world.isSideSolid(new BlockPos(x, y, z + 1), EnumFacing.NORTH) ||
                world.isSideSolid(new BlockPos(x, y, z - 1), EnumFacing.SOUTH) ||
                world.isSideSolid(new BlockPos(x + 1, y, z), EnumFacing.WEST) ||
                world.isSideSolid(new BlockPos(x - 1, y, z), EnumFacing.EAST);
    }*/
    
    
    //Drops the cog which is on the non-solid block.
	@Override
    public void onNeighborChange(IBlockAccess worldIn, BlockPos pos, BlockPos neighbor) {
    	if(worldIn instanceof World) {
    		World world = (World) worldIn;
			for(EnumFacing side : EnumFacing.values()) {
				TileCog cog = getCog(worldIn, pos);
				CogType type = cog.getCogType(side);
				if (type != CogType.NONE && !canPlaceBlockOnSide(world, pos, side)) {
					cog.destroySide(side, true);
					Block.spawnAsEntity(world, pos, new ItemStack(this, 1, type.getMetadata()));
				}
	    	}
    	}
    }

	//Returns ItemStack of cogs in all sides.
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		int[] valueCount = new int[CogType.values().length];
		for(EnumFacing side : EnumFacing.values()) {
			int value = getCog(world, pos).getCogType(side).getMetadata();
			valueCount[value]++;
		}
		for(int i = 0; i < valueCount.length; i++) {
			if(CogType.values()[i] == CogType.NONE) continue;
			ItemStack stack = new ItemStack(this, valueCount[i], i);
			drops.add(stack);
		}
	}
	
	//Returns the ItemStack of the cog which player is looking.
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		EnumFacing facing = getLookingSide(player, world, pos);
		if(facing == null) return null;
		CogType type = getCog(world, pos).getCogType(facing);
		if(type == CogType.NONE) return null;
		return new ItemStack(this, 1, type.getMetadata());
	}
	
	//When player breaks the cog, only destroys the cog which player is looking.
	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest){
		EnumFacing side = getLookingSide(player, world, pos);
		if(side != null) {
			getCog(world, pos).destroySide(side, true);
			return true;
		}
		return false;
    }
	
	public EnumFacing getLookingSide(EntityPlayer player, IBlockAccess world, BlockPos pos) {
		return findLookingSide(player, pos, getCog(world, pos).getPlacedBlockSides());
	}
	
	/**
	 * Returns the side where the blocks the player is looking are located among the given side.
	 * Returns null if none of them are looked.
	 * @param player
	 * @param pos
	 * @param sides
	 * @return
	 */
	public EnumFacing findLookingSide(EntityPlayer player, BlockPos pos, EnumFacing[] sides) {
		Vec3d start = player.getPositionVector().addVector(0F, player.eyeHeight, 0F);
		Vec3d end = player.getLookVec();
		
		RayTraceResult result = null;
		double dist = -1;
		EnumFacing side = null;
		for(int i = 0; i < sides.length; i++) {
			RayTraceResult temp = this.rayTrace(pos, start, end, AABBs[sides[i].getIndex()]);
			double tempDist = -1;
			if (temp != null && (result == null || (tempDist = temp.hitVec.distanceTo(start)) < dist)) {
				result = temp;
				dist = tempDist;
				side = sides[i];
			}
		}
		return side;
	}
	
	private TileCog getCog(IBlockAccess world, BlockPos pos) {
		return (TileCog) world.getTileEntity(pos);
	}
    
    @Override
    public void postInit() {
    	woodenCog = addVariant(CogType.WOODEN.getMetadata());
    	stoneCog = addVariant(CogType.STONE.getMetadata());
    	ironCog = addVariant(CogType.STONE.getMetadata());
    }
    
    public static ItemStack woodenCog, stoneCog, ironCog;
}
