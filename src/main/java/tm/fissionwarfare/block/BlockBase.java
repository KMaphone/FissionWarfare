package tm.fissionwarfare.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.item.ItemBlockMeta;

public class BlockBase extends Block {

	public String imageName;
	
	public BlockBase(String imageName, String name, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {		
		super(material);
		this.imageName = imageName;
		setBlockName(name);
		setBlockTextureName(Reference.MOD_ID + ":" + imageName);
		setHarvestLevel("pickaxe", harvestLevel);
		setStepSound(stepSound);
		setHardness(hardness);
		setResistance(resistance);
		
		if (isRegistered) {
			setCreativeTab(InitTabs.tabMain);
			GameRegistry.registerBlock(this, name);
		}
	}
	
	public BlockBase(String imageName, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {
		this(imageName, imageName, material, harvestLevel, hardness, resistance, stepSound, isRegistered);
	}
	
	public BlockBase(String imageName, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound) {
		this(imageName, imageName, material, harvestLevel, hardness, resistance, stepSound, true);
	}
}
