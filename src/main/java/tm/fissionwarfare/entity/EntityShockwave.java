package tm.fissionwarfare.entity;

import net.minecraft.client.particle.EntityHugeExplodeFX;
import net.minecraft.world.World;

public class EntityShockwave extends EntityHugeExplodeFX{

	public EntityShockwave(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
	}
	
	@Override
	public boolean isInRangeToRenderDist(double distance) {
		return(distance < 64);
	}

}
