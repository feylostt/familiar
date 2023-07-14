package io.github.feylostt.familiar.spells;

import io.github.feylostt.familiar.Familiar;
import io.github.feylostt.familiar.items.FamiliarItems;
import io.github.feylostt.familiar.mixin.BrewingRecipeRegistryMixin;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FamiliarSpells {

	public static final Registry<Spell> SPELL = createRegistry("spell", Spell.class);

	public static Spell REGENERATION;
	public static Spell GUARDED;
	public static Spell RESISTANCE;
	public static Spell HASTE;

	public static Spell DASH;

	public static Potion AMETHYST_BASE;
	public static Potion QUARTZ_BASE;
	public static Potion ROSE_QUARTZ_BASE;
	public static Potion BLACK_TOURMALINE_BASE;
	public static Potion CITRINE_BASE;
	public static Potion SELENITE_BASE;
	public static Potion DASH_POTION; // powdered amethyst, sugar, moss clump

	public static void initializeSpells() {
		// Ability Spells
		DASH = registerSpell(new Spell(SpellType.ABILITY, 0xf7d425), "dash");

		// Aura Spells
		REGENERATION = registerSpell(new Spell(SpellType.PASSIVE, 0xffedfd), "regeneration");
		GUARDED = registerSpell(new Spell(SpellType.PASSIVE, 0xf294e8), "guarded");
		RESISTANCE = registerSpell(new Spell(SpellType.PASSIVE, 0x5b535c), "resistance");
		HASTE = registerSpell(new Spell(SpellType.PASSIVE, 0xe3c668), "haste");
	}

	public static void initializePotions() {

		AMETHYST_BASE = registerPotion(new Potion(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0)), "amethyst_base", Potions.WATER, FamiliarItems.POWDERED_AMETHYST);
		QUARTZ_BASE = registerPotion(new Potion(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 100, 0)), "quartz_base", Potions.WATER, FamiliarItems.POWDERED_QUARTZ);
		ROSE_QUARTZ_BASE = registerPotion(new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 0)), "rose_quartz_base", Potions.WATER, FamiliarItems.POWDERED_ROSE_QUARTZ);
		

		DASH_POTION = registerPotion(
			new Potion(new StatusEffectInstance(DASH.getEffectWrapper(), 3600, 0, true, false, false)),
			"dash",
			AMETHYST_BASE,
			FamiliarItems.MOSS_CLUMP
		);
	}

	private static Spell registerSpell(Spell spell, String name) {
		Registry.register(SPELL, new Identifier(Familiar.MODID, name), spell);
		spell.setEffectWrapper(registerStatusEffect(new SpellEffect(StatusEffectType.NEUTRAL, spell.getColor(), name), name));
		return spell;
	}

	private static StatusEffect registerStatusEffect(StatusEffect statusEffect, String name) {
		Registry.register(Registry.STATUS_EFFECT, new Identifier(Familiar.MODID, name), statusEffect);
		return statusEffect;
	}

	private static Potion registerPotion(Potion potion, String name, Potion inputPotion, Item ingredient) {
		Registry.register(Registry.POTION, new Identifier(Familiar.MODID, name), potion);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(inputPotion, ingredient, potion);
		return potion;
	}

	@SuppressWarnings("unchecked")
	private static <T> Registry<T> createRegistry(String name, Class<?> registryClass) {
		Registry<?> registry = FabricRegistryBuilder.createSimple(registryClass, new Identifier(Familiar.MODID, name)).buildAndRegister();
		return (Registry<T>) registry;
	}
}
