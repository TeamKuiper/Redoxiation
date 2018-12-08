package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityIronCog extends TileEntity implements ITickable {
	private int scale = 0;
	private float rotation = 0;
	private float angvel = 0;

	@Override
	public void update() {
		rotation += 0.3141592653589793238462643383279f * angvel;
		if (rotation >= 6.283185307179586476925286766559f) {
			rotation = rotation - 6.283185307179586476925286766559f;
		}
	}

	public int getScale() {
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

	// rotation save to NBT
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		rotation = compound.getFloat("rotation");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setFloat("rotation", rotation);
		return compound;
	}

	// Server<->Client
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new SPacketUpdateTileEntity(pos, this.getBlockMetadata(), tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
		this.readFromNBT(tag);
	}
}
