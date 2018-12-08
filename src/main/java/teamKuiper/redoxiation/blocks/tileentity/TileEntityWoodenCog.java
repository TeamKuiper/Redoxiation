package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class TileEntityWoodenCog extends TileEntity implements ITickable {
	private float scale = 1.0f;
	private float rotation = 0;
	private float angvel = 0;
	private int state;
	private int chunknumber = 0;

	@Override
	public void update() {
		if (chunknumber != 0) {
			angvel = 1 / ((float) chunknumber);
		} else {
			angvel = 0;
		}
		world.markBlockForUpdate(this.pos);
		if (world.isRemote) {
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
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setFloat("angvel", angvel);
		compound.setInteger("chunknumber", chunknumber);
		return compound;
	}

	// Server<->Client
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new SPacketUpdateTileEntity(pos, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
		this.readFromNBT(tag);
	}

	// FloodFill
	public boolean checkState(BlockPos pos, int st) {
		return ((world.getBlockState(pos).getBlock() == RedoxiationBlocks.woodenCog) && (((TileEntityWoodenCog) world.getTileEntity(pos)).state != st));
	}

	public int state() {
		return state;
	}

	public int fill(BlockPos pos, int checknum, int st) {
		checknum++;
		TileEntityWoodenCog tile = (TileEntityWoodenCog) world.getTileEntity(pos);
		tile.state = st;
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		BlockPos pos1 = new BlockPos(x + 1, y, z);
		BlockPos pos2 = new BlockPos(x - 1, y, z);
		BlockPos pos3 = new BlockPos(x, y + 1, z);
		BlockPos pos4 = new BlockPos(x, y - 1, z);
		BlockPos pos5 = new BlockPos(x, y, z + 1);
		BlockPos pos6 = new BlockPos(x, y, z - 1);
		
		if (checkState(pos1, st)) {
			checknum = fill(pos1, checknum, st);
		}
		if (checkState(pos2, st)) {
			checknum = fill(pos2, checknum, st);
		}
		if (checkState(pos3, st)) {
			checknum = fill(pos3, checknum, st);
		}
		if (checkState(pos4, st)) {
			checknum = fill(pos4, checknum, st);
		}
		if (checkState(pos5, st)) {
			checknum = fill(pos5, checknum, st);
		}
		if (checkState(pos6, st)) {
			checknum = fill(pos6, checknum, st);
		}
		
		tile.setchunknumber(checknum);
		return checknum;
	}

	public int setFill(BlockPos pos, int checknum, int st, World world) {
		TileEntityWoodenCog tile = (TileEntityWoodenCog) world.getTileEntity(pos);
		tile.setstate(st);
		tile.setchunknumber(checknum);
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		BlockPos pos1 = new BlockPos(x + 1, y, z);
		BlockPos pos2 = new BlockPos(x - 1, y, z);
		BlockPos pos3 = new BlockPos(x, y + 1, z);
		BlockPos pos4 = new BlockPos(x, y - 1, z);
		BlockPos pos5 = new BlockPos(x, y, z + 1);
		BlockPos pos6 = new BlockPos(x, y, z - 1);
		
		if (checkState(pos1, st)) {
			setFill(pos1, checknum, st, world);
		}
		if (checkState(pos2, st)) {
			setFill(pos2, checknum, st, world);
		}
		if (checkState(pos3, st)) {
			setFill(pos3, checknum, st, world);
		}
		if (checkState(pos4, st)) {
			setFill(pos4, checknum, st, world);
		}
		if (checkState(pos5, st)) {
			setFill(pos5, checknum, st, world);
		}
		if (checkState(pos6, st)) {
			setFill(pos6, checknum, st, world);
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