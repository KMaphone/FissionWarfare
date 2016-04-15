package tm.fissionwarfare.explosion;

import java.util.ArrayList;
import java.util.List;

import com.sun.swing.internal.plaf.basic.resources.basic;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import tm.fissionwarfare.math.Angle2d;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.math.RaytraceUtil;
import tm.fissionwarfare.math.Vector3d;

public class ExplosionUtil {
	
	public static void generateExplosion(World world, Vector3d vector, double size, int step) {
		
		for (int yaw = 0; yaw < 360; yaw += step) {

			for (int pitch = 0; pitch < 180; pitch += step) {

				Angle2d angle = new Angle2d(pitch, yaw);
				List<Location> locations = generateExplosionRay(vector, angle, world, size);
				
				for (Location location : locations) {
					hitBlock(location);
				}
			}
		}
	}
	
	private static void hitBlock(Location location) {
		
		/*if (Math.random() * 100 <= 2) {
			location.getBlock().dropBlockAsItem(location.world, location.x, location.y, location.z, location.getMetadata(), 0);
		}*/
		
		location.setBlockToAir();
	}
	
	private static List<Location> generateExplosionRay(Vector3d vector, Angle2d angle, World world, double distance) {

		List<Location> list = new ArrayList<Location>();

		Vector3d velcity = vector.getVectorFromAngle(angle);
		Vector3d raytrace = vector.copy();
		
		while (vector.distance(raytrace) <= distance) {

			raytrace.add(velcity);
			Location loc = new Location(world, raytrace);
			
			if (loc.getBlock() != Blocks.air) {
				list.add(loc);
			}
		}
		
		return list;
	}
}
