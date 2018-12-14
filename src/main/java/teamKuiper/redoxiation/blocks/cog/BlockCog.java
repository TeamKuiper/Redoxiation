package teamKuiper.redoxiation.blocks.cog;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
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
import teamKuiper.redoxiation.blocks.rocks.BlockNetherOre.OreType;
import teamKuiper.redoxiation.utils.IBlockBoundCustomizable;
import teamKuiper.redoxiation.utils.IInitializable;

public class BlockCog extends BlockContainer implements IInitializable, IBlockBoundCustomizable {

    public static final PropertyEnum<CogType> DOWN = PropertyEnum.create("down", CogType.class);
    public static final PropertyEnum<CogType> UP = PropertyEnum.create("up", CogType.class);
    public static final PropertyEnum<CogType> NORTH = PropertyEnum.create("north", CogType.class);
    public static final PropertyEnum<CogType> SOUTH = PropertyEnum.create("south", CogType.class);
    public static final PropertyEnum<CogType> WEST = PropertyEnum.create("west", CogType.class);
    public static final PropertyEnum<CogType> EAST = PropertyEnum.create("east", CogType.class);
    public static final PropertyEnum<?>[] SIDE_PROPERTIES = {DOWN, UP, NORTH, SOUTH, WEST, EAST};
    
    //public static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(1, 1, 1, 1, 1, 1);
    public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.3125f, 0.0f, 0.3125f, 0.6875f, 0.0625f, 0.6875f);
    public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.3125f, 0.9375f, 0.3125f, 0.6875f, 1.0f, 0.6875f);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.3125f, 0.3125f, 0.9375f, 0.6875f, 0.6875f, 1.0f);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.3125f, 0.3125f, 0.0f, 0.6875f, 0.6875f, 0.0625f);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0f, 0.3125f, 0.3125f, 0.0625f, 0.6875f, 0.6875f);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.9375f, 0.3125f, 0.3125f, 1.0f, 0.6875f, 0.6875f);
    public static final AxisAlignedBB[] AABBs = {DOWN_AABB, UP_AABB, NORTH_AABB, SOUTH_AABB, WEST_AABB, EAST_AABB};
    
    public static final ItemStack[] COGs = new ItemStack[CogType.values().length-1];
    
    public BlockCog() {
        super(Material.ROCK);
        setRegistryName(Redoxiation.MODID, "cog");
        setCreativeTab(Redoxiation.tabRedoxiation);
        setHarvestLevel("pickaxe", 2);
        setHardness(2.0F);
        setResistance(10.0F);
    }
    
    @Override
    public void init() {
		for (int i = 0; i < OreType.values().length-1; i++) {
			if(CogType.values()[i] == CogType.NONE) continue;
			COGs[i] = new ItemStack(this, 1, i);
		}
    }
    
    @Override
    public AxisAlignedBB[] getDrawAABBs(World world, EntityPlayer player, BlockPos pos, IBlockState state) {
    	return new AxisAlignedBB[] {AABBs[getLookingSide(player, pos, state).getIndex()]};
    }

    public TileEntity createNewTileEntity(World world, int meta) {
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
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		for (int i = 0; i < OreType.values().length-1; i++) {
			if(CogType.values()[i] == CogType.NONE) continue;
			items.add(new ItemStack(this, 1, i));
		}
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
	@SuppressWarnings("unchecked")
	@Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		for (int i = 0; i < SIDE_PROPERTIES.length; i++) {
			if(state.getValue((PropertyEnum<CogType>) SIDE_PROPERTIES[i]) != CogType.NONE) {
		        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABBs[i]);
			}
		}
    }

	//Returns true if there is no cog in the given side and the block of the given side is solid.
    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
    	if(world.getBlockState(pos).getValue(SIDE_PROPERTIES[side.getIndex()]) != CogType.NONE)
    		return false;
    	EnumFacing oppositeSide = side.getOpposite();
    	BlockPos blockPos = new BlockPos(pos.getX() + oppositeSide.getFrontOffsetX(), pos.getY() + oppositeSide.getFrontOffsetY(), pos.getZ() + oppositeSide.getFrontOffsetZ());
        return world.isSideSolid(blockPos, side);
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
    @SuppressWarnings("unchecked")
	@Override
    public void onNeighborChange(IBlockAccess worldIn, BlockPos pos, BlockPos neighbor) {
    	if(worldIn instanceof World) {
    		World world = (World) worldIn;
			IBlockState state = world.getBlockState(pos);
			for (int i = 0; i < SIDE_PROPERTIES.length; i++) {
				CogType type = state.getValue((PropertyEnum<CogType>) SIDE_PROPERTIES[i]);
				EnumFacing side = EnumFacing.getFront(i);
				if (type != CogType.NONE && !canPlaceBlockOnSide(world, pos, side)) {
					destroySide(world, pos, state, EnumFacing.getFront(i), true);
					Block.spawnAsEntity(world, pos, getItemStackBySide(state, side));
				}
	    	}
    	}
    }
    
    /**
     * Place the cog in the given side.
     * Returns true if succeed.
     * @param state
     * @param side
     * @param newType
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean placeSide(IBlockState state, EnumFacing side, CogType newType) {
    	PropertyEnum<CogType> property = (PropertyEnum<CogType>) SIDE_PROPERTIES[side.getIndex()];
		CogType type = state.getValue(property);
		if(type != CogType.NONE) return false;
		state.withProperty(property, newType);
		return true;
    }
    
    /**
     * Destroys the cog which is in the given side.
     * Returns true if succeed.
     * @param world
     * @param pos
     * @param state
     * @param side
     * @param dropItem
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean destroySide(World world, BlockPos pos, IBlockState state, EnumFacing side, boolean dropItem) {
    	PropertyEnum<CogType> property = (PropertyEnum<CogType>) SIDE_PROPERTIES[side.getIndex()];
		CogType type = state.getValue(property);
		if(type == CogType.NONE) return false;
		state.withProperty(property, CogType.NONE);
		removeIfEmpty(world, pos, state);
		return true;
    }

    /**
     * Remove the block if there is no cog in the block.
     * @param world
     * @param pos
     * @param state
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean removeIfEmpty(World world, BlockPos pos, IBlockState state) {
		for(int i = 0; i < SIDE_PROPERTIES.length; i++) {
			if(state.getValue((PropertyEnum<CogType>) SIDE_PROPERTIES[i]) != CogType.NONE)
				return false;
		}
		world.setBlockToAir(pos);
		return true;
    }

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, SIDE_PROPERTIES);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState();
		CogType[] types = CogType.values();
		
		for(int i = SIDE_PROPERTIES.length - 1; i >= 0; i--) {
			state.withProperty((PropertyEnum<CogType>) SIDE_PROPERTIES[i], types[meta%4]);
			meta >>= 2;
		}
		
		return state;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getMetaFromState(IBlockState state) {
		int metadata = 0;
		
		for(int i = 0; i < SIDE_PROPERTIES.length; i++) {
			metadata <<= 2;
			metadata += state.getValue((PropertyEnum<CogType>) SIDE_PROPERTIES[i]).getValue();
		}
		
		return metadata;
	}

	//Returns ItemStack of cogs in all sides.
	@SuppressWarnings("unchecked")
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		int[] valueCount = new int[CogType.values().length];
		for(int i = 0; i < SIDE_PROPERTIES.length; i++) {
			int value = state.getValue((PropertyEnum<CogType>) SIDE_PROPERTIES[i]).getValue();
			valueCount[value]++;
		}
		for(int i = 0; i < valueCount.length; i++) {
			if(CogType.values()[i] == CogType.NONE) continue;
			ItemStack stack = new ItemStack(Item.getItemFromBlock(this), valueCount[i], i);
			drops.add(stack);
		}
	}
	
	//Returns the ItemStack of the cog which player is lokking.
	@SuppressWarnings("unchecked")
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		EnumFacing facing = getLookingSide(player, pos, state);
		if(facing == null) return null;
		CogType type = state.getValue((PropertyEnum<CogType>) SIDE_PROPERTIES[facing.getIndex()]);
		if(type == CogType.NONE) return null;
		ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
		stack.setItemDamage(type.getValue());
		return stack;
	}
	
	//When player breaks the cog, only destroys the cog which player is looking.
	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest){
		EnumFacing side = getLookingSide(player, pos, state);
		if(side != null) {
			destroySide(world, pos, state, side, true);
			return true;
		}
		return false;
    }
	
	/**
	 * Returns Itemstack of the cog which are in the given side.
	 * @param state
	 * @param side
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ItemStack getItemStackBySide(IBlockState state, EnumFacing side) {
    	CogType type = state.getValue((PropertyEnum<CogType>) SIDE_PROPERTIES[side.getIndex()]);
    	if(type == CogType.NONE)
    		return null;
    	return new ItemStack(this, 1, type.getValue());
	}
	
	public EnumFacing getLookingSide(EntityPlayer player, BlockPos pos, IBlockState state) {
		return findLookingSide(player, pos, getPlacedBlockSides(state));
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
	
	/**
	 * Returns the sides which cog exists.
	 * @param state
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public EnumFacing[] getPlacedBlockSides(IBlockState state) {
		List<EnumFacing> sides = new ArrayList<EnumFacing>();
		for(int i = 0; i < SIDE_PROPERTIES.length; i++) {
			if(state.getValue((PropertyEnum<CogType>) SIDE_PROPERTIES[i]) != CogType.NONE) {
				sides.add(EnumFacing.getFront(i));
			}
		}
		return sides.toArray(new EnumFacing[0]);
	}
}
