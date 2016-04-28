package tm.fissionwarfare.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.Reference;

public class InitTabs {

	public static final CreativeTabs tabMain = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(InitBlocks.concrete);
		}
		
		public ItemStack getIconItemStack() {
			return new ItemStack(getTabIconItem(), 1, 4);
		}
		
		@Override
		public String getTranslatedTabLabel() {			
			return Reference.MOD_NAME + " - Main";
		}
	};
}
