package tm.fissionwarfare.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.math.MathUtil;

public class GuiUtil {

	private static Minecraft mc = Minecraft.getMinecraft();
	
	private static int zLevel = 10;
	private static final int TEXTURE_SIZE = 256;
		
	public static void drawStatusPanel(int energy, int progress, int x, int y, int mouseX, int mouseY) {
		
		GuiRect rect = new GuiRect(x + 1, y + 15, 18, 46);
		GuiRect rect2 = new GuiRect(rect.x + 5, rect.y + 5, rect.width - 10, rect.height - 10);
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		drawRect(x, y, 0, 0, 0, 19, 76);		
		
		if (rect.contains(mouseX, mouseY) && ! rect2.contains(mouseX, mouseY)) {
			drawTextBox("Progress: " + progress + "%", mouseX, mouseY, rect);
		}
		
		else drawTextBox(energy + " " + "/ 500 RF", mouseX, mouseY, rect2);	
		
		drawScaledHeightRect(x, y, 19, 0, progress, 12, 40, progress, 100);
	}
	
	public static void drawTextBox(String text, int mouseX, int mouseY, GuiRect rect) {
		
		if (rect.contains(mouseX, mouseY)) {
			
			drawCappedRect(mouseX + 2, mouseY - 12, 0, 208, 0, mc.fontRenderer.getStringWidth(text) + 1, 12, 256);
			mc.fontRenderer.drawString(text, mouseX + 4, mouseY - 10, 0xFFFFFF);
		}
	}
	
	public static void drawCenteredString(String text, int x, int y) {
				
		mc.fontRenderer.drawString(text, x - (mc.fontRenderer.getStringWidth(text) / 2), y, 4210752);
		GL11.glColor4f(1, 1, 1, 1);
	}
	
	public static void drawCappedRect(int x, int y, int u, int v, int zLevel, int sWidth, int height, int maxWidth) {
		
		drawRect(x, y, u, v, zLevel, sWidth, height);
		drawRect(x + sWidth, y, u + maxWidth - 2, v, zLevel, 2, height);		
	}
	
	public static void drawScaledHeightRect(int x, int y, int u, int v, int zLevel, int width, int height, int value, int maxValue) {
		
		drawRect(x, y, u, v, zLevel, width, -MathUtil.scaleInt(value, maxValue, height));
	}
	
	public static void drawRect(int x, int y, int u, int v, int zLevel, int width, int height) {
		
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
