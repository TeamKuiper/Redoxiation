package teamKuiper.redoxiation;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.blocks.rocks.BlockOverworldOre;
import teamKuiper.redoxiation.blocks.rocks.BlockRock;
import teamKuiper.redoxiation.items.RedoxiationItems;

public class RedoxiationRecipeManager {
	
	public static void recipeCrafting()
	{
		Redoxiation.logger.info("Recipe & Smelting Complete. Starting World generator");
	}

	public static void recipeFurnace()
	{
		//Normal Smelting
		/*GameRegistry.addSmelting(BlockOverworldOre.oreCopper, new ItemStack(RedoxiationItems.ingotCopper,1,0),0.1f);
		GameRegistry.addSmelting(BlockOverworldOre.oreChromium, new ItemStack(RedoxiationItems.ingotChromium,1,0), 0.1f);
		GameRegistry.addSmelting(BlockOverworldOre.oreCobalt, new ItemStack(RedoxiationItems.ingotCobalt,1,0), 0.1f);
		GameRegistry.addSmelting(BlockOverworldOre.oreLead, new ItemStack(RedoxiationItems.ingotLead,1,0), 0.1f);
		GameRegistry.addSmelting(BlockOverworldOre.oreNickel, new ItemStack(RedoxiationItems.ingotNickel,1,0), 0.1f);
		GameRegistry.addSmelting(BlockOverworldOre.orePlatinum, new ItemStack(RedoxiationItems.ingotPlatinum,1,0), 0.1f);
		GameRegistry.addSmelting(BlockOverworldOre.oreSilver, new ItemStack(RedoxiationItems.ingotSilver,1,0),0.1f);
		GameRegistry.addSmelting(BlockOverworldOre.oreTin, new ItemStack(RedoxiationItems.ingotTin,1,0),0.1f);
		GameRegistry.addSmelting(BlockOverworldOre.oreZinc, new ItemStack(RedoxiationItems.ingotZinc,1,0), 0.1f);
		GameRegistry.addSmelting(BlockRock.scheelite, new ItemStack(RedoxiationItems.ingotTungsten,1,0), 0.1f);*/
		
		//Dust Smelting
		
	}
}
