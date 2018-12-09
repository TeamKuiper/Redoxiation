package teamKuiper.redoxiation.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.advancement.RedoxiationAdvancement;
import teamKuiper.redoxiation.blocks.gui.GUIs;
import teamKuiper.redoxiation.blocks.tileentity.TileMachineBase;

public class MachineBlockBase extends BlockContainer {

	Random random = new Random();
	Class<? extends TileMachineBase> machineTile;

	protected MachineBlockBase(Material p_i45386_1_, Class<? extends TileMachineBase> tile) {
		super(p_i45386_1_);
		machineTile = tile;
	}

	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && machineTile.isInstance(tile)) {
			TileMachineBase multiBlock = (TileMachineBase) tile;
			if (multiBlock.hasMaster()) {
				if (multiBlock.isMaster()) {
					if (!multiBlock.checkMultiBlockForm())
						multiBlock.resetStructure();
				} else {
					if (!multiBlock.checkForMaster()) {
						TileMachineBase master = (TileMachineBase) world.getTileEntity(multiBlock.getMasterPos());
						master.resetStructure();
					}
				}
			}
		}
		super.onNeighborChange(world, pos, neighbor);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);
		if ((tile != null) && (tile.getClass().isInstance(machineTile))) {
			TileMachineBase multiBlock = (TileMachineBase) tile;
			if (multiBlock.isMaster()) {
				multiBlock.resetStructure();
			}
		}
		TileMachineBase tileMachine = (TileMachineBase) world.getTileEntity(pos);

		if (tileMachine != null) {
			for (int i1 = 0; i1 < tileMachine.getSizeInventory(); ++i1) {
				ItemStack itemstack = tileMachine.getStackInSlot(i1);

				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					int stackSize = itemstack.getCount();
					for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; stackSize > 0; world.spawnEntityInWorld(entityitem)) {
						int j1 = this.random.nextInt(21) + 10;

						if (j1 > stackSize) {
							j1 = stackSize;
						}

						itemstack.setCount(stackSize - j1);
						entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1,itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}

		super.breakBlock(world, x, y, z, block, meta);
	}
        

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (machineTile.isInstance(world.getTileEntity(pos))) {
				TileMachineBase tile = (TileMachineBase) world.getTileEntity(pos);
				if (tile.hasmastercheck) {
					BlockPos masterPos = tile.getMasterPos();
					player.openGui(Redoxiation.instance, GUIs.BlastFurnaceBlock.ordinal(), world, masterPos.getX(), masterPos.getY(), masterPos.getZ());
					RedoxiationAdvancement.triggerAchievement(player, "redoxiation.blast");
					return true;
				}
			}
			return false;
		}
		return true;
	}
}
