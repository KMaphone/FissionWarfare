package tm.fissionwarfare.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BlockOreBase extends BlockBase {
	
	Random rand = new Random();
	
	private Item droppedItem;
	
	private int dropAmountMin, dropAmountMax;
	private int expAmountMin, expAmountMax;
	
	public BlockOreBase(String imagePath) {
		super(imagePath + "_ore", Material.rock, 1, 3, 3, Block.soundTypeStone);
		droppedItem = Item.getItemFromBlock(this);
	}
	
	public BlockOreBase setDroppedItem(Item item, int dropAmountMin, int dropAmountMax, int expAmountMin, int expAmountMax) {
		droppedItem = item;
		this.dropAmountMin = dropAmountMin;
		this.dropAmountMax = dropAmountMax;
		this.expAmountMin = dropAmountMin;
		this.expAmountMax = expAmountMax;
		return this;
	}

	public int quantityDroppedWithBonus(int fortune, Random random) {
		
		if (fortune > 0 && droppedItem != Item.getItemFromBlock(this)) {		
			
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
	public int getExpDrop(IBlockAccess blockAccess, int i1, int i2) {
		return MathHelper.getRandomIntegerInRange(rand, expAmountMin, expAmountMax);
	}

	@Override
	public Item getItemDropped(int i1, Random rand, int i2) {
		return droppedItem;
	}
}
