package tm.fissionwarfare.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class CubeRenderer {

	public static void render(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, IIcon iicon) {
		
		Tessellator tes = Tessellator.instance;
			
		float minU = iicon.getMinU();
		float maxU = iicon.getMaxU();
		float minV = iicon.getMinV();
		float maxV = iicon.getMaxV();
		
		float spaceU = maxU - minU;
		float spaceV = maxV - minV;
		
		float scale = (spaceV / (maxY - minY)) * maxY;
		
		final float FACE_XZ_NORMAL = 0.8944F;
        final float FACE_Y_NORMAL  = 0.4472F;
		
		tes.startDrawingQuads();
		
		//Top
		tes.setNormal(0.0F, 1.0F, 0.0F);
		tes.addVertexWithUV(minX, maxY, maxZ, minU, minV);		
		tes.addVertexWithUV(maxX, maxY, maxZ, maxU, minV);
		tes.addVertexWithUV(maxX, maxY, minZ, maxU, maxV);
		tes.addVertexWithUV(minX, maxY, minZ, minU, maxV);
		//Bottom
		tes.setNormal(0.0F, -1.0F, 0.0F);
		tes.addVertexWithUV(minX, minY, minZ, minU, minV);
		tes.addVertexWithUV(maxX, minY, minZ, maxU, minV);
		tes.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
		tes.addVertexWithUV(minX, minY, maxZ, minU, maxV);
		//North
		tes.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
		tes.addVertexWithUV(minX, maxY, minZ, minU, minV);
		tes.addVertexWithUV(maxX, maxY, minZ, maxU, minV);
		tes.addVertexWithUV(maxX, minY, minZ, maxU, maxV);
		tes.addVertexWithUV(minX, minY, minZ, minU, maxV);
		//SOUTH
		tes.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
		tes.addVertexWithUV(maxX, maxY, maxZ, minU, minV);
		tes.addVertexWithUV(minX, maxY, maxZ, maxU, minV);
		tes.addVertexWithUV(minX, minY, maxZ, maxU, maxV);
		tes.addVertexWithUV(maxX, minY, maxZ, minU, maxV);
		//EAST
		tes.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tes.addVertexWithUV(maxX, maxY, minZ, minU, minV);
		tes.addVertexWithUV(maxX, maxY, maxZ, maxU, minV);
		tes.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
		tes.addVertexWithUV(maxX, minY, minZ, minU, maxV);
		//WEST
		tes.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
		tes.addVertexWithUV(minX, maxY, maxZ, minU, minV);
		tes.addVertexWithUV(minX, maxY, minZ, maxU, minV);
		tes.addVertexWithUV(minX, minY, minZ, maxU, maxV);
		tes.addVertexWithUV(minX, minY, maxZ, minU, maxV);
		
		tes.draw();
	}
}
