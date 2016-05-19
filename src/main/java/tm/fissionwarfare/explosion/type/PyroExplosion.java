package tm.fissionwarfare.explosion.type;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.explosion.ConcreteUtil;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.sounds.FWSound;

public class PyroExplosion implements IExplosionType {

	public static final int SIZE = 5;
	public static final int DAMAGE = 10;
	
	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}
	
	@Override
	public void doBlockDamage() {
		List<Location> locations = ExplosionUtil.getgEffectedExplosionBlocks(world, vector, SIZE, 1);
		
		for (Location loc : locations) {
			
			Location loc2 = loc.copy().add(ForgeDirection.DOWN);
			
			if (loc.checkBlock(Blocks.air) && !loc2.checkBlock(Blocks.air)) {
				
				loc2.setBlock(Blocks.fire);
			}
		}
	}

	@Override
	public void doPlayerDamage() {
		PlayerExplosionUtil.doLivingDamage(world, vector, SIZE * 2, DAMAGE);
	}

	@Override
	public void doEffects() {
		FWSound.small_blast.play(world, vector.x, vector.y, vector.z, SIZE * 2, 1);
	}
	
	@Override
	public int getMaxFuse() {
		return 80;
	}
}
