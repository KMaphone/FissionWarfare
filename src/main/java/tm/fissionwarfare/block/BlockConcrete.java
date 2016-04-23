package tm.fissionwarfare.block;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.item.ItemBlockMeta;

public class BlockConcrete extends BlockBase implements IConcreteBlock {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] textures = new IIcon[3];
	
	public BlockConcrete() {
		super("concrete", Material.rock, 0, 5F, Float.MAX_VALUE, Block.soundTypeStone, false);
		setCreativeTab(InitTabs.tabMain);
		GameRegistry.registerBlock(this, ItemBlockMeta.class, "concrete");
	}

	@Override
	public int getMaxMeta() {
		return 15;
	}
	
	public int getTextureIndex(int meta) {
		if (meta > 10) return 2;
		if (meta > 5) return 1;
		else return 0;
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return textures[getTextureIndex(meta)];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 5));
		list.add(new ItemStack(item, 1, 10));
		list.add(new ItemStack(item, 1, 15));
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {

		for (int i = 0; i < textures.length; i++) {
			textures[i] = iconReg.registerIcon(Reference.MOD_ID + ":concrete_" + (i + 1));
		}
	}
}
