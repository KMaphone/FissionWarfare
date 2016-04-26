package tm.fissionwarfare.explosion.type;

import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.explosion.ConcreteUtil;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.sounds.FWSound;

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
		ConcreteUtil.generateShockwave(new Location(world, vector), BASIC_SIZE, 1);
		ExplosionUtil.generateExplosion(world, vector, BASIC_SIZE, 8);
	}

	@Override
	public void doPlayerDamage() {
		PlayerExplosionUtil.doLivingDamage(world, vector, BASIC_SIZE * 2, 20);
	}

	@Override
	public void doEffects() {
		FWSound.small_blast.play(world, vector.x, vector.y, vector.z, BASIC_SIZE * 2, 1);
	}
	
	@Override
	public int getMaxFuse() {
		return 80;
	}
}
