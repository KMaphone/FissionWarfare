package tm.fissionwarfare.explosion.type;

import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.explosion.ConcreteUtil;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.sounds.FWSound;

public class AtomicExplosion extends BasicExplosion implements IExplosionType {
	
	private final static int BASIC_SIZE = 15;
	
	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}
	
	@Override
	public void doBlockDamage() {
		ConcreteUtil.generateShockwave(new Location(world, vector), BASIC_SIZE, 2);
		ExplosionUtil.generateExplosion(world, vector, BASIC_SIZE, 2);
	}

	@Override
	public void doPlayerDamage() {
		PlayerExplosionUtil.doLivingDamage(world, vector, BASIC_SIZE * 2, 20);
	}

	@Override
	public void doEffects() {
		if (world.isRemote) {
			System.out.println("Boom");
			FWSound.small_blast.play(world, vector.x, vector.y, vector.z, 10, 1);
			FWSound.rumbling.play(world, vector.x, vector.y, vector.z, 1, 1);
		}
	}
}
