package tm.fissionwarfare.tileentity.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.api.SecurityProfile;
import tm.fissionwarfare.gui.GuiTurret;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.inventory.ContainerTurret;
import tm.fissionwarfare.math.Angle2d;
import tm.fissionwarfare.math.MathUtil;
import tm.fissionwarfare.math.RaytraceUtil;
import tm.fissionwarfare.math.Vector3d;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class TileEntityTurret extends TileEntityEnergyBase implements ISecurity {
		
	public static final int RANGE = 10;	
	public Angle2d angle = new Angle2d(0, 0);	
	public EntityPlayer target;
	
	public SecurityProfile profile = new SecurityProfile();
	
	public TileEntityTurret() {
		setInputSlots(0);
		setSideInputSlots(0);
		setExtractSlots(0);
	}
	
	@Override
	public int getMaxEnergy() {
		return 100000;
	}

	@Override
	public int getMaxReceive() {
		return 10000;
	}

	@Override
	public int getMaxExtract() {
		return 10000;
	}
	
	@Override
	public int getMaxProgress() {
		return 100;
	}
	
	@Override
	public void updateEntity() {
		
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		
		if (!worldObj.isRemote) {
			
			if (target != null && canExtractEnergy(1000)) {
			
				Angle2d targetAngle = Angle2d.getAngleFromVectors(getVector(), new Vector3d(target));
			
				angle.pitch += MathUtil.approach(angle.pitch, targetAngle.pitch, 6);
				angle.yaw = MathUtil.approachRotation(angle.yaw, targetAngle.yaw, 6);	
			
				if (canHit(target)) {
				
					progress++;
				
					if (isDone()) {
						storage.extractEnergy(1000, false);
						fire();						
					}
				} 
			
				else progress = 0;
			
				if (!isTargetInRange(target) || target.capabilities.isCreativeMode) {
					target = null;
				}			
			}
			
			else {
				
				angle.pitch += MathUtil.approach(angle.pitch, 90, 6);
				angle.yaw += 0.5F;
				
				findTarget();
				progress = 0;
			}
		
			isDoneAndReset();
		}	
	}
	
	public boolean canHit(Entity entity) {
		return RaytraceUtil.traceForEntity(getVector(), new Vector3d(target), worldObj);
	}
	
	public void fire() {
		target.attackEntityFrom(DamageSource.generic, 10);
	}
	
	public boolean isTargetInRange(Entity e) {
		return e.getDistance(xCoord + 0.5F, yCoord + 1D, zCoord + 0.5F) <= RANGE;
	}
	
	public Vector3d getVector() {
		return new Vector3d(xCoord + 0.5D, yCoord + 1D, zCoord + 0.5D);
	}
	
	public void findTarget() {
		
		for (Object o : worldObj.loadedEntityList) {
			
			if (o instanceof EntityPlayer) {
				
				EntityPlayer player = (EntityPlayer)o;
				
				if (isTargetInRange(player) && !player.capabilities.isCreativeMode && !profile.isSameTeam(player)) {
					
					target = player;
					return;
				}				
			}
		}
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return stack.getItem() == InitItems.quartz;
	}
	
	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerTurret(player, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiTurret(player, this);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return dir != ForgeDirection.UP;
	}

	@Override
	public SecurityProfile getSecurityProfile() {
		return profile;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	}
	
	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		
		profile.readFromNBT(nbt);
		
		angle.pitch = nbt.getDouble("pitch");
		angle.yaw = nbt.getDouble("yaw");
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		
		profile.writeToNBT(nbt);
		
		nbt.setDouble("pitch", angle.pitch);
		nbt.setDouble("yaw", angle.yaw);
	}
}
