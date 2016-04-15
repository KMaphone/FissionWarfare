package tm.fissionwarfare.explosion.type;

import net.minecraft.world.World;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.math.Vector3d;

public class BasicExplosion implements IExplosionType {

	private static final int BASIC_SIZE = 5;
	
	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}
	
	@Override
	public void doBlockDamage() {
		ExplosionUtil.generateExplosion(world, vector, BASIC_SIZE, 10);
	}

	@Override
	public void doPlayerDamage() {
		PlayerExplosionUtil.doLivingDamage(world, vector, BASIC_SIZE * 2, 20);
	}

	@Override
	public void doEffects() {
		world.playSoundEffect(vector.x, vector.y, vector.z, "random.explode", BASIC_SIZE * 2, 1F);
	}
}
