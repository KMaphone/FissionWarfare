package tm.fissionwarfare.math;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Vector3d {
	
	public double x, y, z;

	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3d(Entity e) {
		this(e.posX, e.posY, e.posZ);
	}
	
	public void roundFloor() {
		x = Math.floor(x);
		y = Math.floor(y);
		z = Math.floor(z);
	}
	
	public void add(Vector3d vec) {
		x += vec.x;
		y += vec.y;
		z += vec.z;
	}
	
	public void sub(Vector3d vec) {
		x -= vec.x;
		y -= vec.y;
		z -= vec.z;
	}
	
	public void div(Vector3d vec) {
		x /= vec.x;
		y /= vec.y;
		z /= vec.z;
	}
	
	public void mul(Vector3d vec) {
		x *= vec.x;
		y *= vec.y;
		z *= vec.z;
	}
	
	public double distance(Vector3d vec) {
		
		double dx = x - vec.x;
		double dy = y - vec.y;
		double dz = z - vec.z;
		
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
	
	public static Angle2d getAngleFromVectors(Vector3d vec1, Vector3d vec2) {
		
		double dx = vec1.x - vec2.x;
		double dy = vec1.y - vec2.y;
		double dz = vec1.z - vec2.z;
					
		double yawRad = Math.atan2(dx, dz);		
		double pitchRad = Math.atan2(Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2)), dy);
		
		double rotYaw = Math.toDegrees(yawRad);
		double rotPitch = Math.toDegrees(pitchRad);
		
		return new Angle2d(rotPitch, rotYaw);
	}
	
	public static Vector3d getVectorFromAngle(Angle2d angle) {
		
		double pitchRad = Math.toRadians(angle.pitch);
		double yawRad = Math.toRadians(angle.yaw);
		
		double x = Math.sin(yawRad) * Math.sin(pitchRad);
		double z = Math.cos(yawRad) * Math.sin(pitchRad);
		double y = Math.cos(pitchRad);
		
		return new Vector3d(x, y, z);
	}
	
	public Vector3d copy() {
		return new Vector3d(x, y, z);
	}
	
	public Vec3 convert() {
		return Vec3.createVectorHelper(x, y, z);
	}
	
	@Override
	public String toString() {
		return "( " + x + ", " + y + ", " + z + " )";
	}
}
