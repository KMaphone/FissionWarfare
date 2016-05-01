package tm.fissionwarfare.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import tm.fissionwarfare.Reference;

public class GuiUtil {

	private static Minecraft mc = Minecraft.getMinecraft();
	
	private static int zLevel = 100;
	private static final int TEXTURE_SIZE = 256;
	
	public static void drawCenteredString(String text, int screenX, int screenY, int x, int y) {
		
		int xPos = screenX + x;
		int yPos = screenY + y;
		
		mc.fontRenderer.drawString(text, xPos - (mc.fontRenderer.getStringWidth(text) / 2), yPos, 4210752);
	}
	
	public static void drawFuelBar(int fuel, int screenX, int screenY, int x, int y) {
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		drawRect(screenX + x, screenY + y, 0, 0, 19, 76);
	}
	
	public static void drawTextBox(String text, int x, int y) {
		
		
	}
	
	public static void drawScaledBox(int x, int y, int u, int v, int sWidth, int height, int maxWidth) {
		
		drawRect(x, y, u, v, sWidth, height);
		drawRect(x + sWidth, y, u + maxWidth - 2, v, 2, height);		
	}
	
	public static void drawRect(int x, int y, int u, int v, int width, int height) {
		
		int maxX = x + width;
		int maxY = y + height;
		
		int maxU = u + width;
		int maxV = v + height;
		
		float pixel = 1F / TEXTURE_SIZE;
		
        Tessellator tessellator = Tessellator.instance;
        
        tessellator.startDrawingQuads();
        
        tessellator.addVertexWithUV(x, maxY, zLevel, u * pixel, maxV * pixel);
        tessellator.addVertexWithUV(maxX, maxY, zLevel, maxU * pixel, maxV * pixel);
        tessellator.addVertexWithUV(maxX, y, zLevel, maxU * pixel, v * pixel);
        tessellator.addVertexWithUV(x, y, zLevel, u * pixel, v * pixel);
        
        tessellator.draw();
    }	
}
