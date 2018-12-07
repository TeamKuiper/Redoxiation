package teamKuiper.redoxiation.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.blocks.tileentity.TileEntityIronCog;
import teamKuiper.redoxiation.blocks.tileentity.TileEntityStoneCog;
import teamKuiper.redoxiation.blocks.tileentity.TileEntityWoodenCog;
import teamKuiper.redoxiation.blocks.tileentity.TileLeadTank;
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
		TileEntitySpecialRenderer renderWoodenCog = new RenderWoodenCog();
		TileEntitySpecialRenderer renderStoneCog = new RenderStoneCog();
		TileEntitySpecialRenderer renderIronCog = new RenderIronCog();
        TileEntitySpecialRenderer renderWire = new RenderWire();
        TileEntitySpecialRenderer renderLeadTank = new RenderLeadTank();
        TileEntitySpecialRenderer renderPipeCopper = new RenderPipeCopper();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenCog.class, renderWoodenCog);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStoneCog.class, renderStoneCog);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIronCog.class, renderIronCog);
        ClientRegistry.bindTileEntitySpecialRenderer(TileWire.class, renderWire);
        ClientRegistry.bindTileEntitySpecialRenderer(TileLeadTank.class, renderLeadTank);
        ClientRegistry.bindTileEntitySpecialRenderer(TilePipeCopper.class, renderPipeCopper);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedoxiationBlocks.stoneCog), new ItemRenderStoneCog(renderStoneCog, new TileEntityStoneCog()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedoxiationBlocks.ironCog), new ItemRenderIronCog(renderIronCog, new TileEntityIronCog()));
	}
}
