package teamKuiper.redoxiation.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.Utils;
import teamKuiper.redoxiation.blocks.tileentity.TileLeadTank;

public class LeadTank extends Block implements ITileEntityProvider {

    String name = "tankLead";
    public static IIcon icon;

    public LeadTank() {
        super(Material.GLASS);
        setRegistryName(Redoxiation.MODID, Redoxiation.MODID + "." + name);
        setCreativeTab(Redoxiation.tabRedoxiation);
    }
 
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return Utils.getTankStackFromTile((TileLeadTank) world.getTileEntity(x, y, z), true);
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileLeadTank();
    }

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public int quantityDropped(Random rnd) {
        return 0;
    }
 
    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int opSide) {
        return 0;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        ItemStack stack = player.inventory.getCurrentItem();
        if (stack != null) {
            FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(stack);
            TileLeadTank tank = (TileLeadTank) world.getTileEntity(x, y, z);
            if (liquid != null) {
                int amount = tank.fill(EnumFacing.UNKNOWN, liquid, false);
                if (amount == liquid.amount) {
                    tank.fill(EnumFacing.UNKNOWN, liquid, true);
                    if (!player.capabilities.isCreativeMode)
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, Utils.useItemSafely(stack));

                    if(tank.tank.getFluid().getFluid().getBlock() != null) {
                    //z world.setLightValue(EnumSkyBlock.Sky., tank.xCoord, tank.xCoord, tank.xCoord, tank.tank.getFluid().getFluid().getBlock().getLightValue());

                    }
                    return true;
                } else
                    return true;
            } else if (FluidContainerRegistry.isBucket(stack)) {
                FluidTankInfo[] tanks = tank.getTankInfo(EnumFacing.UNKNOWN);
                if (tanks[0] != null) {
                    FluidStack fillFluid = tanks[0].fluid;
                    ItemStack fillStack = FluidContainerRegistry.fillFluidContainer(fillFluid, stack);
                    if (fillStack != null) {
                        tank.drain(EnumFacing.UNKNOWN, FluidContainerRegistry.getFluidForFilledItem(fillStack).amount, true);
                        if (!player.capabilities.isCreativeMode) {
                            if (stack.stackSize == 1)
                                player.inventory.setInventorySlotContents(player.inventory.currentItem, fillStack);
                            else {
                                player.inventory.setInventorySlotContents(player.inventory.currentItem, Utils.useItemSafely(stack));
                                if (!player.inventory.addItemStackToInventory(fillStack))
                                    player.dropPlayerItemWithRandomChoice(fillStack, false);
                            }
                        }
                        return true;
                    }
                    else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        if (!player.capabilities.isCreativeMode) {
            TileLeadTank tank = (TileLeadTank) world.getTileEntity(x, y, z);
            Utils.dropStackInWorld(world, x, y, z, Utils.getTankStackFromTile(tank, true));
        }
        return world.setBlockToAir(x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (stack.hasTagCompound()) {
            TileLeadTank tank = (TileLeadTank) world.getTileEntity(x, y, z);
            if (tank != null) {
                NBTTagCompound tagFluid = stack.getTagCompound().getCompoundTag("Fluid");
                if (tagFluid != null) {
                    FluidStack liquid = FluidStack.loadFluidStackFromNBT(tagFluid);
                    tank.tank.setFluid(liquid);
                }
            }
        }
    }

    @Override
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
        Utils.dropStackInWorld(world, x, y, z, Utils.getTankStackFromTile((TileLeadTank) world.getTileEntity(x, y, z), true));
        world.setBlockToAir(x, y, z);
        onBlockDestroyedByExplosion(world, x, y, z, explosion);
    }
}
