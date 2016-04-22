package tm.fissionwarfare.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;

public class BlockConcrete extends BlockBase implements IConcreteBlock {
	
	public BlockConcrete() {
		super("concrete", Material.rock, 0, 5F, Float.MAX_VALUE, Block.soundTypeStone);
	}

	public void breakConcrete(World world, int x, int y, int z) {
		
		int metadata = world.getBlockMetadata(x, y, z) + 1;
		
		if (metadata >= getMaxDamage()) {
			
			world.setBlockToAir(x, y, z);
			world.playSoundEffect(x, y, z, Reference.MOD_ID + ":concrete_break", 0.05F, 1F);
		}
			
		world.setBlockMetadataWithNotify(x, y, z, metadata, 3);
	}
	
	@Override
	public int getBlockStrength() {
		return 5;
	}
	
	@Override
	public int getMaxDamage() {
		return 3;
	}
	
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 0xFFFFFF;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int i) {
		return getBlockColor();
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
		return getBlockColor() - (blockAccess.getBlockMetadata(x, y, z) * 0x1F1F1F);
	}
}
