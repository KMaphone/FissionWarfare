package tm.fissionwarfare.math;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RaytraceUtil {
	
	public static boolean traceForEntity(Vector3d vector, World world, Entity taget, double timeoutRange) {
		
		Vector3d entityVector = new Vector3d(taget.posX, taget.posY, taget.posZ);
		Vector3d velcity = Vector3d.getVectorFromAngle(Angle2d.getAngleFromVector(vector, entityVector));
		Vector3d raytrace = vector.copy();
		
		AxisAlignedBB targetBox = taget.getBoundingBox();
		AxisAlignedBB raytraceBox = AxisAlignedBB.getBoundingBox(raytrace.x, raytrace.y, raytrace.z, raytrace.x, raytrace.y, raytrace.z);
		
		while (!world.checkBlockCollision(raytraceBox) || vector.distance(raytrace) <= timeoutRange) {
			
			raytrace.add(velcity);
			raytraceBox = AxisAlignedBB.getBoundingBox(raytrace.x, raytrace.y, raytrace.z, raytrace.x, raytrace.y, raytrace.z);
			
			if (targetBox.intersectsWith(raytraceBox)) {
				return true;
			}
		}
		
		return false;
	}
}
