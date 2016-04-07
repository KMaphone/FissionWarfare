package tm.fissionwarfare.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import tm.fissionwarfare.Reference;

public class InitTabs {

	public static final CreativeTabs tabMain = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return Items.apple;
		}
		
		@Override
		public String getTranslatedTabLabel() {			
			return Reference.MOD_NAME + " - Main";
		};
	};
}
