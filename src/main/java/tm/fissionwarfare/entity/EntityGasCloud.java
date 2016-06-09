package tm.fissionwarfare.entity;

import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGasCloud extends Entity {

	private double range = 5;
	private double damage = 5;
	private double life = 20 * 10;

	public EntityGasCloud(World world) {
		super(world);
	}

	public EntityGasCloud(World world, double x, double y, double z) {
		super(world);
		setPosition(x, y, z);
	}

	@Override
	public void entityInit() {

	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (ticksExisted > life) {
			setDead();
		}
		
		if (ticksExisted % 20 == 0) {
			doDamage();
		}
		
		if (worldObj.isRemote) {
			doEffects();
		}
	}

	private void doEffects() {
			
		double x = posX + (rand.nextDouble() * (range * 2)) - range;
		double y = posY + (rand.nextDouble() * (range * 2)) - range;
		double z = posZ + (rand.nextDouble() * (range * 2)) - range;
			
		worldObj.spawnParticle("hugeexplosion", x, y, z, 0.0, 0.0, 0.0);
	}

	private void doDamage() {

		CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>(worldObj.loadedEntityList);

		for (Object obj : list) {

			if (obj instanceof EntityLivingBase) {

				EntityLivingBase living = (EntityLivingBase) obj;

				if (living.getDistance(posX, posY, posZ) <= range) {

					living.attackEntityFrom(DamageSource.magic, (float) damage);
				}
			}
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		range = nbt.getDouble("range");
		damage = nbt.getDouble("damage");
		life = nbt.getDouble("life");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setDouble("range", range);
		nbt.setDouble("damage", damage);
		nbt.setDouble("life", life);
	}
}
