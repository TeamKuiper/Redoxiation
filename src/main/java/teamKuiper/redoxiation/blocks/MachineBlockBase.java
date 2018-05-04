package teamKuiper.redoxiation.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.achievement.RedoxiationAchievements;
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
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && machineTile.isInstance(tile)) {
			TileMachineBase multiBlock = (TileMachineBase) tile;
			if (multiBlock.hasMaster()) {
				if (multiBlock.isMaster()) {
					if (!multiBlock.checkMultiBlockForm())
						multiBlock.resetStructure();
				} else {
					if (!multiBlock.checkForMaster()) {
						TileMachineBase master = (TileMachineBase) world.getTileEntity(multiBlock.getMasterX(), multiBlock.getMasterY(), multiBlock.getMasterZ());
						master.resetStructure();
					}
				}
			}
		}
		super.onNeighborBlockChange(world, x, y, z, block);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if ((tile != null) && (tile.getClass().isInstance(machineTile))) {
			TileMachineBase multiBlock = (TileMachineBase) tile;
			if (multiBlock.isMaster()) {
				multiBlock.resetStructure();
			}
		}
		TileMachineBase tileMachine = (TileMachineBase) world.getTileEntity(x, y, z);

		if (tileMachine != null) {
			for (int i1 = 0; i1 < tileMachine.getSizeInventory(); ++i1) {
				ItemStack itemstack = tileMachine.getStackInSlot(i1);

				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
						int j1 = this.random.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (machineTile.isInstance(world.getTileEntity(x, y, z))) {
				TileMachineBase tile = (TileMachineBase) world.getTileEntity(x, y, z);
				if (tile.hasmastercheck) {
					int mx = tile.getMasterX();
					int my = tile.getMasterY();
					int mz = tile.getMasterZ();
					player.openGui(Redoxiation.instance, GUIs.BlastFurnaceBlock.ordinal(), world, mx, my, mz);
					RedoxiationAchievements.triggerAchievement(player, "redoxiation.blast");
					return true;
				}
			}
			return false;
		}
		return true;
	}
}
