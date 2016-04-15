package tm.fissionwarfare.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tm.fissionwarfare.explosion.type.IExplosionType;
import tm.fissionwarfare.explosion.type.IExplosiveBlock;
import tm.fissionwarfare.math.Vector3d;

public class EntityExplosive extends Entity implements IEntityAdditionalSpawnData {

	public Block block;
	public int fuse = 100;

	public EntityExplosive(World world) {
		super(world);
	}

	public EntityExplosive(World world, double x, double y, double z, Block block) {
		this(world);
		this.block = block;
		setPosition(x, y, z);
	}

	@Override
	protected void entityInit() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		preventEntitySpawning = true;
		setSize(0.95F, 0.95F);
	}

	public void onUpdate() {

		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		
		moveEntity(motionX, motionY, motionZ);

		if (!onGround) {
			motionY -= 0.02D;
		} else {
			motionY = 0;
		}

		if (!isDead && fuse <= 0) {

			setDead();
			explode();
		}
		
		fuse--;
		worldObj.spawnParticle("smoke", posX, posY + 0.5, posZ, 0, 0, 0);
	}

	private void explode() {
		
		if (block instanceof IExplosiveBlock) {
			
			IExplosionType explosion = ((IExplosiveBlock) block).getExplosion();
			
			explosion.setup(worldObj, getVector());
			
			if (!worldObj.isRemote) {
				explosion.doBlockDamage();
				explosion.doPlayerDamage();
			}
			
			explosion.doEffects();
		}
	}
	
	public Vector3d getVector() {
		return new Vector3d(posX, posY, posZ);
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0F;
	}
	
	@Override
	public boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("Fuse", fuse);
		nbt.setInteger("Block", Block.getIdFromBlock(block));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		fuse = nbt.getInteger("Fuse");
		block = Block.getBlockById(nbt.getInteger("Block"));
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		NBTTagCompound nbt = new NBTTagCompound();
		writeEntityToNBT(nbt);
		ByteBufUtils.writeTag(buffer, nbt);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		NBTTagCompound nbt = ByteBufUtils.readTag(additionalData);
		readEntityFromNBT(nbt);
	}
}
