package tm.fissionwarfare.explosion.type;

import java.util.Random;

import org.apache.logging.log4j.core.appender.RandomAccessFileManager;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.entity.EntityGasCloud;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.sounds.FWSound;

public class ChemicalExplosion implements IExplosionType {

	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}
	
	@Override
	public void doBlockDamage() {
	}

	@Override
	public void doPlayerDamage() {
		EntityGasCloud gasCloud = new EntityGasCloud(world, vector.x, vector.y, vector.z);
		world.spawnEntityInWorld(gasCloud);
	}

	@Override
	public void doEffects() {
		FWSound.small_blast.play(world, vector.x, vector.y, vector.z, 10, 1);
		
		/*Random rand = new Random();
		double offset = .05D;
		double d2 = vector.y + 1.1D;
		double yMotion = .05D;
		if (world.isRemote) {
			for (int i = 0; i<8; i++){
				world.spawnParticle("hugeexplosion", (vector.x + offset), d2, (vector.z + offset), 0.0, 0.0, 0.0);
				world.spawnParticle("hugeexplosion", (vector.x - offset), d2, (vector.z + offset), 0.0, 0.0, 0.0);
				world.spawnParticle("hugeexplosion", (vector.x - offset), d2, (vector.z - offset), 0.0, 0.0, 0.0);
				world.spawnParticle("hugeexplosion", (vector.x + offset), d2, (vector.z - offset), 0.0, 0.0, 0.0);
			}
			
		}*/
	}
}
