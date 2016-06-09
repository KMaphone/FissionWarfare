package tm.fissionwarfare.tileentity.machine;

import java.util.Random;

import org.lwjgl.opengl.GL11;

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
import tm.fissionwarfare.inventory.ContainerLaunchPad;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.missile.MissileData;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;
import tm.fissionwarfare.util.UnitChatMessage;

public class TileEntityLaunchPad extends TileEntityInventoryBase implements ISecurity {

	public SecurityProfile profile = new SecurityProfile();
	
	@Override
	public int getSizeInventory() {
		return 1;
	}
		
	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerLaunchPad(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return null;
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
		
		NBTTagCompound tempTag = nbt.getCompoundTag("slot_0");

		if (!tempTag.getBoolean("null")) {
			slots[0] = ItemStack.loadItemStackFromNBT(tempTag);
		}
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		
		NBTTagCompound tempTag = new NBTTagCompound();

		if (slots[0] != null) {

			slots[0].writeToNBT(tempTag);
			tempTag.setBoolean("null", false);
		} 
		
		else tempTag.setBoolean("null", true);

		nbt.setTag("slot_0", tempTag);
	}	
}
