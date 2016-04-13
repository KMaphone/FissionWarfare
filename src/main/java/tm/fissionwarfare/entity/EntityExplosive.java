package tm.fissionwarfare.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.world.FluxExplosion;

public class EntityExplosive extends Entity {

	public int fuse = 80;

	public EntityExplosive(World world) {
		super(world);
		this.preventEntitySpawning = true;
		this.yOffset = this.height / 2.0F;
	}

	public EntityExplosive(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y, z);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	@Override
	protected void entityInit() {
		setSize(1F, 1F);
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	public void onUpdate() {
		
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.04;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.onGround) {
			
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}

		if (this.fuse-- <= 0) {
			
			this.setDead();
			
			if (!this.worldObj.isRemote) {
				this.explode();
			}
		} 
		
		else this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
	}

	private void explode() {
		FluxExplosion explosion = new FluxExplosion(worldObj, 10, 1, new Vector3d(posX, posY, posZ));
		explosion.doExplosionPartA();
		explosion.doExplosionPartB();
		explosion.doExplosionPartC();
	}
	
	protected void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setInteger("Fuse", this.fuse);
    }

    protected void readEntityFromNBT(NBTTagCompound nbt) {
        this.fuse = nbt.getInteger("Fuse");
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.0F;
    }
}
