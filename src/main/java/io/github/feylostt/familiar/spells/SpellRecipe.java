package io.github.feylostt.familiar.spells;

import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SpellRecipe implements Recipe<CraftingInventory> {

	private final Ingredient inputComponentA;
	private final Ingredient inputComponentB;
	private final ItemStack result;
	private final Identifier id;

	public SpellRecipe(Identifier id, ItemStack result, Ingredient inputComponentA, Ingredient inputComponentB) {
		this.id = id;
		this.inputComponentA = inputComponentA;
		this.inputComponentB = inputComponentB;
		this.result = result;
	}

	public Ingredient getInputComponentA() {
		return this.inputComponentA;
	}

	public Ingredient getInputComponentB() {
		return this.inputComponentB;
	}

	@Override
	public ItemStack getOutput() {
		return this.result;
	}

	@Override
	public Identifier getId() {
		return this.id;
	}


	@Override
	public boolean matches(CraftingInventory inventory, World world) {
		if(inventory.size() < 2) {
			return false;
		}

		return inputComponentA.test(inventory.getStack(0)) && inputComponentB.test(inventory.getStack(1));
	}

	@Override
	public ItemStack craft(CraftingInventory inventory) {
		return this.getOutput().copy();
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return SpellRecipeSerializer.INSTANCE;
	}

	public static class Type implements RecipeType<SpellRecipe> {
		private Type() {}

		public static final Type INSTANCE = new Type();
		public static final String ID = "spell_recipe";
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}
}

class SpellRecipeJsonFormat {
	JsonObject inputComponentA;
	JsonObject inputComponentB;
	String result;
	String spellEffect;
	int duration;
}
