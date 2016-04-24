package tm.fissionwarfare.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockConcrete extends ItemBlockMeta {

	public ItemBlockConcrete(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		
		int meta = is.getItemDamage();
		int i;
				
		if (meta > 9) i = 2;
		else if (meta > 4) i = 1;
		else i = 0;
		
		return getUnlocalizedName() + "_" + i;
	}
}
