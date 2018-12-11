package teamKuiper.redoxiation.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.blocks.tileentity.TileIronCog;
import teamKuiper.redoxiation.blocks.tileentity.TileStoneCog;
import teamKuiper.redoxiation.blocks.tileentity.TileWoodenCog;
import teamKuiper.redoxiation.blocks.tileentity.TileTankLead;
import teamKuiper.redoxiation.blocks.tileentity.TilePipeCopper;
import teamKuiper.redoxiation.blocks.tileentity.TileWire;
import teamKuiper.redoxiation.render.ItemRenderIronCog;
import teamKuiper.redoxiation.render.ItemRenderStoneCog;
import teamKuiper.redoxiation.render.RenderIronCog;
import teamKuiper.redoxiation.render.RenderLeadTank;
import teamKuiper.redoxiation.render.RenderPipeCopper;
import teamKuiper.redoxiation.render.RenderStoneCog;
import teamKuiper.redoxiation.render.RenderWire;
import teamKuiper.redoxiation.render.RenderWoodenCog;

public class ClientProxy extends CommonProxy{
	
	public void registerTileEntitySpecialRenderer() {
		RenderWoodenCog renderWoodenCog = new RenderWoodenCog();
		RenderStoneCog renderStoneCog = new RenderStoneCog();
		RenderIronCog renderIronCog = new RenderIronCog();
		RenderWire renderWire = new RenderWire();
		RenderLeadTank renderLeadTank = new RenderLeadTank();
		RenderPipeCopper renderPipeCopper = new RenderPipeCopper();
        ClientRegistry.bindTileEntitySpecialRenderer(TileWoodenCog.class, renderWoodenCog);
		ClientRegistry.bindTileEntitySpecialRenderer(TileStoneCog.class, renderStoneCog);
		ClientRegistry.bindTileEntitySpecialRenderer(TileIronCog.class, renderIronCog);
        ClientRegistry.bindTileEntitySpecialRenderer(TileWire.class, renderWire);
        ClientRegistry.bindTileEntitySpecialRenderer(TileTankLead.class, renderLeadTank);
        ClientRegistry.bindTileEntitySpecialRenderer(TilePipeCopper.class, renderPipeCopper);
        //TODO The way of rendering has been changed 
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedoxiationBlocks.stoneCog), new ItemRenderStoneCog(renderStoneCog, new TileStoneCog()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedoxiationBlocks.ironCog), new ItemRenderIronCog(renderIronCog, new TileIronCog()));
	}
}
