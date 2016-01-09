package teamKuiper.redoxiation.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.achievement.RedoxiationAchievements;
import teamKuiper.redoxiation.blocks.gui.GUIs;
import teamKuiper.redoxiation.blocks.tileentity.TileBlastFurnaceBlock;

import java.util.Random;

public class BlastFurnaceBlock extends BlockContainer {
	Random random = new Random();
	
	String name = "blastFurnace";

	public BlastFurnaceBlock() {
		super(Material.wood);
		setCreativeTab(Redoxiation.tabRedoxiation);
		setBlockName("redoxiation.blastFurnaceBlock");
		setBlockTextureName(Redoxiation.MODID + ":" + name);
		GameRegistry.registerTileEntity(TileBlastFurnaceBlock.class, Redoxiation.MODID + ".blastFurnaceBlock");
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof TileBlastFurnaceBlock) {
			TileBlastFurnaceBlock multiBlock = (TileBlastFurnaceBlock) tile;
			if (multiBlock.hasMaster()) {
				if (multiBlock.isMaster()) {
					if (!multiBlock.checkMultiBlockForm())
						multiBlock.resetStructure();
				} else {
					if (!multiBlock.checkForMaster()) {
						TileBlastFurnaceBlock master = (TileBlastFurnaceBlock) world.getTileEntity(multiBlock.getMasterX(), multiBlock.getMasterY(), multiBlock.getMasterZ());
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
		if ((tile != null) && (tile instanceof TileBlastFurnaceBlock)) {
			TileBlastFurnaceBlock multiBlock = (TileBlastFurnaceBlock) tile;
			if (multiBlock.isMaster()) {
				multiBlock.resetStructure();
			}
		}
		TileBlastFurnaceBlock tileblastfurnace = (TileBlastFurnaceBlock) world.getTileEntity(x, y, z);

		if (tileblastfurnace != null) {
			for (int i1 = 0; i1 < tileblastfurnace.getSizeInventory(); ++i1) {
				ItemStack itemstack = tileblastfurnace.getStackInSlot(i1);

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
		return new TileBlastFurnaceBlock();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (world.getTileEntity(x, y, z) instanceof TileBlastFurnaceBlock) {
				TileBlastFurnaceBlock tile = (TileBlastFurnaceBlock) world.getTileEntity(x, y, z);
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