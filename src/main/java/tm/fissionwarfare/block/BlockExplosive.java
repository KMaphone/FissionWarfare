package tm.fissionwarfare.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.api.IExplosiveBlock;
import tm.fissionwarfare.config.FWConfig;
import tm.fissionwarfare.entity.EntityExplosive;
import tm.fissionwarfare.explosion.type.BasicExplosion;

public class BlockExplosive extends BlockBase implements IExplosiveBlock {
	
	private IExplosionType explosion;

	public BlockExplosive(String imagePath, IExplosionType explosion) {
		super(imagePath, Material.tnt, 0, 0, 0, Block.soundTypeStone);
		this.explosion = explosion;
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

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {
		blockIcon = iconReg.registerIcon(getTextureName());
	}
	
	public void activate(World world, int x, int y, int z) {	
		
		if (FWConfig.enableIgnitingPlacedExplosives) {
			world.setBlockToAir(x, y, z);
			world.spawnEntityInWorld(new EntityExplosive(world, x, y, z, this));
		}	 
	}

	@Override
	public IExplosionType getExplosion() {
		return explosion;
	}
}
