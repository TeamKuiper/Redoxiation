package teamKuiper.redoxiation.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.Fluid;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileLeadTank;
import teamKuiper.redoxiation.model.ModelTank;

public class RenderLeadTank extends TileEntitySpecialRenderer<TileLeadTank> {

    public static final ResourceLocation texture = new ResourceLocation(Redoxiation.MODID, "textures/blocks/LeadTank.png");

    private ModelTank model;

    public RenderLeadTank() {
        this.model = new ModelTank();
    }
    
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    	
    	TileLeadTank tank = (TileLeadTank) world.getTileEntity(x, y, z);
    	if (tank.tank.getFluid() != null) {
            Fluid fluid = tank.tank.getFluid().getFluid();
            renderer.setRenderBounds(0.01, 0.01, 0.01, 0.99, (double) tank.tank.getFluidAmount() / (double) tank.tank.getCapacity() * 0.99, 0.99);
            renderer.setOverrideBlockTexture(fluid.getIcon());
            renderer.renderStandardBlock(block, (int)x, (int)y, (int)z);
            renderer.clearOverrideBlockTexture();
            GL11.glPushMatrix();
    	}
    	return true;
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180, 0F, 0F, 1F);
        GL11.glPushMatrix();
        this.model.renderModel(0.0625F, 0);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
