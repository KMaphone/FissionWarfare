package tm.fw.item;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tm.fw.Reference;
import tm.fw.init.InitTabs;

public class ItemBase extends Item {
	
	public ItemBase(String imagePath) {
		setUnlocalizedName(imagePath);
		setTextureName(Reference.MOD_ID + ":" + imagePath);
		setCreativeTab(InitTabs.tabMain);
		GameRegistry.registerItem(this, imagePath);
	}
}