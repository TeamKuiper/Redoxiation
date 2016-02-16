package teamKuiper.redoxiation.render;

import codechicken.multipart.TileMultipart;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileWire;
import teamKuiper.redoxiation.multipart.WirePart;

public class RenderWirePart extends TileEntitySpecialRenderer {
    ResourceLocation texture;
    ResourceLocation objModelLocation;
    IModelCustom model;

    public RenderWirePart() {
        texture = new ResourceLocation(Redoxiation.MODID, "textures/models/WoodenCogTexture.png");
        objModelLocation = new ResourceLocation(Redoxiation.MODID, "textures/models/CogModel.obj");
        model = AdvancedModelLoader.loadModel(objModelLocation);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double posX, double posY, double posZ, float f) {
        float scale;
        float rotation;
        double x, y, z, a, b;
        if (f>=1) {
            f-=1;
            TileMultipart tile = (TileMultipart) tileentity;
            WirePart w = (WirePart) tile.partMap((int)f);
            scale = w.getscale();
            rotation = w.getrotation();
            switch ((int)f) {
            case 0 :
                x = 0;
                y = -0.5;
                z = 0;
                a = 0;
                b = 0;
                break;
            case 1 :
                x = 0;
                y = 0.5;
                z = 0;
                a = 2;
                b = 0;
                break;
            case 2 :
                x = 0;
                y = 0;
                z = -0.5;
                a = 1;
                b = 0;
                break;
            case 3 :
                x = 0;
                y = 0;
                z = 0.5;
                a = -1;
                b = 0;
                break;
            case 4 :
                x = -0.5;
                y = 0;
                z = 0;
                a = 0;
                b = -1;
                break;
            case 5 :
                x = 0.5;
                y = 0;
                z = 0;
                a = 0;
                b = 1;
                break;
            default :
                x = 0;
                y = -0.5;
                z = 0;
                a = 0;
                b = 0;
                break;
        }
        } else {
            scale = 1.0f;
            rotation = 0.0f;
            x = 0;
            y = -0.5;
            z = 0;
            a = 0;
            b = 0;
        }
        bindTexture(texture);

        GL11.glPushMatrix();
        GL11.glTranslated(posX + 0.5 + x, posY + 0.5 + y, posZ + 0.5 + z);
        GL11.glScalef(scale, scale, scale);
        GL11.glPushMatrix();
        if (!(a == 0  && b == 0)) {
            if(a == 2) {
                GL11.glRotated(180, a, 0, b);
            } else {
                GL11.glRotated(90, a, 0, b);
            }
        }
        model.renderAll();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
