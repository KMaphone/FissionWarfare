package tm.fissionwarfare.explosion.type;

import java.util.List;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.math.ShapeUtil;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.sounds.FWSound;

public class EMPExplosion implements IExplosionType {

	public static final int SIZE = 5;
	
	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}
	
	@Override
	public void doBlockDamage() {
		
		List<Location> locations = ShapeUtil.getSphere(new Location(world, vector), SIZE);
				
		for (Location loc : locations) {
			
			if (loc.hasTileEntity() && loc.getTileEntity() instanceof IEnergyHandler) {
				
				IEnergyHandler energy = (IEnergyHandler) loc.getTileEntity();
				
				while (energy.getEnergyStored(ForgeDirection.UNKNOWN) > 0) {
					System.out.println(energy.extractEnergy(ForgeDirection.UNKNOWN, energy.getEnergyStored(ForgeDirection.UNKNOWN), false));
				}			
			}
		}
	}

	@Override
	public void doPlayerDamage() {
		
	}

	@Override
	public void doEffects() {
		FWSound.small_blast.play(world, vector.x, vector.y, vector.z, SIZE * 2, 1);
	}
}
