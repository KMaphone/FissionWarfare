package tm.fissionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.entity.EntityExplosive;
import tm.fissionwarfare.explosion.type.BasicExplosion;
import tm.fissionwarfare.explosion.type.IExplosionType;
import tm.fissionwarfare.explosion.type.IExplosiveBlock;

public class BlockExplosive extends BlockBase implements IExplosiveBlock {

	private IIcon top_image, bottom_image;

	public BlockExplosive(String imagePath) {
		super(imagePath + "_explosive", Material.tnt, 0, 0, 0, Block.soundTypeStone);
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);

		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
			activate(world, x, y, z);
		}
	}

	public void onNeighborBlockChange(World world, int x, int y, int z,	Block block) {
		
		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {			
			activate(world, x, y, z);
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f1, float f2, float f3) {
		
        if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
        	
            activate(world, x, y, z);           
            player.getCurrentEquippedItem().damageItem(1, player);
            return true;
        }
        
        else return super.onBlockActivated(world, x, y, z, player, meta, f1, f2, f3);
    }
	
	public void activate(World world, int x, int y, int z) {	
		 world.setBlockToAir(x, y, z);
		 world.spawnEntityInWorld(new EntityExplosive(world, x + 0.5, y + 0.5, z + 0.5, this));
	}
		
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 0) return bottom_image;
		if (side == 1) return top_image;
		return blockIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconReg) {
		blockIcon = iconReg.registerIcon(getTextureName() + "_side");
		top_image = iconReg.registerIcon(getTextureName() + "_top");
		bottom_image = iconReg.registerIcon(getTextureName() + "_bottom");
	}

	@Override
	public IExplosionType getExplosion() {
		return new BasicExplosion();
	}
}
