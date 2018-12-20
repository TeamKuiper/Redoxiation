package teamKuiper.redoxiation.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockBoundCustomizable {
	public AxisAlignedBB[] getDrawAABBs(EntityPlayer player, World world, BlockPos pos) ;
}
