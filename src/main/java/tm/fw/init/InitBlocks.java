package tm.fw.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tm.fw.block.BlockBase;
import tm.fw.block.BlockOreBase;

public class InitBlocks {

	public static Block copper_ore, tin_ore, uranium_ore, quartz_ore, sulfur_ore;
	
	public static void init() {
		
		copper_ore = new BlockOreBase("copper_ore");
		tin_ore = new BlockOreBase("tin_ore");
		uranium_ore = new BlockOreBase("uranium_ore");
		
		quartz_ore = new BlockOreBase("quartz_ore").setDroppedItem(InitItems.quartz, 1, 2, 1, 2);
		sulfur_ore = new BlockOreBase("sulfur_ore").setDroppedItem(InitItems.sulfur_dust, 1, 2, 2, 4);
	}	
}