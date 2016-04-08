package cofh.thermalexpansion.api.crafting.recipes;

import net.minecraft.item.ItemStack;

public interface ISmelterRecipe {

	ItemStack getPrimaryInput();

	ItemStack getSecondaryInput();

	ItemStack getPrimaryOutput();

	ItemStack getSecondaryOutput();

	int getEnergy();

	int getSecondaryOutputChance();

}
