package teamKuiper.redoxiation.blocks.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.container.ContainerBlastFurnace;
import teamKuiper.redoxiation.blocks.tileentity.TileBlastFurnaceBlock;

@SideOnly(Side.CLIENT)
public class GuiBlastFurnace extends GuiContainer {

	// This is the resource location for the background image
	private static final ResourceLocation texture = new ResourceLocation("redoxiation", "textures/gui/GuiBlastFurnace.png");
	private TileBlastFurnaceBlock tileEntity;

	public GuiBlastFurnace(InventoryPlayer invPlayer,
			TileBlastFurnaceBlock tileBlastFurnaceBlock) {
		super(new ContainerBlastFurnace(invPlayer, tileBlastFurnaceBlock));

		// Set the width and height of the gui
		xSize = 176;
		ySize = 218;

		this.tileEntity = tileBlastFurnaceBlock;
	}

    // some [x,y] coordinates of graphical elements
    final int COOK_BAR_XPOS = 58;
    final int COOK_BAR_YPOS = 67;
    final int COOK_BAR_ICON_U = 176;   // texture position of white arrow icon
    final int COOK_BAR_ICON_V = 0;
    final int COOK_BAR_WIDTH = 68;
    final int COOK_BAR_HEIGHT = 46;

    final int FLAME_XPOS = 65;
    final int FLAME_YPOS = 116;
    final int FLAME_ICON_U = 176;   // texture position of flame icon
    final int FLAME_ICON_V = 46;
    final int FLAME_WIDTH = 14;
    final int FLAME_HEIGHT = 14;
    final int FLAME_X_SPACING = 0;

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        // Bind the image texture
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        // Draw the image
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        // get cook progress as a double between 0 and 1
        double cookProgress = tileEntity.fractionOfCookTimeComplete();
        // draw the cook progress bar
        drawTexturedModalRect(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_ICON_U, COOK_BAR_ICON_V, COOK_BAR_WIDTH, (int)(cookProgress * COOK_BAR_HEIGHT));

        // draw the fuel remaining bar for each fuel slot flame
        for (int i = 0; i < tileEntity.FUEL_SLOTS_COUNT; ++i) {
            double burnRemaining = tileEntity.fractionOfFuelRemaining(i);
            int yOffset = (int)((1.0 - burnRemaining) * FLAME_HEIGHT);
            drawTexturedModalRect(guiLeft + FLAME_XPOS + FLAME_X_SPACING * i, guiTop + FLAME_YPOS + yOffset, FLAME_ICON_U, FLAME_ICON_V + yOffset, FLAME_WIDTH, FLAME_HEIGHT - yOffset);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;
        fontRendererObj.drawString(tileEntity.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());

        List<String> hoveringText = new ArrayList<String>();

        // If the mouse is over the progress bar add the progress bar hovering text
        if (isInRect(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_WIDTH, COOK_BAR_HEIGHT, mouseX, mouseY)){
            hoveringText.add(StatCollector.translateToLocal("gui."+ Redoxiation.MODID + ".blastFurnace.progress") + ":");
            int cookPercentage =(int)(tileEntity.fractionOfCookTimeComplete() * 100);
            hoveringText.add(cookPercentage + "%");
        }

        // If the mouse is over one of the burn time indicator add the burn time indicator hovering text
        for (int i = 0; i < tileEntity.FUEL_SLOTS_COUNT; ++i) {
            if (isInRect(guiLeft + FLAME_XPOS + FLAME_X_SPACING * i, guiTop + FLAME_YPOS, FLAME_WIDTH, FLAME_HEIGHT, mouseX, mouseY)) {
                hoveringText.add(StatCollector.translateToLocal("gui."+ Redoxiation.MODID + ".blastFurnace.fuelTime") + ":");
                hoveringText.add(tileEntity.secondsOfFuelRemaining(i) + StatCollector.translateToLocal("gui."+ Redoxiation.MODID + ".blastFurnace.runningTime"));
            }
        }
        // If hoveringText is not empty draw the hovering text
        if (!hoveringText.isEmpty()){
            drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
        }
		// You must re bind the texture and reset the colour if you still need to use it after drawing a string
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
    }

	// Returns true if the given x,y coordinates are within the given rectangle
	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
		return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
	}
}
