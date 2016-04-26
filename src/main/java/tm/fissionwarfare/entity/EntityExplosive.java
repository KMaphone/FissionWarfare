package tm.fissionwarfare.entity;

import cofh.lib.audio.ISoundSource;
import cofh.lib.audio.SoundBase;
import cofh.lib.util.helpers.SoundHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.explosion.IExplosionType;
import tm.fissionwarfare.explosion.IExplosiveBlock;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.sounds.BombSound;

public class EntityExplosive extends Entity implements IEntityAdditionalSpawnData {

	public BlockExplosive block;
	public int fuse;
	
	private SoundBase beep;

	public EntityExplosive(World world) {
		super(world);
	}

	public EntityExplosive(World world, int x, int y, int z, BlockExplosive block) {
		this(world);
		this.block = block;
		this.fuse = block.getExplosion().getMaxFuse();
		setPosition(x + 0.5D, y + 0.5D, z + 0.5D);
	}

	@Override
	protected void entityInit() {
		SoundHelper.playSound(new BombSound(this));
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
		
		IExplosionType explosion = block.getExplosion();
		
		explosion.setup(worldObj, getVector());
			
		if (!worldObj.isRemote) {
			explosion.doBlockDamage();
			explosion.doPlayerDamage();
		}
			
		explosion.doEffects();
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
		block = (BlockExplosive) Block.getBlockById(nbt.getInteger("Block"));
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
