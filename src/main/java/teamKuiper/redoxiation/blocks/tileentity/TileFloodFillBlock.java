package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class TileFloodFillBlock extends TileEntity {

	int state;
	int chunknumber;

	public boolean checkstate(int x, int y, int z, int st) {
		return ((worldObj.getBlock(x, y, z) == RedoxiationBlocks.FloodFillBlock) && (((TileFloodFillBlock) worldObj.getTileEntity(x, y, z)).state != st));
	}

	public int state() {
		return state;
	}

	public int fill(int x, int y, int z, int checknum, int st) {
		checknum++;
		TileEntity tile = worldObj.getTileEntity(x, y, z);
		((TileFloodFillBlock) worldObj.getTileEntity(x, y, z)).state = st;
		if (checkstate(x + 1, y, z, st)) {
			checknum = fill(x + 1, y, z, checknum, st);
		}
		if (checkstate(x - 1, y, z, st)) {
			checknum = fill(x - 1, y, z, checknum, st);
		}
		if (checkstate(x, y + 1, z, st)) {
			checknum = fill(x, y + 1, z, checknum, st);
		}
		if (checkstate(x, y - 1, z, st)) {
			checknum = fill(x, y - 1, z, checknum, st);
		}
		if (checkstate(x, y, z + 1, st)) {
			checknum = fill(x, y, z + 1, checknum, st);
		}
		if (checkstate(x, y, z - 1, st)) {
			checknum = fill(x, y, z - 1, checknum, st);
		}
		return checknum;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setInteger("chunknumber", chunknumber);
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		chunknumber = data.getInteger("chunknumber");
	}
}
