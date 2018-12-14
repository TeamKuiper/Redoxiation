package teamKuiper.redoxiation.blocks.cog;

import net.minecraft.tileentity.TileEntity;

public class TileCog extends TileEntity {
    CogHandler[] handlers = new CogHandler[6];
    
    public CogHandler getCogHandler(int meta) {
    	return handlers[meta];
    }
}
