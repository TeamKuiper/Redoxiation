package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TilePipeBase extends TileEntity {

	public EnumFacing[] connections = new EnumFacing[6];

	public TilePipeBase() {

	}

	public void updateEntity() {
		this.updateConnections();
	}

	public void updateConnections() {
		if (this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) != null
				&& this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[0] = EnumFacing.UP;
		} else {
			connections[0] = null;
		}
		if (this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) != null
				&& this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[1] = EnumFacing.DOWN;
		} else {
			connections[1] = null;
		}
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) != null
				&& this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[2] = EnumFacing.NORTH;
		} else {
			connections[2] = null;
		}
		if (this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) != null
				&& this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[3] = EnumFacing.EAST;
		} else {
			connections[3] = null;
		}
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) != null
				&& this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[4] = EnumFacing.SOUTH;
		} else {
			connections[4] = null;
		}
		if (this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) != null
				&& this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
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
