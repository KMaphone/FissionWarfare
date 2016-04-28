package tm.fissionwarfare.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.api.IReinforcedBlock;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.item.ItemBlockMeta;

public abstract class BlockReinforced extends BlockMetaBase implements IReinforcedBlock {

	@SideOnly(Side.CLIENT)
	private IIcon[] textures = new IIcon[getMaxMeta() + 1];
	
	public BlockReinforced(String imageName, Class<? extends ItemBlockMeta> itemBlock) {
		super(imageName, Material.rock, 2, 0, Float.MAX_VALUE, Block.soundTypeStone, InitTabs.tabMain, itemBlock);
	}
	
	public abstract int[] getRegisteredMetas();
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		
		for (int i = 0; i < getRegisteredMetas().length; i++) {
			list.add(new ItemStack(item, 1, getRegisteredMetas()[i]));
		}
	}
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return 3 + (10F * world.getBlockMetadata(x, y, z));
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta >= textures.length) return textures[textures.length - 1]; 
		return textures[meta];
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {
		
		for (int i = 0; i < textures.length; i++) {
			textures[i] = iconReg.registerIcon(Reference.MOD_ID + ":reinforced/" + imageName + "_" + i);
		}
	}
}
