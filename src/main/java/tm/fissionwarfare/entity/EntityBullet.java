package tm.fissionwarfare.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tm.fissionwarfare.math.Angle2d;
import tm.fissionwarfare.math.Vector3d;

public class EntityBullet extends EntityThrowable {

	public Vector3d velcity;
	public double speed;
	
	public float damage;
	
	public EntityBullet(World world) {
		super(world);
	}
	
	public void shoot(Angle2d angle, double speed, float damage) {
		this.velcity = Vector3d.getVectorFromAngle(angle);
		this.speed = speed;
		this.damage = damage;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		motionX = velcity.x * speed;
		motionY = velcity.y * speed;
		motionZ = velcity.z * speed;
	}

	@Override
	public void onImpact(MovingObjectPosition pos) {
		
		Entity entity = pos.entityHit;
		
		if (entity != null && entity instanceof EntityLivingBase) {
			
			EntityLivingBase living = (EntityLivingBase) entity;
			
			living.attackEntityFrom(DamageSource.generic, damage);
		}
	}
}
