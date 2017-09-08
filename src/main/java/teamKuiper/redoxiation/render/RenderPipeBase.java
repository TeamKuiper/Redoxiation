package teamKuiper.redoxiation.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import teamKuiper.redoxiation.blocks.tileentity.TilePipeBase;

public class RenderPipeBase extends TileEntitySpecialRenderer {

	ResourceLocation texture;
	boolean drawInside = true;

	float pixel = 1F / 16F;
	float texturePixel = 1F / 32F;

	public RenderPipeBase(ResourceLocation texture) {
		this.texture = texture;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double translationX, double translationY, double translationZ,
			float f) {
		GL11.glTranslated(translationX, translationY, translationZ);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(texture);
		{
			TilePipeBase pipe = (TilePipeBase) tileEntity;
			//if(!pipe.onlyOneOpposite(pipe.connections)) {
				drawCore(tileEntity);
			//}
			for (int x = 0; x < pipe.connections.length; x++) {
				if (pipe.connections[x] != null) {
					drawConnector(pipe.connections[x]);
				}
			}
		}
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-translationX, -translationY, -translationZ);
	}

	public void drawConnector(ForgeDirection direction) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		{
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			if (direction.equals(ForgeDirection.UP)) {
				// rotate
			} else if (direction.equals(ForgeDirection.DOWN)) {
				GL11.glRotatef(180, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.SOUTH)) {
				GL11.glRotatef(90, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.NORTH)) {
				GL11.glRotatef(270, 1, 0, 0);
			} else if (direction.equals(ForgeDirection.WEST)) {
				GL11.glRotatef(90, 0, 0, 1);
			} else if (direction.equals(ForgeDirection.EAST)) {
				GL11.glRotatef(270, 0, 0, 1);
			}
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
					5 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 1 - 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1, 1 - 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
					0 * texturePixel);

			tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
					5 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
					0 * texturePixel);

			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
					5 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1, 1 - 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
					0 * texturePixel);

			tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
					5 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1, 1 - 11 * pixel / 2, 10 * texturePixel, 5 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1, 11 * pixel / 2, 10 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
					0 * texturePixel);
		}
		;
		tessellator.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if (direction.equals(ForgeDirection.UP)) {
			// rotate
		} else if (direction.equals(ForgeDirection.DOWN)) {
			GL11.glRotatef(-180, 1, 0, 0);
		} else if (direction.equals(ForgeDirection.SOUTH)) {
			GL11.glRotatef(-90, 1, 0, 0);
		} else if (direction.equals(ForgeDirection.NORTH)) {
			GL11.glRotatef(-270, 1, 0, 0);
		} else if (direction.equals(ForgeDirection.WEST)) {
			GL11.glRotatef(-90, 0, 0, 1);
		} else if (direction.equals(ForgeDirection.EAST)) {
			GL11.glRotatef(-270, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	public void drawCore(TileEntity tileentity) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		{
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
					5 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
					5 * texturePixel);

			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
					5 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
					5 * texturePixel);

			tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
					5 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
					5 * texturePixel);

			tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
					5 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
					5 * texturePixel);

			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
					5 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
					5 * texturePixel);

			tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
					5 * texturePixel);
			tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
					0 * texturePixel);
			tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
					5 * texturePixel);

			if (drawInside) {
				tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
						5 * texturePixel);
				tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
						0 * texturePixel);
				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2,
						5 * texturePixel, 0 * texturePixel);
				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
						5 * texturePixel);

				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
						5 * texturePixel);
				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2,
						0 * texturePixel, 0 * texturePixel);
				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
						0 * texturePixel);
				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
						5 * texturePixel);

				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
						5 * texturePixel);
				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
						0 * texturePixel);
				tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
						0 * texturePixel);
				tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
						5 * texturePixel);

				tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
						5 * texturePixel);
				tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
						0 * texturePixel);
				tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
						0 * texturePixel);
				tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
						5 * texturePixel);

				tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
						5 * texturePixel);
				tessellator.addVertexWithUV(11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
						0 * texturePixel);
				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
						0 * texturePixel);
				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2,
						5 * texturePixel, 5 * texturePixel);

				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 0 * texturePixel,
						5 * texturePixel);
				tessellator.addVertexWithUV(1 - 11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 0 * texturePixel,
						0 * texturePixel);
				tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 5 * texturePixel,
						0 * texturePixel);
				tessellator.addVertexWithUV(11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 5 * texturePixel,
						5 * texturePixel);
			}
		}
		tessellator.draw();
	}

}
