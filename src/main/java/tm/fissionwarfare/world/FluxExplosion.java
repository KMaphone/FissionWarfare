package tm.fissionwarfare.world;

import net.minecraft.world.World;
import tm.fissionwarfare.math.Angle2d;
import tm.fissionwarfare.math.Vector3d;

public class FluxExplosion {

	private World world;
	private double size;
	private Vector3d vector;
	
	public void doExplosionPartA() {
		
		for (int yaw = 0; yaw < 360; yaw++) {
			
			for (int pitch = 0; pitch < 360; pitch++) {
			
				Angle2d angle = new Angle2d(pitch, yaw);
				Vector3d velcity = Vector3d.getVectorFromAngle(angle);
				Vector3d ray = vector.copy();
				
				while (vector.distance(ray) <= size) {
					
					ray.add(velcity);
					
					world.setBlockToAir((int)ray.x, (int)ray.y, (int)ray.z);
				}
			}
		}
	}
}
