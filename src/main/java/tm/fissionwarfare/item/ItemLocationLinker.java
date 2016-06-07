package tm.fissionwarfare.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.util.UnitChatMessage;

public class ItemLocationLinker extends ItemBase {

	public ItemLocationLinker() {
		super("location_linker");	
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			
			list.add(EnumChatFormatting.GOLD + "Block Location");
			list.add(EnumChatFormatting.GOLD + "X: " + EnumChatFormatting.AQUA + getNBT(is).getInteger("X"));
			list.add(EnumChatFormatting.GOLD + "Z: " + EnumChatFormatting.AQUA + getNBT(is).getInteger("Z"));
			list.add("");
			list.add("Binds locations to Launch Pad");
			list.add("Right-click : Links a location.");
			list.add("Sneak Right-click : Sends coords to machine.");
		} 
		
		else list.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int i, float f1, float f2, float f3) {
		
		UnitChatMessage message = new UnitChatMessage("Location Linker", player);
		
		if (!world.isRemote) {
						
			if (player.isSneaking()) {
				
				if (world.getTileEntity(x, y, z) instanceof TileEntityLaunchPad) {
					
					TileEntityLaunchPad tileEntity = (TileEntityLaunchPad)world.getTileEntity(x, y, z);
					
					tileEntity.targetCoords[0] = getNBT(is).getInteger("X");
					tileEntity.targetCoords[1] = getNBT(is).getInteger("Z");
					
					player.worldObj.playSoundAtEntity(player, "random.orb", 1F, 1F);
					
					message.printMessage(EnumChatFormatting.GREEN, "Launch Pad's coords set to " + getNBT(is).getInteger("X") + ", " + getNBT(is).getInteger("Z"));
					return true;
				}				
			}
			
			getNBT(is).setInteger("X", x);	
			getNBT(is).setInteger("Z", z);
			
			player.worldObj.playSoundAtEntity(player, "random.click", 1F, 1F);
			
			message.printMessage(EnumChatFormatting.GREEN, "Coords set to " + x + ", " + z);
			return true;					
		}	
		
		return false;
	}	
}