package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class TileEntityWoodenCog extends TileEntity {
	private float scale = 1.0f;
	private float rotation = 0;
	private float angvel = 0;
	private int state;
	private int chunknumber = 0;

	@Override
	public void updateEntity() {
		if (chunknumber != 0) {
			angvel = 1 / ((float) chunknumber);
		} else {
			angvel = 0;
		}
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		if (worldObj.isRemote) {
			rotation += angvel;
		}
		if (rotation >= 360) {
			rotation = rotation - 360;
		}
	}

	public float getScale() {
		return scale;
	}

	public void setScale(int argV) {
		scale = argV;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float argR) {
		rotation = argR;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		angvel = compound.getFloat("angvel");
		chunknumber = compound.getInteger("chunknumber");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setFloat("angvel", angvel);
		compound.setInteger("chunknumber", chunknumber);
	}

	// Server<->Client
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.func_148857_g();
		this.readFromNBT(tag);
	}

	// FloodFill
	public boolean checkstate(int x, int y, int z, int st) {
		return ((worldObj.getBlock(x, y, z) == RedoxiationBlocks.woodenCog) && (((TileEntityWoodenCog) worldObj.getTileEntity(x, y, z)).state != st));
	}

	public int state() {
		return state;
	}

	public int fill(int x, int y, int z, int checknum, int st) {
		checknum++;
		TileEntityWoodenCog tile = (TileEntityWoodenCog) worldObj.getTileEntity(x, y, z);
		tile.state = st;
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
		
		tile.setchunknumber(checknum);
		return checknum;
	}

	public int setfill(int x, int y, int z, int checknum, int st, World world) {
		TileEntityWoodenCog tile = (TileEntityWoodenCog) world.getTileEntity(x, y, z);
		tile.setstate(st);
		tile.setchunknumber(checknum);
		
		if (checkstate(x + 1, y, z, st)) {
			setfill(x + 1, y, z, checknum, st, world);
		}
		if (checkstate(x - 1, y, z, st)) {
			setfill(x - 1, y, z, checknum, st, world);
		}
		if (checkstate(x, y + 1, z, st)) {
			setfill(x, y + 1, z, checknum, st, world);
		}
		if (checkstate(x, y - 1, z, st)) {
			setfill(x, y - 1, z, checknum, st, world);
		}
		if (checkstate(x, y, z + 1, st)) {
			setfill(x, y, z + 1, checknum, st, world);
		}
		if (checkstate(x, y, z - 1, st)) {
			setfill(x, y, z - 1, checknum, st, world);
		}
		return checknum;
	}

	public int chunknumber() {
		return chunknumber;
	}

	public void setchunknumber(int chunknumber) {
		this.chunknumber = chunknumber;
	}

	public void setstate(int state) {
		this.state = state;
	}
}