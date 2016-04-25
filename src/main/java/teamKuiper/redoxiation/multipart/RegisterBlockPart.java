package teamKuiper.redoxiation.multipart;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.TMultiPart;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

import java.util.Arrays;

public class RegisterBlockPart implements IPartFactory, IPartConverter
{
    String type = Redoxiation.MODID + "Wire";
    @Override
    public TMultiPart createPart(String name, boolean client)
    {
        if(name.equals(type)) {
            return new WirePart();
        }
        return null;
    }

    public void init()
    {
        MultiPartRegistry.registerConverter(this);
        MultiPartRegistry.registerParts(this, new String[]{
                type
        });
    }

    @Override
    public Iterable<Block> blockTypes() {
        return Arrays.asList(RedoxiationBlocks.wire);
    }

    @Override
    public TMultiPart convert(World world, BlockCoord pos)
    {
        Block b = world.getBlock(pos.x, pos.y, pos.z);
        int meta = world.getBlockMetadata(pos.x, pos.y, pos.z);
        if(b == RedoxiationBlocks.wire)
            return new WirePart(meta);

        return null;
    }
}
