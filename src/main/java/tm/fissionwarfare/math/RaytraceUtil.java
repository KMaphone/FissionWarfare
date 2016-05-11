package tm.fissionwarfare.math;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RaytraceUtil {
	
	public static boolean traceForEntity(Angle2d angle, Vector3d vector, World world, Entity entity, double distance) {
		
		Vector3d velcity = Vector3d.getVectorFromAngle(angle);
		
		Vector3d raytrace = vector.copy();
		
		while (vector.distance(raytrace) <= distance) {
			
			raytrace.add(velcity);
			
			if (entity.boundingBox.isVecInside(Vec3.createVectorHelper(raytrace.x, raytrace.y, raytrace.z))) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean traceForBlock(Vector3d vector, Vector3d target, World world, Block src) {
		
		Vector3d velcity = Vector3d.getVectorFromAngle(Angle2d.getAngleFromVectors(vector, target));
		Vector3d raytrace = vector.copy();
		
		while (vector.distance(raytrace) <= vector.distance(target)) {
			
			raytrace.add(velcity);
			
			if (doesHitBlock(raytrace, world, src)) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean doesHitBlock(Vector3d vector, World world, Block src) {
		
		int x = MathHelper.floor_double(vector.x);
		int y = MathHelper.floor_double(vector.y);
		int z = MathHelper.floor_double(vector.z);
		
		Block block = world.getBlock(x, y, z);
		
		if (block != Blocks.air && block != src) {
			
			return true;
		}
		
		return false;
	}
}
