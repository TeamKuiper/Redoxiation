package teamKuiper.redoxiation.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileEntityWoodenCog;

public class RenderWoodenCog extends TileEntitySpecialRenderer {
    ResourceLocation texture;
    ResourceLocation objModelLocation;
    IModelCustom model;
    public RenderWoodenCog() {
        texture = new ResourceLocation(Redoxiation.MODID, "textures/models/WoodenCogTexture.png");
        objModelLocation = new ResourceLocation(Redoxiation.MODID, "textures/models/CogModel.obj");
        model = AdvancedModelLoader.loadModel(objModelLocation);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double posX, double posY, double posZ, float timeSinceLastTick) {
        TileEntityWoodenCog te = (TileEntityWoodenCog) tileentity;
        float rotation = te.getRotation();
        float scale = te.getScale();
        bindTexture(texture);

        GL11.glPushMatrix();
        GL11.glTranslated(posX + 0.5, posY, posZ + 0.5);
        GL11.glScalef(scale, scale, scale);
        GL11.glPushMatrix();
        GL11.glRotatef(rotation, 0F, 1F, 0F);
        model.renderAll();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
