package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TilePipeBase extends TileEntity {

	public EnumFacing[] connections = new EnumFacing[6];

	public TilePipeBase() {

	}

	public void updateEntity() {
		this.updateConnections();
	}

	public void updateConnections() {
		int xCoord = pos.getX();
		int yCoord = pos.getY();
		int zCoord = pos.getZ();
		
		BlockPos pos1 = new BlockPos(xCoord, yCoord + 1, zCoord);
		BlockPos pos2 = new BlockPos(xCoord, yCoord - 1, zCoord);
		BlockPos pos3 = new BlockPos(xCoord, yCoord, zCoord - 1);
		BlockPos pos4 = new BlockPos(xCoord + 1, yCoord, zCoord);
		BlockPos pos5 = new BlockPos(xCoord, yCoord, zCoord + 1);
		BlockPos pos6 = new BlockPos(xCoord - 1, yCoord, zCoord);
		
		if (this.world.getTileEntity(pos1) != null
				&& this.world.getTileEntity(pos1).getBlockType() == this.world
						.getTileEntity(pos).getBlockType()) {
			connections[0] = EnumFacing.UP;
		} else {
			connections[0] = null;
		}
		if (this.world.getTileEntity(pos2) != null
				&& this.world.getTileEntity(pos2).getBlockType() == this.world
						.getTileEntity(pos).getBlockType()) {
			connections[1] = EnumFacing.DOWN;
		} else {
			connections[1] = null;
		}
		if (this.world.getTileEntity(pos3) != null
				&& this.world.getTileEntity(pos3).getBlockType() == this.world
						.getTileEntity(pos).getBlockType()) {
			connections[2] = EnumFacing.NORTH;
		} else {
			connections[2] = null;
		}
		if (this.world.getTileEntity(pos4) != null
				&& this.world.getTileEntity(pos4).getBlockType() == this.world
						.getTileEntity(pos).getBlockType()) {
			connections[3] = EnumFacing.EAST;
		} else {
			connections[3] = null;
		}
		if (this.world.getTileEntity(pos5) != null
				&& this.world.getTileEntity(pos5).getBlockType() == this.world
						.getTileEntity(pos).getBlockType()) {
			connections[4] = EnumFacing.SOUTH;
		} else {
			connections[4] = null;
		}
		if (this.world.getTileEntity(pos6) != null
				&& this.world.getTileEntity(pos6).getBlockType() == this.world
						.getTileEntity(pos).getBlockType()) {
			connections[5] = EnumFacing.WEST;
		} else {
			connections[5] = null;
		}
	}
	
	public boolean onlyOneOpposite(EnumFacing[] directions) {
		EnumFacing mainDirection = null;
		boolean isOpposite = false;
		
		for(int x = 0; x < directions.length; x++) {
			if(mainDirection == null && directions[x] != null) {
				mainDirection = directions[x];
			}
			
			if(directions[x] != null && mainDirection != directions[x]) {
				if(!isOpposite(mainDirection, directions[x])) {
					return false;
				} else {
					isOpposite = true;
				}
			}
		}
		
		return isOpposite;
	}
	
	public boolean isOpposite(EnumFacing firstDirection, EnumFacing secondDirection) {
		if((firstDirection == EnumFacing.UP && secondDirection == EnumFacing.DOWN)
				|| (firstDirection == EnumFacing.DOWN && secondDirection == EnumFacing.UP)
				|| (firstDirection == EnumFacing.SOUTH && secondDirection == EnumFacing.NORTH)
				|| (firstDirection == EnumFacing.NORTH && secondDirection == EnumFacing.SOUTH)
				|| (firstDirection == EnumFacing.EAST && secondDirection == EnumFacing.WEST)
				|| (firstDirection == EnumFacing.WEST && secondDirection == EnumFacing.EAST)) {
			return true;
		}
		return false;
	}
}