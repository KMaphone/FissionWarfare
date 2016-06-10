package tm.fissionwarfare.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.util.ItemLoreUtil;
import tm.fissionwarfare.util.NBTUtil;

public class ItemCompressor extends ItemArmor implements IEnergyContainerItem {
	
	private final int MAX_ENERGY_TRANSFER = 1000;
	
	public ItemCompressor() {
		super(ArmorMaterial.CLOTH, Reference.armorIDCompressor, 1);
		String imageName = "compressor";
		setUnlocalizedName(imageName);
		setTextureName(Reference.MOD_ID + ":" + imageName);
		setCreativeTab(InitTabs.tabMain);
		setMaxDamage(0);
		GameRegistry.registerItem(this, imageName);		
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean par4) {
		
		list.add(EnumChatFormatting.GOLD + "RF : " + EnumChatFormatting.AQUA + NBTUtil.getNBT(is).getInteger("energy") + " / " + getMaxEnergyStored(is));		
		list.add("");
		
		if (ItemLoreUtil.addShiftLore(list)) {
						
			list.add("This item is placed in your chestplate slot");
			list.add("Used for the Nail Gun to operate.");
		}		
    }
		
	@Override
	public double getDurabilityForDisplay(ItemStack is) {
		int stored = this.getMaxEnergyStored(is) - this.getEnergyStored(is) + 1;
	  	int max = this.getMaxEnergyStored(is) + 1;
	  	return stored / max;
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}
	
	@Override
    public boolean showDurabilityBar(ItemStack is) {		
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
	
	@Override
	public int receiveEnergy(ItemStack is, int maxReceive, boolean sim) {
		
		int energy = this.getEnergyStored(is);
        int energyReceived = Math.min(this.getMaxEnergyStored(is) - energy, Math.min(maxReceive, MAX_ENERGY_TRANSFER));
        
        if (!sim) {
            energy += energyReceived;
            NBTUtil.getNBT(is).setInteger("energy", energy);
        }
        
        return energyReceived;
	}
	
	@Override
	public int extractEnergy(ItemStack is, int maxExtract, boolean sim) {
		
		int energy = this.getEnergyStored(is);
	    int energyExtracted = Math.min(energy, Math.min(maxExtract, MAX_ENERGY_TRANSFER));
	    
	    if (!sim) {
	    	energy -= energyExtracted;
	    	NBTUtil.getNBT(is).setInteger("energy", energy);
	    }
	    
	    return energyExtracted;
	}
	
	@Override
	public int getEnergyStored(ItemStack is) {
		return NBTUtil.getNBT(is).getInteger("energy");
	}
	
	@Override
	public int getMaxEnergyStored(ItemStack is) {
		return 10000;
	}
}
