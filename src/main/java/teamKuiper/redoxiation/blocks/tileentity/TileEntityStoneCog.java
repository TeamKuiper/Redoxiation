package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStoneCog extends TileEntity {
	private int scale = 0;
	private float rotation = 0;
	private float angvel = 0;

	@Override
	public void updateEntity() {
		rotation += 0.3141592653589793238462643383279f*angvel;
		if(rotation >= 6.283185307179586476925286766559f) {
			rotation = rotation - 6.283185307179586476925286766559f;
		}
	}
	
	public int getScale() {
		return scale;
	}
	
	public void setScale(int argV)	{
		scale = argV;
	}
	
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float argR) {
		rotation = argR;
	}
	
	//rotation save to NBT
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		rotation = compound.getFloat("rotation");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super. writeToNBT(compound);
		compound.setFloat("rotation", rotation);
	}

	//Server<->Client
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		
		return new S35PacketUpdateTileEntity(this.xCoord,this.yCoord,this.zCoord,this.blockMetadata,tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.func_148857_g();
		this.readFromNBT(tag);
	}
}