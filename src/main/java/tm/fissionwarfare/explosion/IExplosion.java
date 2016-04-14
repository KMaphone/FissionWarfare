package tm.fissionwarfare.explosion;

import net.minecraft.world.World;
import tm.fissionwarfare.math.Vector3d;

public interface IExplosion {
	
	public void setup(World world, Vector3d vector);
	
	public void doBlockDamage();
	
	public void doPlayerDamage();
	
	public void doEffects();
}
