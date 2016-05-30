package tm.fissionwarfare.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import tm.fissionwarfare.missile.MissileData;

public class ItemMissile extends ItemBase {

	public ItemMissile() {
		super("missile", false);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		
		MissileData missileData = MissileData.getDataFromItem(is);
		
		if (missileData.getExplosionType() != null) list.add(EnumChatFormatting.GOLD + "Type: " + EnumChatFormatting.AQUA + missileData.getExplosionType().getName());
		list.add(EnumChatFormatting.GOLD + "Accuracy Tier: " + EnumChatFormatting.AQUA + missileData.getAccuracy());
		list.add(EnumChatFormatting.GOLD + "Speed Tier: " + EnumChatFormatting.AQUA + missileData.getSpeed());
	}
}
