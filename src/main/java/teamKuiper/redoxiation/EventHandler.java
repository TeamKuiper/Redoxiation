package teamKuiper.redoxiation;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.blocks.cog.BlockCog;
import teamKuiper.redoxiation.blocks.cog.TileCog;

public class EventHandler {
	@SubscribeEvent
	public void multiCog(PlayerInteractEvent.RightClickBlock event) {
		EnumFacing side = event.getFace();
		BlockPos pos = event.getPos();
		BlockPos offsetPos = new BlockPos(pos.getX() + side.getFrontOffsetX(), pos.getY() + side.getFrontOffsetY(),
				pos.getZ() + side.getFrontOffsetZ());
		Block block = event.getWorld().getBlockState(offsetPos).getBlock();
		Item item = event.getEntityPlayer().getHeldItemMainhand().getItem();
		if (item != null && block instanceof BlockCog && item == Item.getItemFromBlock(RedoxiationBlocks.cog)) {
			Redoxiation.logger.info("!!!!!");
			TileCog tile = (TileCog) event.getWorld().getTileEntity(offsetPos);
			tile.side[side.getOpposite().ordinal()] = true;
		}
	}
}
