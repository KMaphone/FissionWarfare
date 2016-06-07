package tm.fissionwarfare.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;

public class ItemBase extends Item {
	
	public String imageName;
	public boolean hasTexture;
	
	public ItemBase(String imageName, boolean hasTexture) {
		this.imageName = imageName;
		this.hasTexture = hasTexture;
		setUnlocalizedName(imageName);
		setTextureName(Reference.MOD_ID + ":" + imageName);
		setCreativeTab(InitTabs.tabMain);
		GameRegistry.registerItem(this, imageName);
	}
	
	public ItemBase(String imageName) {
		this(imageName, true);
	}
	
	@Override
	public void registerIcons(IIconRegister iconReg) {
		if (hasTexture) super.registerIcons(iconReg);
	}
}