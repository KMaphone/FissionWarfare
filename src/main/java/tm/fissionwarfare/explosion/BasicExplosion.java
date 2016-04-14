package tm.fissionwarfare.explosion;

import net.minecraft.world.World;
import tm.fissionwarfare.math.Vector3d;

public class BasicExplosion implements IExplosion {

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
		
	}

	@Override
	public void doEffects() {
		
	}
}
