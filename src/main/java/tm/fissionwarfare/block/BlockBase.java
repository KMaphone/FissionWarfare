package tm.fissionwarfare.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;

public class BlockBase extends Block {

	public BlockBase(String imagePath, String name, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {		
		super(material);
		setBlockName(name);
		setBlockTextureName(Reference.MOD_ID + ":" + imagePath);
		setHarvestLevel("pickaxe", harvestLevel);
		setStepSound(stepSound);		
		setHardness(hardness);
		setResistance(resistance);
		
		if (isRegistered) {
			setCreativeTab(InitTabs.tabMain);
			GameRegistry.registerBlock(this, name);
		}
	}
	
	public BlockBase(String imagePath, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {
		this(imagePath, imagePath, material, harvestLevel, hardness, resistance, stepSound, isRegistered);
	}
	
	public BlockBase(String imagePath, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound) {
		this(imagePath, imagePath, material, harvestLevel, hardness, resistance, stepSound, true);
	}
}
