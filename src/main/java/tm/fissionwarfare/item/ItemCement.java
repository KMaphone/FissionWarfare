package tm.fissionwarfare.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tm.fissionwarfare.api.ITieredItem;

public class ItemCement extends ItemTiered implements ITieredItem {

	private IIcon[] textures= new IIcon[6];
	
	public ItemCement() {
		super("cement", 6);
	}
	
	private int getTierFromMeta(int meta) {
		return (meta % 3) + 1;
	}
	
	@Override
	public int getTier(ItemStack stack) {
		return getTierFromMeta(stack.getItemDamage());
	}
		
	@Override
	public String getUnlocalizedName(ItemStack is) {		
		return "item." + (is.getItemDamage() > 2 ? ("wet_") : "") + imageName;
	}
}
