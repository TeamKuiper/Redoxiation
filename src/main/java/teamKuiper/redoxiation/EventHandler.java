package teamKuiper.redoxiation;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamKuiper.redoxiation.blocks.Cog;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.blocks.tileentity.TileCog;

public class EventHandler {
    @SubscribeEvent
    public void multiCog(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            ForgeDirection side = ForgeDirection.getOrientation(event.face);
            Block block = event.world.getBlock(event.x + side.offsetX, event.y + side.offsetY, event.z + side.offsetZ);
            Item item = null;
            if (event.entityPlayer.getHeldItem() != null)
                item = event.entityPlayer.getHeldItem().getItem();
            if (block instanceof Cog ) {
                if (item != null && item == Item.getItemFromBlock(RedoxiationBlocks.cog)) {
                    Redoxiation.logger.info("!!!!!");
                    TileCog tile = (TileCog)event.world.getTileEntity(event.x + side.offsetX, event.y + side.offsetY, event.z + side.offsetZ);
                    tile.side[side.getOpposite().ordinal()] = true;
                }
            }
        }
    }
}
