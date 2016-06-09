package tm.fissionwarfare.tileentity.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.item.EntityItem;
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
import tm.fissionwarfare.block.BlockControlPanel;
import tm.fissionwarfare.block.BlockSupportFrame;
import tm.fissionwarfare.entity.EntityMissile;
import tm.fissionwarfare.gui.GuiControlPanel;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.inventory.ContainerEnergyBase;
import tm.fissionwarfare.inventory.ContainerLaunchPad;
import tm.fissionwarfare.itemblock.ItemSupportFrame;
import tm.fissionwarfare.math.Location;
import tm.fissionwarfare.missile.MissileData;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.util.UnitChatMessage;

public class TileEntityLaunchPad extends TileEntityEnergyBase implements ISecurity {

	private Random rand = new Random();
	
	public SecurityProfile profile = new SecurityProfile();
	
	public int energyCost = 10000;
	
	public ItemStack missile;
	
	public int[] targetCoords = new int[2];
	
	public boolean launching;
		
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (!worldObj.isRemote) {
			checkForFullFrame();
		}
		
		if (getControlPanel() == null || getSupportFrame() == null || missile == null || !canExtractEnergy(energyCost) || !isPathClear()) {
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
		
			MissileData missileData = MissileData.getDataFromItem(missile);
			
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
								
				worldObj.spawnEntityInWorld(new EntityMissile(worldObj, xCoord, yCoord + 0.6D, zCoord, targetCoords[0] + x, targetCoords[1] + z, missile));
			}
			
			decrStackSize(0, 1);
		}
	}
	
	public void toggleLaunch(EntityPlayer player) {
		if (launching) launching = false;
		else startLaunch(player);
	}
	
	public void startLaunch(EntityPlayer player) {
			
		if (!launching && getControlPanel() != null && getSupportFrame() != null && missile != null && canExtractEnergy(energyCost) && isPathClear()) {			
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
	
	public BlockControlPanel getControlPanel() {
		
		Location loc = getLocation().add(getBlockMetadata(), false);
		
		if (loc.getBlock() instanceof BlockControlPanel) {
			return (BlockControlPanel)loc.getBlock();
		}
		
		return null;
	}
	
	public BlockSupportFrame getSupportFrame() {
		
		Location loc = getLocation().add(getBlockMetadata(), true);
		
		if (loc.getBlock() instanceof BlockSupportFrame) {
			return (BlockSupportFrame)loc.getBlock();
		}
		
		return null;
	}
	
	public List<Location> getFrame() {
		
		List<Location> locs = new ArrayList<Location>();
		
		for (int xzOffset = -1; xzOffset < 2; xzOffset++) {
			
			for (int yOffset = 0; yOffset < 7; yOffset++) {
				
				locs.add(new Location(worldObj, xCoord + (ItemSupportFrame.shouldRotate(getBlockMetadata()) ? xzOffset : 0), yOffset + yOffset, zCoord + (!ItemSupportFrame.shouldRotate(getBlockMetadata()) ? xzOffset : 0)));
			}
		}
		
		return locs;
	}
	
	public void checkForFullFrame() {
		
		for (Location loc : getFrame()) {
			
			if (!(loc.getBlock() instanceof BlockSupportFrame)) {
				
				destroyFrame();
				break;
			}
		}
	}
	
	public void destroyFrame() {
		
		worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord + 0.5F, zCoord, new ItemStack(InitItems.support_frame)));		
		
		for (Location loc : getFrame()) {
			
			loc.setBlockToAir();			
		}
	}
	
	private void printErrorMessage(EntityPlayer player) {
		
		UnitChatMessage message = new UnitChatMessage("Launch Pad", player);
		
		if (getSupportFrame() == null) message.printMessage(EnumChatFormatting.RED, "No Support Frame connected!");
		if (getControlPanel() == null) message.printMessage(EnumChatFormatting.RED, "No Control Panel connected!");
		
		else {
			if (missile == null) message.printMessage(EnumChatFormatting.RED, "No missile in this unit!");
			if (!isPathClear()) message.printMessage(EnumChatFormatting.RED, "The path is not cleared! (A 3x3 wide square of blocks need to see the sky)");
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
		if (getControlPanel() != null && missile != null) missileData = MissileData.getDataFromItem(missile);
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
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 1F, yCoord, zCoord - 1F, xCoord + 2F, yCoord + 7F, zCoord + 2F);
	}
		
	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		
		NBTTagCompound tempTag = nbt.getCompoundTag("missile");

		if (!tempTag.getBoolean("null")) {
			missile = ItemStack.loadItemStackFromNBT(tempTag);
		}
		
		profile.readFromNBT(nbt);
		
		if (nbt.hasKey("coords")) targetCoords = nbt.getIntArray("coords");
		launching = nbt.getBoolean("launching");
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		
		NBTTagCompound tempTag = new NBTTagCompound();

		if (missile != null) {

			missile.writeToNBT(tempTag);
			tempTag.setBoolean("null", false);
		} 
		
		else tempTag.setBoolean("null", true);

		nbt.setTag("missile", tempTag);
		
		profile.writeToNBT(nbt);
		
		nbt.setIntArray("coords", targetCoords);		
		nbt.setBoolean("launching", launching);		
	}
}
