package tm.fissionwarfare.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.api.IConcreteBlock;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.item.ItemBlockConcrete;
import tm.fissionwarfare.item.ItemBlockMeta;
import tm.fissionwarfare.proxy.ClientProxy;

public class BlockConcrete extends BlockMetaBase implements IConcreteBlock {
	
	private int[] startingMetas = {4, 9, 14};
	
	@SideOnly(Side.CLIENT)
	private IIcon[] textures = new IIcon[getMaxMeta() + 1];
	
	public BlockConcrete() {
		super("concrete", Material.rock, 0, 5F, Float.MAX_VALUE, Block.soundTypeStone, InitTabs.tabMain, ItemBlockConcrete.class);
	}	
	
	@Override
	public int getMaxMeta() {
		return 14;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, startingMetas[0]));
		list.add(new ItemStack(item, 1, startingMetas[1]));
		list.add(new ItemStack(item, 1, startingMetas[2]));
	}
	
	public int getRepairedMeta(int meta) {
		int i;
		if (meta > 9) i = startingMetas[2];
		else if (meta > 4) i = startingMetas[1];
		else i = startingMetas[0];
		return i;
	}
			
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(this, 1, getRepairedMeta(world.getBlockMetadata(x, y, z)));		
	}
	
	@Override
	public int damageDropped(int meta) {
		
		int i;
		if (meta > 13) i = startingMetas[2];
		else if (meta > 8) i = startingMetas[1];
		else if (meta > 3) i = startingMetas[0];
		else i = 0;
		
		return i;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		
		if (metadata < 4) {
			return new ArrayList<ItemStack>();
		}
		
		return super.getDrops(world, x, y, z, metadata, fortune);
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z,	Random rand) {
		
		int meta = world.getBlockMetadata(x, y, z);
		
		if (!world.getBlock(x, y - 1, z).isBlockNormalCube()) {
			
			if (meta < 3) {
			
				for (int i = 0; i < (3 - meta); i++) {
					world.spawnParticle("blockdust_" + Block.getIdFromBlock(this) + "_" + world.getBlockMetadata(x, y, z), x + rand.nextDouble(), y, z + rand.nextDouble(), 0, 0, 0);
				}		
			}
		}	
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta >= textures.length) return textures[textures.length - 1]; 
		return textures[meta];
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {
		
		for (int i = 0; i < textures.length; i++) {
			textures[i] = iconReg.registerIcon(Reference.MOD_ID + ":concrete/concrete_" + i);
		}
	}
}
