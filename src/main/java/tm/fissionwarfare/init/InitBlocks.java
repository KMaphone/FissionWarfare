package tm.fissionwarfare.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tm.fissionwarfare.block.BlockBase;
import tm.fissionwarfare.block.BlockOreBase;

public class InitBlocks {

	public static Block copper_ore, tin_ore, uranium_ore, quartz_ore, sulfur_ore;
	
	public static void init() {
		
		copper_ore = new BlockOreBase("copper");
		tin_ore = new BlockOreBase("tin");
		uranium_ore = new BlockOreBase("uranium");
		
		quartz_ore = new BlockOreBase("quartz").setDroppedItem(InitItems.quartz, 1, 2, 1, 2);
		sulfur_ore = new BlockOreBase("sulfur").setDroppedItem(InitItems.sulfur_dust, 1, 2, 2, 4);
	}	
}