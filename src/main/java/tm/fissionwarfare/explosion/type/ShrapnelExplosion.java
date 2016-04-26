package tm.fissionwarfare.explosion.type;

import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.explosion.ConcreteUtil;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.IExplosionType;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.math.ShapeUtil;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.sounds.FWSound;

public class ShrapnelExplosion implements IExplosionType {

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
		PlayerExplosionUtil.doLivingDamage(world, vector, 20, 100);
	}

	@Override
	public void doEffects() {
		FWSound.small_blast.play(world, vector.x, vector.y, vector.z, 10F, 1F);
	}
	
	@Override
	public int getMaxFuse() {
		return 100;
	}
}
