package teamKuiper.redoxiation.blocks;


import codechicken.lib.packet.PacketCustom;
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
import teamKuiper.redoxiation.blocks.tileentity.TileWire;
import teamKuiper.redoxiation.handlers.PacketHandler;

public class Wire extends BlockContainer {

    String name = "wire";

    public static final AxisAlignedBB DEFAULT_AABB = new AxisAlignedBB(1, 1, 1, 1, 1, 1);
    public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.3125f, 0.875f, 0.3125f, 0.6875f, 1.0f, 0.6875f);
    public static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.3125f, 0.0f, 0.3125f, 0.6875f, 0.0625f, 0.6875f);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.3125f, 0.3125f, 0.0f, 0.6875f, 0.6875f, 0.125f);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.3125f, 0.3125f, 0.875f, 0.6875f, 0.6875f, 1.0f);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.875f, 0.3125f, 0.3125f, 1.0f, 0.6875f, 0.6875f);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0f, 0.3125f, 0.3125f, 0.125f, 0.6875f, 0.6875f);

    public Wire() {
        super(Material.ROCK);
        setRegistryName(Redoxiation.MODID, Redoxiation.MODID + "." + name);
        setCreativeTab(Redoxiation.tabRedoxiation);
        setHarvestLevel("pickaxe", 2);
        setHardness(2.0F);
        setResistance(10.0F);
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileWire();
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
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		return side;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        if(world.isRemote)
            new PacketCustom(PacketHandler.channel, 1).sendToServer();
		TileWire tile = (TileWire) world.getTileEntity(pos);
		int check = tile.fill(pos, 0, false, 1);
		tile.setfill(pos, check, true);
    }
    
    public boolean checkstate(BlockPos pos, boolean st, World world) {
		return ((world.getBlockState(pos).getBlock() == RedoxiationBlocks.wire) && (((TileWire) world.getTileEntity(x, y, z)).state() != st));
	}
    
    public void breakBlock(World world, BlockPos pos, Block block, int meta) {
    	
    	int x = pos.getX();
    	int y = pos.getY();
    	int z = pos.getZ();

    	BlockPos pos1 = new BlockPos(x+1, y, z);
    	BlockPos pos2 = new BlockPos(pos2);
    	BlockPos pos3 = new BlockPos(pos3);
    	BlockPos pos4 = new BlockPos(pos4);
    	BlockPos pos5 = new BlockPos(pos5);
    	BlockPos pos6 = new BlockPos(pos6);

		int check = 0;

		if (checkstate(pos1, false, world))
		{
			((TileWire)world.getTileEntity(pos1)).fill(pos1, 0, false, 1);
		}
		if (checkstate(pos2, false, world))
		{
			((TileWire)world.getTileEntity(pos2)).fill(pos2, 0, false, 1);
		}
		if (checkstate(pos3, false, world))
		{
			((TileWire)world.getTileEntity(pos3)).fill(pos3, 0, false, 1);
		}
		if (checkstate(pos4, false, world))
		{
			((TileWire)world.getTileEntity(pos4)).fill(pos4, 0, false, 1);
		}
		if (checkstate(pos5, false, world))
		{
			((TileWire)world.getTileEntity(pos5)).fill(pos5, 0, false, 1);
		}
		if (checkstate(pos6, false, world))
		{
			((TileWire)world.getTileEntity(pos6)).fill(pos6, 0, false, 1);
		}
		
		
		if (checkstate(pos1, true, world))
		{
			check = ((TileWire)world.getTileEntity(pos1)).getchunknumber();
			((TileWire)world.getTileEntity(pos1)).setfill(pos1, check, true);
		}
		if (checkstate(pos2, true, world))
		{
			check = ((TileWire)world.getTileEntity(pos2)).getchunknumber();
			((TileWire)world.getTileEntity(pos2)).setfill(pos2, check, true);
		}
		if (checkstate(pos3, true, world))
		{
			check = ((TileWire)world.getTileEntity(pos3)).getchunknumber();
			((TileWire)world.getTileEntity(pos3)).setfill(pos3, check, true);
		}
		if (checkstate(pos4, true, world))
		{
			check = ((TileWire)world.getTileEntity(pos4)).getchunknumber();
			((TileWire)world.getTileEntity(pos4)).setfill(pos4, check, true);
		}
		if (checkstate(pos5, true, world))
		{
			check = ((TileWire)world.getTileEntity(pos5)).getchunknumber();
			((TileWire)world.getTileEntity(pos5)).setfill(pos5, check, true);
		}
		if (checkstate(pos6, true, world))
		{
			check = ((TileWire)world.getTileEntity(pos6)).getchunknumber();
			((TileWire)world.getTileEntity(pos6)).setfill(pos6, check, true);
		}
		
	}

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
        EnumFacing dir = side.getOpposite();
        return world.isSideSolid(new BlockPos(pos.getX() + dir.getFrontOffsetX(), pos.getY() + dir.getFrontOffsetY(), pos.getZ() + dir.getFrontOffsetZ()), dir.getOpposite());
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
    	int x = pos.getX();
    	int y = pos.getY();
    	int z = pos.getZ();
    	
        return world.isSideSolid(new BlockPos(x - 1, y, z), EnumFacing.EAST, false) ||
                world.isSideSolid(new BlockPos(x + 1, y, z), EnumFacing.WEST, false) ||
                world.isSideSolid(new BlockPos(x, y, z - 1), EnumFacing.SOUTH, false) ||
                world.isSideSolid(new BlockPos(x, y, z + 1), EnumFacing.NORTH, false) ||
                world.isSideSolid(new BlockPos(x, y - 1, z), EnumFacing.UP, false) ||
                world.isSideSolid(new BlockPos(x, y + 1, z), EnumFacing.DOWN, false);
    }

    @Override
    public void onNeighborChange(IBlockAccess worldIn, BlockPos pos, BlockPos neighbor) {
    	if(!(worldIn instanceof World)) {
    		return;
    	}
    	
    	World world = (World) worldIn;
        if (!this.canPlaceBlockAt(world, pos)) {
            this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
            world.setBlockToAir(pos);
        } else {
            EnumFacing side = world.getBlockState(pos).getValue(RedoxiationBlocks.FACING);
            if (!canPlaceBlockOnSide(world, pos, side)) {
                this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
                world.setBlockToAir(pos);
            }
        }
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
}
