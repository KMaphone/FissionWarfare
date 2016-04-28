package tm.fissionwarfare.item;

import java.util.List;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import scala.tools.nsc.backend.icode.TypeKinds.REFERENCE;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitItems;

public class ItemCement extends ItemBase {

	private IIcon wet_icon;
	
	public ItemCement(String imagePath) {
		super(imagePath);
		ThermalExpansionHelper.addTransposerFill(1000, new ItemStack(this),	new ItemStack(this, 1, 1), new FluidStack(FluidRegistry.WATER, 1000), false);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "_" + is.getItemDamage();
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return meta == 1 ? wet_icon : itemIcon;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconReg) {
		itemIcon = iconReg.registerIcon(Reference.MOD_ID + ":" + imageName);
		wet_icon = iconReg.registerIcon(Reference.MOD_ID + ":" + "wet_" + imageName);
	}
}
