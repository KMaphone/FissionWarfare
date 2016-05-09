package tm.fissionwarfare.math;

import codechicken.lib.math.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class RaytraceUtil {
	
	public static boolean traceForEntity(Vector3d vector, Vector3d target, World world) {
		
		Vector3d velcity = Vector3d.getVectorFromAngle(Angle2d.getAngleFromVectors(vector, target));
		Vector3d raytrace = vector.copy();
		
		while (vector.distance(raytrace) <= vector.distance(target)) {
			
			raytrace.add(velcity);
			
			if (doesHitBlock(raytrace, world)) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean doesHitBlock(Vector3d vector, World world) {
		
		int x = MathHelper.floor_double(vector.x);
		int y = MathHelper.floor_double(vector.y);
		int z = MathHelper.floor_double(vector.z);
		
		Block block = world.getBlock(x, y, z);
		
		if (block.isOpaqueCube()) {
			return true;
		}
		
		return false;
	}
}
