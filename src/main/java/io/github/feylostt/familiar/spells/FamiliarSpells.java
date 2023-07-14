package io.github.feylostt.familiar.spells;

import io.github.feylostt.familiar.Familiar;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FamiliarSpells {

	public static final Registry<Spell> SPELL = createRegistry("spell", Spell.class);

	public static Spell DASH; // powdered amethyst, sugar, moss clump

	public static Spell REGENERATION;
	public static Spell GUARDED;
	public static Spell RESISTANCE;
	public static Spell HASTE;

	public static void init() {
		// Initialize Spell Recipes
		initRecipes();

		// Ability Spells
		DASH = registerSpell(new Spell(SpellType.ABILITY, 0xf7d425), "dash");

		// Aura Spells
		REGENERATION = registerSpell(new Spell(SpellType.PASSIVE, 0xffedfd), "regeneration");
		GUARDED = registerSpell(new Spell(SpellType.PASSIVE, 0xf294e8), "guarded");
		RESISTANCE = registerSpell(new Spell(SpellType.PASSIVE, 0x5b535c), "resistance");
		HASTE = registerSpell(new Spell(SpellType.PASSIVE, 0xe3c668), "haste");
	}

	private static Spell registerSpell(Spell spell, String name) {
		Registry.register(SPELL, new Identifier(Familiar.MODID, name), spell);
		return spell;
	}

	private static void initRecipes() {
		Registry.register(Registry.RECIPE_SERIALIZER, SpellRecipeSerializer.ID, SpellRecipeSerializer.INSTANCE);
		Registry.register(Registry.RECIPE_TYPE, new Identifier(Familiar.MODID, SpellRecipe.Type.ID), SpellRecipe.Type.INSTANCE);
	}

	@SuppressWarnings("unchecked")
	private static <T> Registry<T> createRegistry(String name, Class<?> registryClass) {
		Registry<?> registry = FabricRegistryBuilder.createSimple(registryClass, new Identifier(Familiar.MODID, name)).buildAndRegister();
		return (Registry<T>) registry;
	}
}
