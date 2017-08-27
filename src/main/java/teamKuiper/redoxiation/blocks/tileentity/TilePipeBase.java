package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TilePipeBase extends TileEntity {

	public ForgeDirection[] connections = new ForgeDirection[6];

	public TilePipeBase() {

	}

	public void updateEntity() {
		this.updateConnections();
	}

	public void updateConnections() {
		if (this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) != null
				&& this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[0] = ForgeDirection.UP;
		} else {
			connections[0] = null;
		}
		if (this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) != null
				&& this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[1] = ForgeDirection.DOWN;
		} else {
			connections[1] = null;
		}
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) != null
				&& this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[2] = ForgeDirection.NORTH;
		} else {
			connections[2] = null;
		}
		if (this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) != null
				&& this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[3] = ForgeDirection.EAST;
		} else {
			connections[3] = null;
		}
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) != null
				&& this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[4] = ForgeDirection.SOUTH;
		} else {
			connections[4] = null;
		}
		if (this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) != null
				&& this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord).getBlockType() == this.worldObj
						.getTileEntity(xCoord, yCoord, zCoord).getBlockType()) {
			connections[5] = ForgeDirection.WEST;
		} else {
			connections[5] = null;
		}
	}
	
	public boolean onlyOneOpposite(ForgeDirection[] directions) {
		ForgeDirection mainDirection = null;
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
	
	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection) {
		if((firstDirection == ForgeDirection.UP && secondDirection == ForgeDirection.DOWN)
				|| (firstDirection == ForgeDirection.DOWN && secondDirection == ForgeDirection.UP)
				|| (firstDirection == ForgeDirection.SOUTH && secondDirection == ForgeDirection.NORTH)
				|| (firstDirection == ForgeDirection.NORTH && secondDirection == ForgeDirection.SOUTH)
				|| (firstDirection == ForgeDirection.EAST && secondDirection == ForgeDirection.WEST)
				|| (firstDirection == ForgeDirection.WEST && secondDirection == ForgeDirection.EAST)) {
			return true;
		}
		return false;
	}
}
