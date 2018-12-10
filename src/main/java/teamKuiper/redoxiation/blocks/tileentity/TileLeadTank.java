package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TileLeadTank extends TileEntity implements IFluidHandler {

    public FluidTank tank = new FluidTank(20000);
    private boolean needsUpdate = false;
    private int updateTimer = 0;

    public TileLeadTank() {}

    public TileLeadTank(BlockPos pos) {
        world.getTileEntity(pos);

    }

    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        needsUpdate = true;
        return this.tank.fill(resource, doFill);
    }

    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
        return this.tank.drain(resource.amount, doDrain);
    }

    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
        needsUpdate = true;
        return this.tank.drain(maxDrain, doDrain);
    }

    public boolean canFill(EnumFacing from, Fluid fluid) {
        return true;
    }

    public boolean canDrain(EnumFacing from, Fluid fluid) {
        return true;
    }

    public FluidTankInfo[] getTankInfo(EnumFacing from) {
        return new FluidTankInfo[] {this.tank.getInfo()};
    }

    public float getAdjustedVolume() {
        float amount = tank.getFluidAmount();
        float capacity = tank.getCapacity();
        float volume = (amount/capacity)*0.8F;
        return volume;
    }

    public void updateEntity() {
        needsUpdate = true;
        if (needsUpdate) {
            if (updateTimer == 0) {
                updateTimer = 16; // every 1 ticks it will send an update
                } else {
                --updateTimer;
                if (updateTimer == 0) {
                	IBlockState state = world.getBlockState(pos);
                    world.notifyBlockUpdate(pos, state, state, 2);
                    needsUpdate = false;
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        tank.readFromNBT(tag);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
    	tag = super.writeToNBT(tag);
        tank.writeToNBT(tag);
        writeCustomNBT(tag);
        return tag;
    }

    private void writeCustomNBT(NBTTagCompound tag) {
        NBTTagCompound comp = new NBTTagCompound();
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new SPacketUpdateTileEntity(pos, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.getNbtCompound();
        readFromNBT(tag);
    }
}