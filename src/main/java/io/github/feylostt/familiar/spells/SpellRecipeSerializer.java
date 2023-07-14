package io.github.feylostt.familiar.spells;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.feylostt.familiar.Familiar;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpellRecipeSerializer implements RecipeSerializer<SpellRecipe> {

	public static final SpellRecipeSerializer INSTANCE = new SpellRecipeSerializer();
	public static final Identifier ID = new Identifier(Familiar.MODID, "spell_recipe");

	private SpellRecipeSerializer() {}

	@Override
	public SpellRecipe read(Identifier id, JsonObject json) {
		SpellRecipeJsonFormat recipeJson = new Gson().fromJson(json, SpellRecipeJsonFormat.class);

		if (recipeJson.inputComponentA == null || recipeJson.inputComponentB == null || recipeJson.result == null) {
			throw new JsonSyntaxException("A required attribute is missing!");
		}

		Ingredient inputComponentA = Ingredient.fromJson(recipeJson.inputComponentA);
		Ingredient inputComponentB = Ingredient.fromJson(recipeJson.inputComponentB);

		String spellEffect = recipeJson.spellEffect;
		int duration = recipeJson.duration;

		Item result = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.result)).orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.result));
		ItemStack output = new ItemStack(result, 1);

		return new SpellRecipe(id, output, inputComponentA, inputComponentB);
	}

	@Override
	public SpellRecipe read(Identifier id, PacketByteBuf buf) {
		Ingredient inputComponentA = Ingredient.fromPacket(buf);
		Ingredient inputComponentB = Ingredient.fromPacket(buf);
		ItemStack output = buf.readItemStack();

		return new SpellRecipe(id, output, inputComponentA, inputComponentB);
	}

	@Override
	public void write(PacketByteBuf buf, SpellRecipe recipe) {
		recipe.getInputComponentA().write(buf);
		recipe.getInputComponentB().write(buf);
		buf.writeItemStack(recipe.getOutput());
	}
}
