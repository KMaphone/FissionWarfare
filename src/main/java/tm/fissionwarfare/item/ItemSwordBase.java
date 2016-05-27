package tm.fissionwarfare.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemSword;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;

public class ItemSwordBase extends ItemSword {

	private String imagePath;
	private ToolMaterial toolMaterial;

	public ItemSwordBase(String imagePath, ToolMaterial toolMaterial) {
		super(toolMaterial);
		this.imagePath = imagePath;
		this.toolMaterial = toolMaterial;
		setCreativeTab(InitTabs.tabMain);
		setUnlocalizedName(imagePath);
		GameRegistry.registerItem(this, imagePath);
	}

}
