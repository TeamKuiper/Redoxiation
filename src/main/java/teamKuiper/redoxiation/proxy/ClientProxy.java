package teamKuiper.redoxiation.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.obj.OBJLoader;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.blocks.tileentity.TileIronCog;
import teamKuiper.redoxiation.blocks.tileentity.TileStoneCog;
import teamKuiper.redoxiation.render.ItemRenderIronCog;
import teamKuiper.redoxiation.render.ItemRenderStoneCog;

public class ClientProxy extends CommonProxy {
	
	
	public void init() {
        //TODO The way of rendering has been changed 
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedoxiationBlocks.stoneCog), new ItemRenderStoneCog(renderStoneCog, new TileStoneCog()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedoxiationBlocks.ironCog), new ItemRenderIronCog(renderIronCog, new TileIronCog()));
	}
}
