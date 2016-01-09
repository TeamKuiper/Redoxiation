package teamKuiper.redoxiation.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.blocks.tileentity.*;
import teamKuiper.redoxiation.render.*;

public class ClientProxy extends CommonProxy{
	
	public void registerTileEntitySpecialRenderer() {
		TileEntitySpecialRenderer renderWoodenCog = new RenderWoodenCog();
		TileEntitySpecialRenderer renderStoneCog = new RenderStoneCog();
		TileEntitySpecialRenderer renderIronCog = new RenderIronCog();
        TileEntitySpecialRenderer renderWire = new RenderWire();
        TileEntitySpecialRenderer renderLeadTank = new RenderLeadTank();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenCog.class, renderWoodenCog);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStoneCog.class, renderStoneCog);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIronCog.class, renderIronCog);
        ClientRegistry.bindTileEntitySpecialRenderer(TileWire.class, renderWire);
        ClientRegistry.bindTileEntitySpecialRenderer(TileLeadTank.class, renderLeadTank);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedoxiationBlocks.StoneCog), new ItemRenderStoneCog(renderStoneCog, new TileEntityStoneCog()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(RedoxiationBlocks.IronCog), new ItemRenderIronCog(renderIronCog, new TileEntityIronCog()));
	}
}
