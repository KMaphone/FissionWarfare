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

public class ExplosionBuilder {

	private Block[] rayStoppingBlocks = {Blocks.bedrock};
	
	private int size;
	private double step;
	
	private World world;
	private Vector3d vector;

	public ExplosionBuilder(World world, Vector3d vector, int size, double step) {
		this.world = world;
		this.vector = vector;
		this.size = size;
		this.step = step;
	}

	public List<Location> generateExplosion() {
		
		List<Location> list = new ArrayList<Location>();
		
		for (double yaw = 0; yaw < 360; yaw += step) {

			for (double pitch = 0; pitch < 180; pitch += step) {

				Angle2d angle = new Angle2d(pitch, yaw);
				List<Location> rayLocations = generateExplosionRay(vector, angle, world, size);
				list.addAll(rayLocations);
			}
		}
		
		return list;
	}
	
	private List<Location> generateExplosionRay(Vector3d vector, Angle2d angle, World world, double distance) {

		List<Location> list = new ArrayList<Location>();

		Vector3d velcity = vector.getVectorFromAngle(angle);
		Vector3d raytrace = vector.copy();
		
		while (vector.distance(raytrace) <= distance) {

			raytrace.add(velcity);
			Location loc = new Location(world, raytrace);
			
			if (loc.getBlock() != Blocks.air) {
				list.add(loc);
				
				if (loc.compare(rayStoppingBlocks)) {
					break;
				}
			}
		}
		
		return list;
	}
}
