package tm.fissionwarfare.math;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class RaytraceUtil {

	public static List<Location> traceBlocks(Vector3d vector, Angle2d angle, World world, double distance) {

		List<Location> list = new ArrayList<Location>();

		Vector3d velcity = vector.getVectorFromAngle(angle);
		Vector3d raytrace = vector.copy();
		
		while (vector.distance(raytrace) <= distance) {

			raytrace.add(velcity);
			Location loc = new Location(world, raytrace);
			
			if (loc.getBlock() == Blocks.bedrock) {
				break;
			}
			
			if (loc.getBlock() != Blocks.air) {
				list.add(loc);
			}
		}
		
		return list;
	}
}
