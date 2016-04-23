package tm.fissionwarfare.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import scala.tools.nsc.backend.icode.TypeKinds.REFERENCE;
import tm.fissionwarfare.Reference;

public class ItemCement extends ItemBase {

	private IIcon wet_icon;
	
	public ItemCement(String imagePath) {
		super(imagePath);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconReg) {
		itemIcon = iconReg.registerIcon(Reference.MOD_ID + ":" + imagePath);
		wet_icon = iconReg.registerIcon(Reference.MOD_ID + ":" + "wet_" + imagePath);
	}
}
