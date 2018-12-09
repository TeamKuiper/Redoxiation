package teamKuiper.redoxiation.advancement;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class AdvancementEvents {
	@SubscribeEvent
	public void entitySlain(LivingDeathEvent event) {
		/*if(event.source != null && event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer murderer = (EntityPlayer) event.source.getEntity();
			
			if(murderer.getHeldItem().getItem() != null)
			{
				RedoxiationAchievements.triggerAchievement(murderer, "redoxiation.start");
			}
		}*/
	}
	
	@SubscribeEvent
	public void onBlockHarvest(HarvestDropsEvent event) {
		if (event.getHarvester() != null) {
			EntityPlayer miner = event.getHarvester();

			if (event.getState().getBlock() == RedoxiationBlocks.oreCopper) {
				RedoxiationAdvancement.triggerAchievement(miner, "redoxiation.start");
			}
		}
	}
}
