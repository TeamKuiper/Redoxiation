package teamKuiper.redoxiation.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockBoundCustomizable {
	public AxisAlignedBB[] getDrawAABBs(World world, EntityPlayer player, BlockPos pos, IBlockState state) ;
}
