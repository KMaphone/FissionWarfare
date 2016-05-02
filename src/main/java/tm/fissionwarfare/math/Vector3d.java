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
	
	public static Vector3d getVectorFromAngle(Angle2d angle) {
		
		double pitchRad = Math.toRadians(angle.pitch);
		double yawRad = Math.toRadians(angle.yaw);
		
		double x = Math.sin(pitchRad) * Math.cos(yawRad);
		double z = Math.sin(pitchRad) * -Math.sin(yawRad);
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
