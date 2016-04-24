package tm.fissionwarfare.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import tm.fissionwarfare.item.ItemBlockMeta;

public class BlockMetaBase extends BlockBase {

	public BlockMetaBase(String imagePath, Material material, int harvestLevel, float hardness, float resistance, SoundType stepSound, CreativeTabs creativeTab, Class<? extends ItemBlockMeta> itemBlock) {
		super(imagePath, material, harvestLevel, hardness, resistance, stepSound, false);
		setCreativeTab(creativeTab);
		GameRegistry.registerBlock(this, itemBlock, imagePath);
	}

}
