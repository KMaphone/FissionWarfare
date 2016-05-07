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
import tm.fissionwarfare.api.IReinforcedBlock;
import tm.fissionwarfare.config.FWConfig;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.item.ItemBlockReinforced;
import tm.fissionwarfare.item.ItemBlockMeta;
import tm.fissionwarfare.proxy.ClientProxy;

public class BlockConcrete extends BlockReinforced {
	
	public BlockConcrete() {
		super("concrete");
	}	
			
	@Override
	public int getMaxMeta() {
		return 14;
	}
	
	@Override
	public int[] getRegisteredMetas() {
		return new int[]{4, 9, 14};
	}
	
	public int getRepairedMeta(int meta) {
		int i;
		if (meta > 9) i = getRegisteredMetas()[2];
		else if (meta > 4) i = getRegisteredMetas()[1];
		else i = getRegisteredMetas()[0];
		return i;
	}
			
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(this, 1, getRepairedMeta(world.getBlockMetadata(x, y, z)));		
	}
	
	@Override
	public int damageDropped(int meta) {
		int i;
		if (meta > 13) i = getRegisteredMetas()[2];
		else if (meta > 8) i = getRegisteredMetas()[1];
		else if (meta > 3) i = getRegisteredMetas()[0];
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
		
		if (FWConfig.enableConcretePaticles) {
			
			int meta = world.getBlockMetadata(x, y, z);
			
			if (!world.getBlock(x, y - 1, z).isBlockNormalCube()) {
			
				if (meta < 3) {
				
					for (int i = 0; i < (3 - meta); i++) {
						world.spawnParticle("blockdust_" + Block.getIdFromBlock(this) + "_" + world.getBlockMetadata(x, y, z), x + rand.nextDouble(), y, z + rand.nextDouble(), 0, 0, 0);
					}		
				}
			}	
		}	
	}
}
