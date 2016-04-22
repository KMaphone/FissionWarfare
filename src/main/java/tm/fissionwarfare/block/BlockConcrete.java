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

	@Override
	public int getBlockStrength() {
		return 5;
	}
}
