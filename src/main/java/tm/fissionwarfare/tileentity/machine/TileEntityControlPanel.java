package tm.fissionwarfare.tileentity.machine;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.api.SecurityProfile;
import tm.fissionwarfare.tileentity.base.TileEntityBase;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class TileEntityControlPanel extends TileEntityBase implements ISecurity {
	
	public SecurityProfile profile = new SecurityProfile();
	
	public int[] targetCoords = new int[2];

	@Override
	public SecurityProfile getSecurityProfile() {
		return profile;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readSyncNBT(nbt);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeSyncNBT(nbt);
	}
	
	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		
		if (nbt.hasKey("coords")) targetCoords = nbt.getIntArray("coords");
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		
		nbt.setIntArray("coords", targetCoords);
	}
}
