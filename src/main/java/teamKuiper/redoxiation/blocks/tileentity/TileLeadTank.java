package teamKuiper.redoxiation.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
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

    public TileLeadTank(int x, int y, int z) {
        worldObj.getTileEntity(x, y, z);

    }

    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        needsUpdate = true;
        return this.tank.fill(resource, doFill);
    }

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return this.tank.drain(resource.amount, doDrain);
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        needsUpdate = true;
        return this.tank.drain(maxDrain, doDrain);
    }

    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
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
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tank.writeToNBT(tag);
        writeCustomNBT(tag);
    }

    private void writeCustomNBT(NBTTagCompound tag) {
        NBTTagCompound comp = new NBTTagCompound();
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.func_148857_g();
        readFromNBT(tag);
    }
}