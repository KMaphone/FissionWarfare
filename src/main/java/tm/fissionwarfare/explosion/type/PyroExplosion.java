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

public class PyroExplosion implements IExplosionType {

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
	
	@Override
	public int getMaxFuse() {
		return 80;
	}
}
