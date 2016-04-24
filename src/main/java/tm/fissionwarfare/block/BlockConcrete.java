package tm.fissionwarfare.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.item.ItemBlockConcrete;
import tm.fissionwarfare.item.ItemBlockMeta;

public class BlockConcrete extends BlockMetaBase implements IConcreteBlock {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] textures = new IIcon[getMaxMeta() + 1];
	
	public BlockConcrete() {
		super("concrete", Material.rock, 0, 5F, Float.MAX_VALUE, Block.soundTypeStone, InitTabs.tabMain, ItemBlockConcrete.class);
	}
	
	@Override
	public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
		return 5;
	}
	
	@Override
	public int getMaxMeta() {
		return 14;
	}
				
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return textures[meta];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 4));
		list.add(new ItemStack(item, 1, 9));
		list.add(new ItemStack(item, 1, 14));
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z,	Random rand) {
		
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta < 3) {
			
			for (int i = 0; i < (3 - meta); i++) {
				world.spawnParticle("blockdust_" + Block.getIdFromBlock(this) + "_" + world.getBlockMetadata(x, y, z), x + rand.nextDouble(), y, z + rand.nextDouble(), 0, 0, 0);
			}		
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {

		for (int i = 0; i < textures.length; i++) {
			textures[i] = iconReg.registerIcon(Reference.MOD_ID + ":concrete/concrete_" + i);
		}
	}
}
