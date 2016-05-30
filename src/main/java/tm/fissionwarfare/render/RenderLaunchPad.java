package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public class RenderLaunchPad extends RenderTileEntityBase {

	public RenderLaunchPad() {
		super("launch_pad");
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f1) {
		
		GL11.glPushMatrix();
		
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glScaled(0.85D, 0.85D, 0.85D);
			
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();	
		
		GL11.glPopMatrix();
	}	
}
