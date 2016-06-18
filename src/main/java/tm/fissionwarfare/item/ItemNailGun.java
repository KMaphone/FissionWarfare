package tm.fissionwarfare.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.block.BlockConcrete;
import tm.fissionwarfare.config.FWConfig;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.packet.ClientPacketHandler;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.ChatUtil;
import tm.fissionwarfare.util.ItemLoreUtil;
import tm.fissionwarfare.util.NBTUtil;
import tm.fissionwarfare.util.UnitChatMessage;
import tm.fissionwarfare.util.math.MathUtil;

public class ItemNailGun extends ItemBase {

	public ItemNailGun() {
		super("nail_gun");
		setFull3D();
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {

		list.add(EnumChatFormatting.GOLD + "Multi-Mode : " + EnumChatFormatting.AQUA + (NBTUtil.getNBT(stack).getBoolean("multiMode") ? "On" : "Off"));
		
		if (NBTUtil.getNBT(stack).getBoolean("hasCompressor")) {

			list.add(EnumChatFormatting.GOLD + "Compressor RF : " + EnumChatFormatting.AQUA + NBTUtil.getNBT(stack).getInteger("simEnergy") + " / " + ItemCompressor.MAX_ENERGY_STORED);
			list.add("");
		}

		if (ItemLoreUtil.addShiftLore(list)) {
			list.add("Repairs and upgrades concrete.");
			list.add("Right-click : Uses item.");
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {

		return NBTUtil.getNBT(stack).getBoolean("hasCompressor");
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {

		return 1 - MathUtil.scaleDouble(NBTUtil.getNBT(stack).getInteger("simEnergy"), ItemCompressor.MAX_ENERGY_STORED, 1);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean b) {

		if (entity instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) entity;

			if (getCompressor(player) != null) {

				NBTUtil.getNBT(stack).setBoolean("hasCompressor", true);
				NBTUtil.getNBT(stack).setInteger("simEnergy", getCompressorItem(player).getEnergyStored(getCompressor(player)));
			}

			else NBTUtil.getNBT(stack).setBoolean("hasCompressor", false);
		}
	}

	private ItemStack getCompressor(EntityPlayer player) {

		if (player.inventory.armorInventory[2] != null && player.inventory.armorInventory[2].getItem() instanceof ItemCompressor) {
			return player.inventory.armorInventory[2];
		}

		return null;
	}

	private ItemCompressor getCompressorItem(EntityPlayer player) {

		return (ItemCompressor) getCompressor(player).getItem();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		UnitChatMessage message = new UnitChatMessage("Nail Gun", player);

		if (!world.isRemote) {

			if (player.isSneaking()) {

				if (NBTUtil.getNBT(stack).getBoolean("multiMode")) {

					NBTUtil.getNBT(stack).setBoolean("multiMode", false);
					message.printMessage(EnumChatFormatting.WHITE, "Multi-Mode : Off.");
				}

				else {

					NBTUtil.getNBT(stack).setBoolean("multiMode", true);
					message.printMessage(EnumChatFormatting.WHITE, "Multi-Mode : On.");
				}
			}
		}

		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float float1, float float2, float float3) {

		UnitChatMessage message = new UnitChatMessage("Nail Gun", player);

		if (!world.isRemote) {

			if (FWConfig.enableNailGun) {

				if (!player.isSneaking()) {

					if (NBTUtil.getNBT(stack).getBoolean("hasCompressor")) {

						if (NBTUtil.getNBT(stack).getBoolean("multiMode")) {

							for (int xPos = x - 1; xPos < x + 2; xPos++) {

								for (int yPos = y - 1; yPos < y + 2; yPos++) {

									for (int zPos = z - 1; zPos < z + 2; zPos++) {

										if (!upgradeConcrete(player, world, xPos, yPos, zPos, message)) {
											return false;
										}
									}
								}
							}
						}

						else upgradeConcrete(player, world, x, y, z, message);
					}

					else message.printMessage(EnumChatFormatting.RED, "Missing compressor!");
				}
			}

			else message.printMessage(EnumChatFormatting.RED, "This function was disabled by config!");
		}

		return true;
	}

	public boolean upgradeConcrete(EntityPlayer player, World world, int x, int y, int z, UnitChatMessage message) {

		int meta = world.getBlockMetadata(x, y, z);

		if (world.getBlock(x, y, z) == InitBlocks.concrete) {

			if (meta < 14) {

				if (getCompressorItem(player).getEnergyStored(getCompressor(player)) >= 1000) {

					if (player.inventory.hasItemStack(new ItemStack(InitItems.nail_gun_magazine)) || player.capabilities.isCreativeMode) {

						if (!player.capabilities.isCreativeMode) {

							player.inventory.consumeInventoryItem(InitItems.nail_gun_magazine);
							FissionWarfare.network.sendTo(new ClientPacketHandler("consumeitem%" + Item.getIdFromItem(InitItems.nail_gun_magazine)), (EntityPlayerMP) player);
						}

						getCompressorItem(player).extractEnergy(getCompressor(player), 1000, false);
						FissionWarfare.network.sendTo(new ClientPacketHandler("set.energy%" + 38 + "%" + NBTUtil.getNBT(getCompressor(player)).getInteger("energy")), (EntityPlayerMP) player);

						player.inventory.markDirty();

						world.setBlockMetadataWithNotify(x, y, z, meta + ((meta == BlockConcrete.metaTiers[0] || meta == BlockConcrete.metaTiers[1]) ? 5 : 1), 2);
						FissionWarfare.network.sendTo(new ClientPacketHandler("playsound%random.anvil_land%" + x + "%" + y + "%" + z + "%" + 0.1F), (EntityPlayerMP) player);

						return true;
					}
				}

				else message.printMessage(EnumChatFormatting.RED, "Not enough energy! (1000 RF required)");

				return false;
			}
		}

		return true;
	}
}
