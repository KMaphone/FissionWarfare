package tm.fissionwarfare.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import scala.annotation.meta.field;
import tm.fissionwarfare.math.Angle2d;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.math.RaytraceUtil;
import tm.fissionwarfare.math.Vector3d;

public class FluxExplosion {

	public final Random random = new Random();
	
	private World world;
	private double size, step;
	private Vector3d vector;
	
	public FluxExplosion(World world, double size, double step, Vector3d vector) {
		this.world = world;
		this.size = size;
		this.step = step;
		this.vector = vector;
	}
	
	public void hitBlock(Location location) {
		
		location.setBlockToAir();
	}
	
	public boolean canMoveThrough(Location location) {
		
		if (location.matches(Blocks.bedrock)) {
			return false;
		}
		
		return true;
	}
	
	public void doExplosionPartA() {
		
		for (int yaw = 0; yaw < 360; yaw += step) {
			
			for (int pitch = 0; pitch < 180; pitch += step) {
			
				Angle2d angle = new Angle2d(pitch, yaw);
				List<Location> locations = RaytraceUtil.traceBlocks(vector, angle, world, size + ((Math.random() * 8) - 4));
				
				for (Location loc : locations) {
					if (!canMoveThrough(loc)) {
						break;
					}
					
					hitBlock(loc);
				}
			}
		}
	}
	
	public void doExplosionPartB() {
		
		List<Object> list = new ArrayList<Object>();
		
		list.addAll(world.loadedEntityList);
		
		for (Object obj : list) {
			
			if (obj instanceof EntityLivingBase) {
				
				EntityLivingBase player = (EntityLivingBase) obj;
				
				double blastZone = size * 3;
				double distance = player.getDistance(vector.x, vector.y, vector.z);
				double damage = blastZone - distance;
				
				if (damage > 0) {
				
					player.attackEntityFrom(DamageSource.generic, (float) damage);
				}
			}
		}
		
	}
	
	public void doExplosionPartC() {
		world.playSoundEffect(vector.x, vector.y, vector.z, "random.explode", (float) size, 1F);
		
		for (int i = 0; i < size * 2; i++) {
			
			double rx = MathHelper.getRandomDoubleInRange(random, -size, size);
			double ry = MathHelper.getRandomDoubleInRange(random, -size, size);
			double rz = MathHelper.getRandomDoubleInRange(random, -size, size);
			
			world.spawnParticle("explode", vector.x + rx, vector.y + ry, vector.z + rz, 0, 0, 0);
			world.spawnParticle("smoke", vector.x + rx, vector.y + ry, vector.z + rz, 0, 0, 0);
		}
	}
}
