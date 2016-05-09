package tm.fissionwarfare.explosion;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import tm.fissionwarfare.api.IReinforcedBlock;
import tm.fissionwarfare.math.Angle2d;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.math.Vector3d;

public class ExplosionUtil {
	
	private static List<Location> effectedBlocks = new ArrayList<Location>();
	
	public static void generateExplosion(World world, Vector3d vector, double size, int step) {
		
		effectedBlocks.clear();
		
		for (int yaw = 0; yaw < 360; yaw += step) {

			for (int pitch = 0; pitch < 180; pitch += step) {

				Angle2d angle = new Angle2d(pitch, yaw);
				generateExplosionRay(vector, angle, world, size);
			}
		}
		
		for (Location location : effectedBlocks) {
			processBlock(location);
		}
	}
	
	private static boolean contains(Location location) {
		
		for (Location loc : effectedBlocks) {
			if (loc.matches(location)) {
				return true;
			}
		}
		
		return false;
	}
	
	private static void processBlock(Location location) {	
		
		location.setBlockToAir();		
	}

	private static void generateExplosionRay(Vector3d vector, Angle2d angle, World world, double distance) {
		
		Vector3d velcity = vector.getVectorFromAngle(angle);
		Vector3d raytrace = vector.copy();
				
		while (vector.distance(raytrace) <= distance) {
			
			raytrace.add(velcity);
			Location loc = new Location(world, raytrace);
			
			if (loc.getBlock().getBlockHardness(world, loc.x, loc.y, loc.z) < 0) {
				break;
			}
			
			if (loc.getBlock() instanceof IReinforcedBlock) {
				break;
			}
				
			if (loc.getBlock() != Blocks.air && !contains(loc)) {
										
				effectedBlocks.add(loc);
			}
			
		}
	}
}
