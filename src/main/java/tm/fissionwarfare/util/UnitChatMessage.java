package tm.fissionwarfare.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class UnitChatMessage {

	private String unitName;
	private EntityPlayer player;
	
	public UnitChatMessage(EntityPlayer player, String unitName) {
		this.player = player;
		this.unitName = unitName;
	}
	
	public void printMessage(EnumChatFormatting format, String message) {
						
		String newMessage = "";
		String[] m = message.split(" ");
		
		for(String t : m){
			newMessage += format;
			newMessage += t;
			newMessage += " ";
		}
		
		player.addChatComponentMessage(new ChatComponentText(getUnitName() + (format + newMessage)));
	}
	
	private int getMessageLength(String message) {
		return getUnitName().length() + message.length();
	}
	
	private String getUnitName() {
		return "[" + unitName + "] ";
	}
}
