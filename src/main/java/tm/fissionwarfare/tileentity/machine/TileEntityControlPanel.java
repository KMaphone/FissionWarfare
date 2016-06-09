package tm.fissionwarfare.tileentity.machine;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import tm.fissionwarfare.gui.GuiControlPanel;
import tm.fissionwarfare.inventory.ContainerEnergyBase;
import tm.fissionwarfare.inventory.ContainerLaunchPad;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.missile.MissileData;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.util.UnitChatMessage;

public class TileEntityControlPanel extends TileEntityEnergyBase implements ISecurity {

	private Random rand = new Random();
	
	public SecurityProfile profile = new SecurityProfile();
	
	public int energyCost = 10000;
	
	public int[] targetCoords = new int[2];
	
	public boolean launching;
		
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (getLaunchPad() == null || !isLaunchPadRotatedCorrectly() || getLaunchPad().slots[0] == null || !canExtractEnergy(energyCost) || !isPathClear()) {
			launching = false;
		}
		
		if (launching) {
			
			progress++;
			
			for (int i = 0; i < progress / 20; i++) {

				double randX = MathHelper.getRandomDoubleInRange(rand, -0.2D, 0.2D);
				double randZ = MathHelper.getRandomDoubleInRange(rand, -0.2D, 0.2D);
				
				int x = getLaunchPad().xCoord;
				int y = getLaunchPad().yCoord;
				int z = getLaunchPad().zCoord;
				
				worldObj.spawnParticle("smoke", x + 0.5D, y + 0.77F, z + 0.5D, -0.3D, -0.05D, randX);
				worldObj.spawnParticle("smoke", x + 0.5D, y + 0.77F, z + 0.5D, 0.3D, -0.05D, randX);
				worldObj.spawnParticle("smoke", x + 0.5D, y + 0.77F, z + 0.5D, randZ, -0.05D, 0.3D);
				worldObj.spawnParticle("smoke", x + 0.5D, y + 0.77F, z + 0.5D, randZ, -0.05D, -0.3D);
				
				if (i % 8 == 7) {
					
					worldObj.spawnParticle("flame", x + 0.5D, y + 0.77F, z + 0.5D, -0.3D, -0.05D, randX);
					worldObj.spawnParticle("flame", x + 0.5D, y + 0.77F, z + 0.5D, 0.3D, -0.05D, randX);
					worldObj.spawnParticle("flame", x + 0.5D, y + 0.77F, z + 0.5D, randZ, -0.05D, 0.3D);
					worldObj.spawnParticle("flame", x + 0.5D, y + 0.77F, z + 0.5D, randZ, -0.05D, -0.3D);
				}
			}
		}
		
		else progress = 0;
		
		if (isDoneAndReset()) {
		
			MissileData missileData = MissileData.getDataFromItem(getLaunchPad().slots[0]);
			
			launching = false;
			storage.extractEnergy(energyCost, false);
			
			if (!worldObj.isRemote) {
				
				int distances[] = {0, 5, 20, 50};
								
				int percentage = rand.nextInt(100 - ((missileData.getAccuracy() - 1) * 20));
				
				int index;
				
				if (percentage < 20) index = 1;
				else if (percentage < 80) index = 2;
				else index = 3;
				
				int x = MathHelper.getRandomIntegerInRange(rand, distances[index - 1], distances[index]);
				int z = MathHelper.getRandomIntegerInRange(rand, distances[index - 1], distances[index]);
				
				if (rand.nextInt(2) == 0) x = 0 - x;
				if (rand.nextInt(2) == 0) z = 0 - z;
								
				worldObj.spawnEntityInWorld(new EntityMissile(worldObj, getLaunchPad().xCoord, getLaunchPad().yCoord + 0.6D, getLaunchPad().zCoord, targetCoords[0] + x, targetCoords[1] + z, getLaunchPad().slots[0]));
			}
			
			getLaunchPad().decrStackSize(0, 1);
		}
	}
	
	public void toggleLaunch(EntityPlayer player) {
		if (launching) launching = false;
		else startLaunch(player);
	}
	
	public void startLaunch(EntityPlayer player) {
			
		if (!launching && getLaunchPad() != null && isLaunchPadRotatedCorrectly() && getLaunchPad().slots[0] != null && canExtractEnergy(energyCost) && isPathClear()) {			
			launching = true;
		}
		
		else if (worldObj.isRemote) printErrorMessage(player);
	}
	
	private boolean isPathClear() {
		
		for (int x = -1; x < 2; x++) {
			
			for (int z = -1; z < 2; z++) {
				
				if (!worldObj.canBlockSeeTheSky(getLaunchPad().xCoord + x, getLaunchPad().yCoord, getLaunchPad().zCoord + z)) return false;
			}
		}
		
		return true;
	}
	
	public TileEntityLaunchPad getLaunchPad() {
		
		Location loc;
		
		if (getBlockMetadata() == 0) loc = new Location(worldObj, xCoord, yCoord, zCoord - 1);
		else if (getBlockMetadata() == 1) loc = new Location(worldObj, xCoord + 1, yCoord, zCoord);
		else if (getBlockMetadata() == 2) loc = new Location(worldObj, xCoord, yCoord, zCoord + 1);
		else if (getBlockMetadata() == 3) loc = new Location(worldObj, xCoord - 1, yCoord, zCoord);
		else loc = new Location(worldObj, xCoord, yCoord, zCoord);
		
		if (loc.hasTileEntity() & loc.getTileEntity() instanceof TileEntityLaunchPad) {
			return (TileEntityLaunchPad)loc.getTileEntity();
		}
		
		return null;
	}
	
	private boolean isLaunchPadRotatedCorrectly() {
		return getBlockMetadata() == getLaunchPad().getBlockMetadata();
	}
		
	private void printErrorMessage(EntityPlayer player) {
		
		UnitChatMessage message = new UnitChatMessage("Control Panel", player);
		
		if (getLaunchPad() == null) message.printMessage(EnumChatFormatting.RED, "No Launch Pad connected!");
		
		else {
			if (!isLaunchPadRotatedCorrectly()) message.printMessage(EnumChatFormatting.RED, "Launch Pad isn't rotated correctly!");
			if (getLaunchPad().slots[0] == null) message.printMessage(EnumChatFormatting.RED, "No missile in the Launch Pad!");
			if (!isPathClear()) message.printMessage(EnumChatFormatting.RED, "The path is not cleared! (A 3x3 wide square of blocks above the Launch Pad need to see the sky)");
		}		
		
		if (!canExtractEnergy(energyCost)) message.printMessage(EnumChatFormatting.RED, "Not enough energy! (" + energyCost + " RF required)");
		
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
		MissileData missileData = null;;
		if (getLaunchPad() != null && getLaunchPad().slots[0] != null) missileData = MissileData.getDataFromItem(getLaunchPad().slots[0]);
		return (20 * 20) - (missileData == null ? 0 : missileData.getSpeed() * 4);
	}
		
	@Override
	public int getSizeInventory() {
		return 0;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return dir == ForgeDirection.DOWN;
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerEnergyBase(player, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiControlPanel(getTileContainer(player), player, this);
	}

	@Override
	public SecurityProfile getSecurityProfile() {
		return profile;
	}
	
	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		
		profile.readFromNBT(nbt);
		
		if (nbt.hasKey("coords")) targetCoords = nbt.getIntArray("coords");
		launching = nbt.getBoolean("launching");
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		
		profile.writeToNBT(nbt);
		
		nbt.setIntArray("coords", targetCoords);		
		nbt.setBoolean("launching", launching);
	}
}
