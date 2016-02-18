package teamKuiper.redoxiation.multipart;

import net.minecraft.world.World;
import teamKuiper.redoxiation.blocks.tileentity.TileWire;

public interface FloodFill {
    public TileWire getWire(World world, int x, int y, int z, int side);
    public WirePart getWirePart(World world, int x, int y, int z, int side);
    public boolean checkstate(int x, int y, int z, int st);
}
