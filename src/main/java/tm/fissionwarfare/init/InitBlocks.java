package tm.fissionwarfare.init;

import cofh.thermalfoundation.item.TFItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tm.fissionwarfare.block.BlockBase;
import tm.fissionwarfare.block.BlockOreBase;

public class InitBlocks {

	public static Block uranium_ore, quartz_ore, sulfur_ore;
	
	public static void init() {
			
		quartz_ore = new BlockOreBase("quartz").setDroppedItem(InitItems.quartz, 1, 2, 1, 2);
		sulfur_ore = new BlockOreBase("sulfur").setDroppedItem(TFItems.dustSulfur.getItem(), 1, 2, 2, 4);
		
		uranium_ore = new BlockOreBase("uranium");
	}	
}