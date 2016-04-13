package tm.fissionwarfare.math;

public class Angle2d {

	public double pitch, yaw;

	public Angle2d(double pitch, double yaw) {
		this.pitch = pitch;
		this.yaw = yaw;
	}
	
	public static Angle2d getAngleFromVector(Vector3d vec1, Vector3d vec2) {
		
		double dx = vec1.x - vec2.x;
		double dy = vec1.y - vec2.y;
		double dz = vec1.z - vec2.z;
		
		double pitch = Math.atan2(dz, dx);
		double yaw = Math.atan2(Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2)), dy);
		
		return new Angle2d(pitch, yaw);
	}
}
