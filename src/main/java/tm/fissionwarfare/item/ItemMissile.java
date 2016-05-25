package tm.fissionwarfare.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.missile.MissileData;

public class ItemMissile extends ItemBase {

	public MissileData missileData = new MissileData();
	
	public ItemMissile() {
		super("missile");
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		
		MissileData.getDataFromItem(is);
		
		list.add("Type: " + missileData.getExplosionType().getName());
		list.add("Accuracy: " + missileData.getAccuracy());
		list.add("Speed: " + missileData.getSpeed());
	}
}
