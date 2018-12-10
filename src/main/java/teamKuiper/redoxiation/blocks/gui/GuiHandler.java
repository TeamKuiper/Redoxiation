package teamKuiper.redoxiation.blocks.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import teamKuiper.redoxiation.blocks.container.ContainerBlastFurnace;
import teamKuiper.redoxiation.blocks.tileentity.TileBlastFurnaceBlock;

public class GuiHandler implements IGuiHandler {
	private static final int GUIID = 31;

	public static int getGuiID() {
		return GUIID;
	}

	// Gets the server side element for the given gui id this should return a
	// container
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity instanceof TileBlastFurnaceBlock) {
			TileBlastFurnaceBlock tileInventoryFurnace = (TileBlastFurnaceBlock) tileEntity;
			return new ContainerBlastFurnace(player.inventory, tileInventoryFurnace);
		}
		return null;
	}

	// Gets the client side element for the given gui id this should return a
	// gui
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity instanceof TileBlastFurnaceBlock) {
			TileBlastFurnaceBlock tileInventoryFurnace = (TileBlastFurnaceBlock) tileEntity;
			return new GuiBlastFurnace(player.inventory, tileInventoryFurnace);
		}
		return null;
	}

}
