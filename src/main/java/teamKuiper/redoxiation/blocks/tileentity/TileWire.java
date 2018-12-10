package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class TileWire extends TileEntity implements ITickable {
    private float scale = 1.0f;
    private float rotation = 0;
    private float angVel = 0;
    private boolean state;
    private int chunkNumber = 0;
    private float multiply = 1;

    @Override
    public void update() {
        if (chunkNumber != 0) {
            angVel = multiply / ((float) chunkNumber);
        } else {
            angVel = 0;
        }
        IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 2);
        if (world.isRemote) {
            rotation += angVel;
        }
        if (rotation >= 360) {
            rotation = rotation - 360;
        }
        else if (rotation <0) {
        	rotation = rotation + 360;
        }
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float argV) {
        this.scale = argV;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float argR) {
        this.rotation = argR;
    }
    
    public float getMultiply() {
        return multiply;
    }

    public void setMultiply(float argR) {
    	this.multiply = argR;
    }

    public int getChunkNumber() {
        return chunkNumber;
    }

    public void setChunkNumber(int chunkNumber) {
        this.chunkNumber = chunkNumber;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
    public float getAngVel() {
        return angVel;
    }
    
    public void setAngVel(float angVel) {
        this.angVel = angVel;
    }

    public EnumFacing getSide() {
        return world.getBlockState(pos).getValue(RedoxiationBlocks.FACING);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        angVel = compound.getFloat("angVel");
        multiply = compound.getFloat("multiply");
        int chunk = compound.getInteger("chunkNumber");
        if (chunk == chunkNumber)
        {
        	chunkNumber = chunk;
        }
        else
        {
        	chunkNumber = chunk;
        	rotation = 0;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setFloat("angVel", angVel);
        compound.setFloat("multiply", multiply);
        compound.setInteger("chunkNumber", chunkNumber);
        
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
    public boolean checkState(BlockPos pos, boolean st) {
        return ((world.getBlockState(pos).getBlock() == RedoxiationBlocks.wire) && (((TileWire) world.getTileEntity(pos)).state != st));
    }
    
    public boolean checkStuck(BlockPos pos, boolean st, float mul) {
    	return ((world.getBlockState(pos).getBlock() == RedoxiationBlocks.wire) && (((TileWire) world.getTileEntity(pos)).state != st) && (((TileWire) world.getTileEntity(pos)).multiply != -mul));
    }

    public boolean state() {
        return state;
    }

    public int fill(BlockPos pos, int checknum, boolean st, float mul) {
    	if (checknum == -1)
    	{
    		checknum = -1;
    	}
    	else
    	{
    		checknum++;
    	}
        TileWire tile = (TileWire) world.getTileEntity(pos);
        tile.state = st;
        tile.setMultiply(-mul);
        
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        
        BlockPos pos1 = new BlockPos(x+1, y, z);
        BlockPos pos2 = new BlockPos(x-1, y, z);
        BlockPos pos3 = new BlockPos(x, y+1, z);
        BlockPos pos4 = new BlockPos(x, y-1, z);
        BlockPos pos5 = new BlockPos(x, y, z+1);
        BlockPos pos6 = new BlockPos(x, y, z-1);
        
        if (checkStuck(pos1, !st, tile.multiply)) {
        	checknum = -1;
        }
        else if (checkStuck(pos2, !st, tile.multiply)) {
        	checknum = -1;
        }
        else if (checkStuck(pos3, !st, tile.multiply)) {
        	checknum = -1;
        }
        else if (checkStuck(pos4, !st, tile.multiply)) {
        	checknum = -1;
        }
        else if (checkStuck(pos5, !st, tile.multiply)) {
        	checknum = -1;
        }
        else if (checkStuck(pos6, !st, tile.multiply)) {
        	checknum = -1;
        }
        if (checkState(pos1, st)) {
            checknum = fill(pos1, checknum, st, tile.multiply);
        }
        if (checkState(pos2, st)) {
            checknum = fill(pos2, checknum, st, tile.multiply);
        }
        if (checkState(pos3, st)) {
            checknum = fill(pos3, checknum, st, tile.multiply);
        }
        if (checkState(pos4, st)) {
            checknum = fill(pos4, checknum, st, tile.multiply);
        }
        if (checkState(pos5, st)) {
            checknum = fill(pos5, checknum, st, tile.multiply);
        }
        if (checkState(pos6, st)) {
            checknum = fill(pos6, checknum, st, tile.multiply);
        }
        tile.setChunkNumber(checknum);
        return checknum;
    }

    public int setFill(BlockPos pos, int checknum, boolean st) {
        TileWire tile = (TileWire) world.getTileEntity(pos);
        tile.setState(st);
        tile.setChunkNumber(checknum);
        
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        
        BlockPos pos1 = new BlockPos(x+1, y, z);
        BlockPos pos2 = new BlockPos(x-1, y, z);
        BlockPos pos3 = new BlockPos(x, y+1, z);
        BlockPos pos4 = new BlockPos(x, y-1, z);
        BlockPos pos5 = new BlockPos(x, y, z+1);
        BlockPos pos6 = new BlockPos(x, y, z-1);
        
        if (checknum == -1)
        {
        	tile.setMultiply(0);
        }
        if (checkState(pos1, st)) {
            setFill(pos1, checknum, st);
        }
        if (checkState(pos2, st)) {
        	setFill(pos2, checknum, st);
        }
        if (checkState(pos3, st)) {
        	setFill(pos3, checknum, st);
        }
        if (checkState(pos4, st)) {
        	setFill(pos4, checknum, st);
        }
        if (checkState(pos5, st)) {
        	setFill(pos5, checknum, st);
        }
        if (checkState(pos6, st)) {
            setFill(pos6, checknum, st);
        }
        return checknum;
    }
}
