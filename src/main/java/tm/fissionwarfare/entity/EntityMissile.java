package tm.fissionwarfare.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMissile extends Entity {
	
	public EntityMissile(World world) {
		super(world);
		setSize(1.8F, 8.5F);
	}
	
	public EntityMissile(World world, int x, int y, int z) {
		super(world);
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	@Override
	protected void entityInit() {
	
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		
	}
}
