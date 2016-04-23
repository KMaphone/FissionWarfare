package tm.fissionwarfare.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.item.ItemBlockMeta;

public class BlockMetaBase extends BlockBase {

	public BlockMetaBase(String imagePath, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound) {
		super(imagePath, material, harvestLevel, hardness, resistance, stepSound, false);
		setCreativeTab(InitTabs.tabMain);
		GameRegistry.registerBlock(this, ItemBlockMeta.class, imagePath);
	}		
}
