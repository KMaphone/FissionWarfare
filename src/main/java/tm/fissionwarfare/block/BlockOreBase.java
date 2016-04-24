package tm.fissionwarfare.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOreBase extends BlockBase {
	
	Random rand = new Random();
	
	private ItemStack droppedItem;
	
	private int dropAmountMin, dropAmountMax;
	private int expAmountMin, expAmountMax;
	
	public BlockOreBase(String imagePath) {
		super(imagePath + "_ore", Material.rock, 1, 3, 3, Block.soundTypeStone);
		droppedItem = new ItemStack(this);
		dropAmountMin = 1;
		dropAmountMax = 1;
	}
	
	public BlockOreBase setDroppedItem(ItemStack item, int dropAmountMin, int dropAmountMax, int expAmountMin, int expAmountMax) {
		droppedItem = item;
		this.dropAmountMin = dropAmountMin;
		this.dropAmountMax = dropAmountMax;
		this.expAmountMin = dropAmountMin;
		this.expAmountMax = expAmountMax;
		GameRegistry.addSmelting(this, droppedItem, dropAmountMin);
		return this;
	}

	public boolean differentDrop() {
		return !ItemStack.areItemStacksEqual(droppedItem, new ItemStack(this));
	}
	
	public int quantityDroppedWithBonus(int fortune, Random random) {
		
		if (fortune > 0 && differentDrop()) {		
			
			int j = random.nextInt(fortune + 2) - 1;

			if (j < 0) j = 0;
			return quantityDropped(random) * (j + 1);
		} 
		
		else return quantityDropped(random);
	}

	@Override
	public int quantityDropped(Random rand) {
		return MathHelper.getRandomIntegerInRange(rand, dropAmountMin, dropAmountMax);
	}
	
	@Override
	public int getExpDrop(IBlockAccess blockAccess, int metadata, int fortune) {
		return MathHelper.getRandomIntegerInRange(rand, expAmountMin, expAmountMax);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {		
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(droppedItem.getItem(), quantityDroppedWithBonus(fortune, rand), droppedItem.getItemDamage()));
		return list;
	}
}
