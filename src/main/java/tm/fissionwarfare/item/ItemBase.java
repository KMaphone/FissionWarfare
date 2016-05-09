package tm.fissionwarfare.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;

public class ItemBase extends Item {
	
	public String imageName;
	
	public ItemBase(String imageName) {
		this.imageName = imageName;
		setUnlocalizedName(imageName);
		setTextureName(Reference.MOD_ID + ":" + imageName);
		setCreativeTab(InitTabs.tabMain);
		GameRegistry.registerItem(this, imageName);
	}
}