package tm.fissionwarfare.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.missile.MissileData;

public class EntityMissile extends Entity implements IEntityAdditionalSpawnData {
	
	public ItemStack missileStack;
	
	public int targetX, targetZ;
	
	private boolean canExplode;
	
	public EntityMissile(World world) {
		super(world);
		setSize(1.3F, 6.5F);
	}
	
	public EntityMissile(World world, double x, double y, double z, int targetX, int targetZ, ItemStack missileStack) {
		super(world);
		setPosition(x + 0.5, y + 0.1F, z + 0.5F);
		this.missileStack = missileStack;
		this.targetX = targetX;
		this.targetZ = targetZ;
		canExplode = false;
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer player) {
		setDead();
	}

	@Override
	public void onUpdate() {
		
		super.onUpdate();
		
		noClip = !canExplode;
		
		moveEntity(motionX, motionY, motionZ);
		
		for (int i = 0; i < 8; i++) {

			double yMotion = canExplode ? 0.5D : -0.5D;
			
			double offset = 0.5D;
			double yOffset = canExplode ? 6.5D : 0;
			
			double randX = MathHelper.getRandomDoubleInRange(rand, -0.1D, 0.1D);
			double randZ = MathHelper.getRandomDoubleInRange(rand, -0.1D, 0.1D);
			
			worldObj.spawnParticle("smoke", posX, posY + yOffset, posZ, randX, yMotion, randZ);			
			
			worldObj.spawnParticle("smoke", posX + offset, posY + yOffset, posZ, randX, yMotion, randZ);			
			worldObj.spawnParticle("smoke", posX - offset, posY + yOffset, posZ, randX, yMotion, randZ);
			worldObj.spawnParticle("smoke", posX, posY + yOffset, posZ + offset, randX, yMotion, randZ);
			worldObj.spawnParticle("smoke", posX, posY + yOffset, posZ - offset, randX, yMotion, randZ);					
			
			if (i % 7 == 0) {
				
				worldObj.spawnParticle("flame", posX, (posY + -motionY) + yOffset, posZ, randX, 0, randZ);
				
				worldObj.spawnParticle("flame", posX + offset, posY + yOffset, posZ, randX, yMotion, randZ);
				worldObj.spawnParticle("flame", posX - offset, posY + yOffset, posZ, randX, yMotion, randZ);
				worldObj.spawnParticle("flame", posX, posY + yOffset, posZ + offset, randX, yMotion, randZ);
				worldObj.spawnParticle("flame", posX, posY + yOffset, posZ - offset, randX, yMotion, randZ);
			}
		}
		
		if (canExplode && onGround) {
			
			MissileData missileData = MissileData.getDataFromItem(missileStack);
				
			IExplosionType explosion = missileData.getExplosionType().getExplosionType();
												
			explosion.setup(worldObj, getVector());
					
			if (!worldObj.isRemote) {
				explosion.doBlockDamage();
				explosion.doPlayerDamage();
			}
			
			explosion.doEffects();
				
			setDead();
		}
		
		if (!canExplode && motionY < 3) {
			
			if (motionY < 0.3) motionY += 0.003F;
			else motionY += 0.2F;
		}
			
		if (posY > 300) {
				
			setPosition(targetX + 0.5F, 300, targetZ + 0.5F);
			motionY = -2;
			canExplode = true;			
		}
	}
	
	public Vector3d getVector() {
		return new Vector3d(posX, posY, posZ);
	}
	
	@Override
	protected void entityInit() {}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		
		targetX = tag.getInteger("targetX");
		targetZ = tag.getInteger("targetZ");
		
		missileStack = ItemStack.loadItemStackFromNBT(tag);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		
		tag.setInteger("targetX", targetX);
		tag.setInteger("targetZ", targetZ);
		
		if (missileStack != null) missileStack.writeToNBT(tag);
	}
	
	@Override
	public void readSpawnData(ByteBuf buffer) {		
		readEntityFromNBT(ByteBufUtils.readTag(buffer));
	}
	
	@Override
	public void writeSpawnData(ByteBuf buffer) {
		NBTTagCompound tag = new NBTTagCompound();
		writeEntityToNBT(tag);
		ByteBufUtils.writeTag(buffer, tag);
	}
}
