package teamKuiper.redoxiation.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderUtil {

	static final List<IBlockBoundCustomizable> BBCs = new ArrayList<IBlockBoundCustomizable>();

	public static void addBlockBoundCustomizable(IBlockBoundCustomizable bbc) {
		if(bbc != null && bbc instanceof Block && !BBCs.contains(bbc)) {
			BBCs.add(bbc);
		}
	}

	@SubscribeEvent
	public void drawSelectionBox(DrawBlockHighlightEvent e) {
		if(e.getTarget().typeOfHit == Type.BLOCK) {
			BlockPos pos = e.getTarget().getBlockPos();
			World world = e.getPlayer().world;
			EntityPlayer player = e.getPlayer();
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			
			for(IBlockBoundCustomizable bbc : BBCs) {
				if(block.equals((Block) bbc)) {
					AxisAlignedBB[] aabbs = bbc.getDrawAABBs(world, player, pos, state);
					for(AxisAlignedBB aabb : aabbs) {
						RenderGlobal.drawSelectionBoundingBox(aabb, 0, 0, 0, 0);
					}
					e.setCanceled(true);
					break;
				}
			}
		}
	}
}
