package teamKuiper.redoxiation.blocks.temp;

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
import net.minecraft.util.EnumHand;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileTankLead;
import teamKuiper.redoxiation.utils.temp.Utils;

public class TankLead extends Block implements ITileEntityProvider {

    public static IIcon icon;

    public TankLead() {
        super(Material.GLASS);
        setRegistryName(Redoxiation.MODID, "tankLead");
        setCreativeTab(Redoxiation.tabRedoxiation);
    }
 
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        // TODO Auto-generated method stub
        return Utils.getTankStackFromTile((TileTankLead) world.getTileEntity(pos), true);
    }
    
    @Deprecated
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return false;
    }

    @Deprecated
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return 0;
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileTankLead();
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.inventory.getCurrentItem();
        if (stack != null) {
            FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(stack);
            TileTankLead tank = (TileTankLead) world.getTileEntity(x, y, z);
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
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (!player.capabilities.isCreativeMode) {
            TileTankLead tank = (TileTankLead) world.getTileEntity(pos);
            Utils.dropStackInWorld(world, pos, Utils.getTankStackFromTile(tank, true));
        }
        return world.setBlockToAir(pos);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasTagCompound()) {
            TileTankLead tank = (TileTankLead) world.getTileEntity(pos);
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
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
        Utils.dropStackInWorld(world, pos, Utils.getTankStackFromTile((TileTankLead) world.getTileEntity(pos), true));
        world.setBlockToAir(pos);
        onBlockDestroyedByExplosion(world, pos, explosion);
    }
}
