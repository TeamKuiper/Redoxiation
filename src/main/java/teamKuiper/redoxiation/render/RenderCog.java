package teamKuiper.redoxiation.render;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.cog.BlockCog;
import teamKuiper.redoxiation.blocks.cog.CogType;
import teamKuiper.redoxiation.blocks.cog.TileCog;

public class RenderCog extends TileEntitySpecialRenderer<TileCog> {

	public static final ResourceLocation texture = new ResourceLocation(Redoxiation.MODID, "textures/blocks/StoneCog.png");
    
    @SuppressWarnings("unchecked")
	@Override
    public final void render(TileCog te, double x, double y, double z, float partialTicks, int destroyStage, float partial)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
		BlockRendererDispatcher blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
		BlockPos blockPos = te.getPos();
		IBlockState state = this.getWorld().getBlockState(blockPos);
		BlockCog cog = (BlockCog) state.getBlock();
		EnumFacing[] sides = cog.getPlacedBlockSides(state);
		
    	GlStateManager.pushMatrix();
    	GlStateManager.disableLighting();
    	GlStateManager.translate(x, y, z);
    	
		for(EnumFacing side : sides) {
			float trX = side.getFrontOffsetX() + (side.getFrontOffsetY() > 0 ? 1 : 0);
			float trY = (side.getFrontOffsetY() > 0 ? 1 : 0);
			float trZ = side.getFrontOffsetZ();
			
			GlStateManager.translate(trX, trY, trZ);
			int[] rots = getRotation(side, te.getAngle());
			GlStateManager.rotate(rots[0], 1, 0, 0);
			GlStateManager.rotate(rots[1], 0, 1, 0);
			GlStateManager.rotate(rots[2], 0, 0, 1);

			IBlockState modelState = cog.getStateForModel(state.getValue((PropertyEnum<CogType>) BlockCog.SIDE_PROPERTIES[side.getIndex()]));
			IBakedModel model = blockRenderer.getBlockModelShapes().getModelForState(modelState);
			blockRenderer.getBlockModelRenderer().renderModel(te.getWorld(), model, state, blockPos, buffer, true);
			
			GlStateManager.rotate(-rots[0], 1, 0, 0);
			GlStateManager.rotate(-rots[1], 0, 1, 0);
			GlStateManager.rotate(-rots[2], 0, 0, 1);
			GlStateManager.translate(-trX, -trY, -trZ);
		}
		
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
    }

	/*@SuppressWarnings("unchecked")
	public void renderCog(TileCog te, double x, double y, double z, float partialTicks, int destroyStage,
			float partial, BufferBuilder renderer) {
		BlockRendererDispatcher blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
		BlockPos blockPos = te.getPos();
		IBlockState state = this.getWorld().getBlockState(blockPos);
		BlockCog cog = (BlockCog) state.getBlock();
		EnumFacing[] sides = cog.getPlacedBlockSides(state);
		for(EnumFacing side : sides) {
			IBlockState modelState = cog.getStateForModel(state.getValue((PropertyEnum<CogType>) BlockCog.SIDE_PROPERTIES[side.getIndex()]));
			IBakedModel model = blockRenderer.getBlockModelShapes().getModelForState(modelState);
			float cogAngle = te.getAngle();
			int[] rots = getRotation(side, cogAngle);
			
		}
		renderer.setTranslation(x - blockPos.getX(), y - blockPos.getY(), z - blockPos.getZ());
		blockRenderer.getBlockModelRenderer().renderModel(te.getWorld(), model, state, blockPos, renderer, true);
	}*/

	private int[] getRotation(EnumFacing side, float cogAngle) {
		int xRot = 0;
		int yRot = 0;
		int zRot = 0;
		
		xRot -= side.getFrontOffsetZ() * 90;
		if(side.getFrontOffsetY() > 0) xRot += 180;
		zRot -= side.getFrontOffsetX() * 90;
		
		xRot += side.getFrontOffsetX() * cogAngle;
		yRot += side.getFrontOffsetY() * cogAngle;
		zRot += side.getFrontOffsetZ() * cogAngle;
		
		return new int[] {xRot, yRot, zRot};
	}
}
