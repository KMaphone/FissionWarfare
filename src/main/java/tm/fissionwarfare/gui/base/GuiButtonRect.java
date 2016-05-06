package tm.fissionwarfare.gui.base;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import tm.fissionwarfare.Reference;

@SideOnly(Side.CLIENT)
public class GuiButtonRect extends GuiButton {

	public GuiRect rect;
	
	public GuiButtonRect(int id, int x, int y, int width, String text) {
		super(id, x, y, width, 16, text);
		rect = new GuiRect(xPosition, yPosition, width, height);
	}
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {

		if (this.visible) {
			
			int color = 0xBBBBBB;
			GL11.glColor4f(1, 1, 1, 1);
			
			if (rect.contains(mouseX, mouseY)) {
				color = 0xFFFFFF;
				GL11.glColor4f(1F, 1F, 1F, 2F);
			}
			
			if (!enabled) {				
				color = 0x707070;
				GL11.glColor4f(0.5F, 0.5F, 0.5F, 1);
			}
			
			mc.getTextureManager().bindTexture(Reference.GUI_TEXTURES);
			GuiUtil.drawCappedRect(rect.x, rect.y, 0, 240, (int)zLevel, rect.width, rect.height, 256);
			
			GL11.glColor4f(1, 1, 1, 1);
			
			GuiUtil.drawCenteredString(displayString, rect.x + (rect.width / 2) + 2, rect.y + (this.height - 8) / 2, color);
		}
	}
}