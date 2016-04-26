package tm.fissionwarfare.explosion.type;

import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.explosion.ConcreteUtil;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.math.ShapeUtil;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.sounds.FWSound;

public class FireExplosion implements IExplosionType {

	private static final int BASIC_SIZE = 20;
	
	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}
	
	@Override
	public void doBlockDamage() {
		
		List<Location> list = ShapeUtil.getSphere(new Location(world, vector), BASIC_SIZE);
		
		for (Location location : list) {
			
			Location temp = location.add(ForgeDirection.UP);
			
			if (!location.checkBlock(Blocks.air) && temp.checkBlock(Blocks.air)) {
				temp.setBlock(Blocks.fire);
			}
		}
	}

	@Override
	public void doPlayerDamage() {
	}

	@Override
	public void doEffects() {
		FWSound.small_blast.play(world, vector.x, vector.y, vector.z, BASIC_SIZE * 2, 1);
	}
	
	@Override
	public int getMaxFuse() {
		return 100;
	}
}
