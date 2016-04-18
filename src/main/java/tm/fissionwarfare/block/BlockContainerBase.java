package tm.fissionwarfare.block;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import scala.annotation.meta.getter;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;

public abstract class BlockContainerBase extends BlockContainer {

	private final Random random = new Random();

	float pixel = 1F/16F;
	
	public BlockContainerBase(String imagePath, int harvestLevel, Material material, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {
		super(material);		
		setBlockName(imagePath);
		setBlockTextureName(Reference.MOD_ID + ":" + imagePath);
		setHarvestLevel("pickaxe", harvestLevel);
		setStepSound(stepSound);
		setHardness(hardness);
		setResistance(resistance);
		
		if (isRegistered) {
			setCreativeTab(InitTabs.tabMain);
			GameRegistry.registerBlock(this, imagePath);
		}
		
		GameRegistry.registerTileEntity(getTileEntity(0).getClass(), Reference.MOD_ID + ":tile_entity_" + imagePath);
	}
	
	public BlockContainerBase(String imagePath, int harvestLevel, Material material, float hardness, float resistance, Block.SoundType stepSound) {
		this(imagePath, harvestLevel, material, resistance, resistance, stepSound, true);
	}
	
	public BlockContainerBase setBounds(float xStart, float yStart, float zStart, float xEnd, float yEnd, float zEnd) {
		setBlockBounds(xStart * pixel, yStart * pixel, zStart * pixel, xEnd * pixel, yEnd * pixel, zEnd * pixel);
		return this;
	}
	
	public abstract TileEntity getTileEntity(int meta); 
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return getTileEntity(meta);
	}
	
	public void breakBlock(World world, int x, int y, int z, Block block, int i) {

		if (!(world.getTileEntity(x, y, z) instanceof ISidedInventory)) {
			return;
		}
		
		ISidedInventory tileentity = (ISidedInventory) world.getTileEntity(x, y, z);
		
		if (tileentity != null) {
			for (int i1 = 0; i1 < tileentity.getSizeInventory(); ++i1) {
				ItemStack itemstack = tileentity.getStackInSlot(i1);

				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
						int j1 = this.random.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}

		super.breakBlock(world, x, y, z, block, i);
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float f, float f2, float f3) {
		
		if(!w.isRemote) {			
			//FMLNetworkHandler.openGui(p, ProjectWorkspace.instance, 0, w, x, y, z);	
		}
		
		return true;	
	}
}