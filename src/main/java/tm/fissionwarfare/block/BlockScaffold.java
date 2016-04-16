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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.item.ItemBlockMeta;
import tm.fissionwarfare.tileentity.TileEntityScaffold;

public class BlockScaffold extends BlockContainerBase {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;
	
	private static final String[] NAMES = { "iron", "steel", "invar" };
	
	public BlockScaffold() {
		super("scaffold", 2, Material.iron, 2, 2, Block.soundTypeMetal, false);
		setCreativeTab(InitTabs.tabMain);
		GameRegistry.registerBlock(this, ItemBlockMeta.class, "scaffold");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
		list.add(new ItemStack(item, 1, 2));
	}
	
	@Override
	public TileEntity getTileEntity(int meta) {
		return new TileEntityScaffold(meta);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return textures[meta];
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {

		textures = new IIcon[NAMES.length];

		for (int i = 0; i < textures.length; ++i) {
			textures[i] = iconReg.registerIcon(Reference.MOD_ID + ":" + NAMES[i] + "_scaffold");
		}
	}
}
