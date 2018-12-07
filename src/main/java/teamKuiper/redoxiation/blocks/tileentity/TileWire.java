package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class TileWire extends TileEntity {
    private float scale = 1.0f;
    private float rotation = 0;
    private float angvel = 0;
    private boolean state;
    private int chunknumber = 0;
    private float multiply = 1;

    @Override
    public void updateEntity() {
        if (chunknumber != 0) {
            angvel = multiply / ((float) chunknumber);
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
        else if (rotation <0) {
        	rotation = rotation + 360;
        }
    }

    public float getscale() {
        return scale;
    }

    public void setscale(float argV) {
        this.scale = argV;
    }

    public float getrotation() {
        return rotation;
    }

    public void setrotation(float argR) {
        this.rotation = argR;
    }
    
    public float getmultiply() {
        return multiply;
    }

    public void setmultiply(float argR) {
    	this.multiply = argR;
    }

    public int getchunknumber() {
        return chunknumber;
    }

    public void setchunknumber(int chunknumber) {
        this.chunknumber = chunknumber;
    }

    public boolean getstate() {
        return state;
    }

    public void setstate(boolean state) {
        this.state = state;
    }
    public float getangvel() {
        return angvel;
    }
    public void setangvel(float angvel) {
        this.angvel = angvel;
    }

    public int getside() {
        return worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        angvel = compound.getFloat("angvel");
        multiply = compound.getFloat("multiply");
        int chunk = compound.getInteger("chunknumber");
        if (chunk == chunknumber)
        {
        	chunknumber = chunk;
        }
        else
        {
        	chunknumber = chunk;
        	rotation = 0;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setFloat("angvel", angvel);
        compound.setFloat("multiply", multiply);
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
    public boolean checkstate(int x, int y, int z, boolean st) {
        return ((worldObj.getBlock(x, y, z) == RedoxiationBlocks.wire) && (((TileWire) worldObj.getTileEntity(x, y, z)).state != st));
    }
    
    public boolean checkstuck(int x, int y, int z, boolean st, float mul) {
    	return ((worldObj.getBlock(x, y, z) == RedoxiationBlocks.wire) && (((TileWire) worldObj.getTileEntity(x, y, z)).state != st) && (((TileWire) worldObj.getTileEntity(x, y, z)).multiply != -mul));
    }

    public boolean state() {
        return state;
    }

    public int fill(int x, int y, int z, int checknum, boolean st, float mul) {
    	if (checknum == -1)
    	{
    		checknum = -1;
    	}
    	else
    	{
    		checknum++;
    	}
        TileWire tile = (TileWire) worldObj.getTileEntity(x, y, z);
        tile.state = st;
        tile.setmultiply(-mul);
        if (checkstuck(x+1, y, z, !st, tile.multiply)) {
        	checknum = -1;
        }
        else if (checkstuck(x-1, y, z, !st, tile.multiply)) {
        	checknum = -1;
        }
        else if (checkstuck(x, y+1, z, !st, tile.multiply)) {
        	checknum = -1;
        }
        else if (checkstuck(x, y-1, z, !st, tile.multiply)) {
        	checknum = -1;
        }
        else if (checkstuck(x, y, z+1, !st, tile.multiply)) {
        	checknum = -1;
        }
        else if (checkstuck(x, y, z-1, !st, tile.multiply)) {
        	checknum = -1;
        }
        if (checkstate(x + 1, y, z, st)) {
            checknum = fill(x + 1, y, z, checknum, st, tile.multiply);
        }
        if (checkstate(x - 1, y, z, st)) {
            checknum = fill(x - 1, y, z, checknum, st, tile.multiply);
        }
        if (checkstate(x, y + 1, z, st)) {
            checknum = fill(x, y + 1, z, checknum, st, tile.multiply);
        }
        if (checkstate(x, y - 1, z, st)) {
            checknum = fill(x, y - 1, z, checknum, st, tile.multiply);
        }
        if (checkstate(x, y, z + 1, st)) {
            checknum = fill(x, y, z + 1, checknum, st, tile.multiply);
        }
        if (checkstate(x, y, z - 1, st)) {
            checknum = fill(x, y, z - 1, checknum, st, tile.multiply);
        }
        tile.setchunknumber(checknum);
        return checknum;
    }

    public int setfill(int x, int y, int z, int checknum, boolean st) {
        TileWire tile = (TileWire) worldObj.getTileEntity(x, y, z);
        tile.setstate(st);
        tile.setchunknumber(checknum);
        if (checknum == -1)
        {
        	tile.setmultiply(0);
        }
        if (checkstate(x + 1, y, z, st)) {
            setfill(x + 1, y, z, checknum, st);
        }
        if (checkstate(x - 1, y, z, st)) {
            setfill(x - 1, y, z, checknum, st);
        }
        if (checkstate(x, y + 1, z, st)) {
            setfill(x, y + 1, z, checknum, st);
        }
        if (checkstate(x, y - 1, z, st)) {
            setfill(x, y - 1, z, checknum, st);
        }
        if (checkstate(x, y, z + 1, st)) {
            setfill(x, y, z + 1, checknum, st);
        }
        if (checkstate(x, y, z - 1, st)) {
            setfill(x, y, z - 1, checknum, st);
        }
        return checknum;
    }
}
