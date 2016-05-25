package tm.fissionwarfare.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.missile.MissileData;

public class ItemMissile extends ItemBase {

	public ItemMissile() {
		super("missile");
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		
		MissileData missileData = MissileData.getDataFromItem(is);
		
		System.out.println(missileData == null);
		
		if (missileData.getExplosionType() != null) list.add("Type: " + missileData.getExplosionType().getName());
		list.add("Accuracy: " + missileData.getAccuracy());
		list.add("Speed: " + missileData.getSpeed());
	}
}
