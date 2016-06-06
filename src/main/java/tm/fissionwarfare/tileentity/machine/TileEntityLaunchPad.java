package tm.fissionwarfare.tileentity.machine;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.api.SecurityProfile;
import tm.fissionwarfare.entity.EntityMissile;
import tm.fissionwarfare.gui.GuiLaunchPad;
import tm.fissionwarfare.inventory.ContainerLaunchPad;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.missile.MissileData;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.util.UnitChatMessage;

public class TileEntityLaunchPad extends TileEntityEnergyBase implements ISecurity {
	
	private Random rand = new Random();
	
	public SecurityProfile profile = new SecurityProfile();
	
	public int energyCost = 10000;
	
	public int[] targetCoords = new int[2];
	
	public boolean launching;
		
	public TileEntityLaunchPad() {
		setSideInputSlots(0);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (slots[0] == null || !canExtractEnergy(energyCost) || !isPathClear()) {
			launching = false;
		}
		
		if (launching) {
			
			progress++;
			
			for (int i = 0; i < progress / 20; i++) {

				double randX = MathHelper.getRandomDoubleInRange(rand, -0.2D, 0.2D);
				double randZ = MathHelper.getRandomDoubleInRange(rand, -0.2D, 0.2D);
				
				worldObj.spawnParticle("smoke", xCoord + 0.5D, yCoord + 0.77F, zCoord + 0.5D, -0.3D, -0.05D, randX);
				worldObj.spawnParticle("smoke", xCoord + 0.5D, yCoord + 0.77F, zCoord + 0.5D, 0.3D, -0.05D, randX);
				worldObj.spawnParticle("smoke", xCoord + 0.5D, yCoord + 0.77F, zCoord + 0.5D, randZ, -0.05D, 0.3D);
				worldObj.spawnParticle("smoke", xCoord + 0.5D, yCoord + 0.77F, zCoord + 0.5D, randZ, -0.05D, -0.3D);
				
				if (i % 8 == 7) {
					
					worldObj.spawnParticle("flame", xCoord + 0.5D, yCoord + 0.77F, zCoord + 0.5D, -0.3D, -0.05D, randX);
					worldObj.spawnParticle("flame", xCoord + 0.5D, yCoord + 0.77F, zCoord + 0.5D, 0.3D, -0.05D, randX);
					worldObj.spawnParticle("flame", xCoord + 0.5D, yCoord + 0.77F, zCoord + 0.5D, randZ, -0.05D, 0.3D);
					worldObj.spawnParticle("flame", xCoord + 0.5D, yCoord + 0.77F, zCoord + 0.5D, randZ, -0.05D, -0.3D);
				}
			}
		}
		
		else progress = 0;
		
		if (isDoneAndReset()) {
		
			MissileData missileData = MissileData.getDataFromItem(slots[0]);
			
			launching = false;
			storage.extractEnergy(energyCost, false);
			
			if (!worldObj.isRemote) {
				
				int distances[] = {0, 5, 20, 50};
								
				int percentage = rand.nextInt(100 - ((missileData.getAccuracy() - 1) * 10));
				
				int index;
				
				if (percentage < 10) index = 1;
				else if (percentage < 80) index = 2;
				else index = 3;
				
				int x = MathHelper.getRandomIntegerInRange(rand, distances[index - 1], distances[index]);
				int z = MathHelper.getRandomIntegerInRange(rand, distances[index - 1], distances[index]);
				
				if (rand.nextInt(2) == 0) x = 0 - x;
				if (rand.nextInt(2) == 0) z = 0 - z;
								
				worldObj.spawnEntityInWorld(new EntityMissile(worldObj, xCoord, yCoord + 0.6D, zCoord, targetCoords[0] + x, targetCoords[1] + z, slots[0]));
			}					
			
			decrStackSize(0, 1);
		}
	}
	
	public void toggleLaunch(EntityPlayer player) {
		if (launching) launching = false;
		else startLaunch(player);
	}
	
	public void startLaunch(EntityPlayer player) {
			
		if (!launching && slots[0] != null && canExtractEnergy(energyCost) && isPathClear()) {			
			launching = true;
		}
		
		else if (worldObj.isRemote) printErrorMessage(player);
	}
	
	private boolean isPathClear() {
		
		for (int x = -1; x < 2; x++) {
			
			for (int z = -1; z < 2; z++) {
				
				if (!worldObj.canBlockSeeTheSky(xCoord + x, yCoord, zCoord + z)) return false;
			}
		}
		
		return true;
	}
		
	private void printErrorMessage(EntityPlayer player) {
		
		UnitChatMessage message = new UnitChatMessage(player, "Launch Pad");
		
		if (slots[0] == null) message.printMessage(EnumChatFormatting.RED, "There is no missile in the slot!");		
		if (!canExtractEnergy(energyCost)) message.printMessage(EnumChatFormatting.RED, "Not enough energy! (" + energyCost + " RF required)");
		if (!isPathClear()) message.printMessage(EnumChatFormatting.RED, "The path is not cleared! (A 3x3 wide square of blocks need to see the sky)");
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
		return 20 * 10;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 1F, yCoord, zCoord - 1F, xCoord + 2F, yCoord + 7F, zCoord + 2F);
	}
		
	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return dir == ForgeDirection.DOWN;
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerLaunchPad(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiLaunchPad(getTileContainer(player), player, this);
	}

	@Override
	public SecurityProfile getSecurityProfile() {
		return profile;
	}
	
	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		
		profile.readFromNBT(nbt);
		
		NBTTagCompound tempTag = nbt.getCompoundTag("slot_0");

		if (!tempTag.getBoolean("null")) {
			slots[0] = ItemStack.loadItemStackFromNBT(tempTag);
		}
		
		if (nbt.hasKey("coords")) targetCoords = nbt.getIntArray("coords");		
		launching = nbt.getBoolean("launching");
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		
		profile.writeToNBT(nbt);
		
		NBTTagCompound tempTag = new NBTTagCompound();

		if (slots[0] != null) {

			slots[0].writeToNBT(tempTag);
			tempTag.setBoolean("null", false);
		} 
		
		else tempTag.setBoolean("null", true);

		nbt.setTag("slot_0", tempTag);
		
		nbt.setIntArray("coords", targetCoords);		
		nbt.setBoolean("launching", launching);
	}
}
