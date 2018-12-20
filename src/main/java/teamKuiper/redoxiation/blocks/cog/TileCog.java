package teamKuiper.redoxiation.blocks.cog;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class TileCog extends TileEntity {
	
    CogHandler[] handlers = new CogHandler[6];
    float angle;
    
    public TileCog() {
    	for(int i = 0; i < handlers.length; i++) {
    		handlers[i] = new CogHandler();
    	}
    }
    
    /**
     * Place the cog in the given side.
     * Returns true if succeed.
     * @param state
     * @param side
     * @param newType
     * @return
     */
	public boolean placeSide(EnumFacing side, CogType newType) {
    	return handlers[side.getIndex()].setCog(newType);
    }
    
    /**
     * Destroys the cog which is in the given side.
     * Returns true if succeed.
     * @param world
     * @param pos
     * @param state
     * @param side
     * @param dropItem
     * @return
     */
	public boolean destroySide(EnumFacing side, boolean dropItem) {
    	boolean succeed = handlers[side.getIndex()].setCog(CogType.NONE);
    	if(succeed) {
    		removeIfEmpty();
    		if(dropItem) Block.spawnAsEntity(world, pos, new ItemStack(RedoxiationBlocks.cog, 1, getCogType(side).getMetadata()));
    		return true;
    	}
    	return false;
    }
	
	/**
	 * Returns the sides which cog exists.
	 * @param state
	 * @return
	 */
	public EnumFacing[] getPlacedBlockSides() {
		List<EnumFacing> sides = new ArrayList<EnumFacing>();
		for(EnumFacing side : EnumFacing.values()) {
			if(getCogType(side) != CogType.NONE) {
				sides.add(side);
			}
		}
		return sides.toArray(new EnumFacing[0]);
	}

    /**
     * Remove the block if there is no cog in the block.
     * @param world
     * @param pos
     * @param state
     * @return
     */
	public boolean removeIfEmpty() {
		for(int i = 0; i < handlers.length; i++) {
			if(handlers[i].getCogType() != CogType.NONE)
				return false;
		}
		world.setBlockToAir(pos);
		return true;
    }
    
    public CogHandler getCogHandler(int meta) {
    	return handlers[meta];
    }
    
    public CogType getCogType(EnumFacing side) {
    	return handlers[side.getIndex()].getCogType();
    }
    
    public float getAngle() {
    	return angle;
    }
}
