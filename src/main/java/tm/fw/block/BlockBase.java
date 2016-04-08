package tm.fw.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tm.fw.Reference;
import tm.fw.init.InitTabs;

public class BlockBase extends Block {

	public BlockBase(String imagePath, String name, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {		
		super(material);
		setBlockName(name);
		setBlockTextureName(Reference.MOD_ID + ":" + imagePath);
		setHarvestLevel("pickaxe", harvestLevel);
		setStepSound(stepSound);
		setCreativeTab(InitTabs.tabMain);
		setHardness(hardness);
		setResistance(resistance);
		if (isRegistered) GameRegistry.registerBlock(this, name);
	}
	
	public BlockBase(String imagePath, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound) {
		this(imagePath, imagePath, material, harvestLevel, hardness, resistance, stepSound, true);
	}
}
