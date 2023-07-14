package io.github.feylostt.familiar.items;

import io.github.feylostt.familiar.Familiar;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class FamiliarItems {
	// Material Components
	// Crystals
	public static Item POWDERED_AMETHYST; // Soothing & Balance - Movement spells
	public static Item POWDERED_QUARTZ; // Mental Clarity & Focus - Sight spells

	public static Item ROSE_QUARTZ_SHARD; // Minor Protective Aura (conduit-esque)
	public static Item POWDERED_ROSE_QUARTZ; // Love & Compassion - Utility spells (Feather Fall, etc)

	public static Item BLACK_TOURMALINE_SHARD; // Minor Resistance Aura
	public static Item POWDERED_BLACK_TOURMALINE; // Grounding & Protection - Resistance spells (totem?)

	public static Item CITRINE_SHARD; // Minor Haste Aura
	public static Item POWDERED_CITRINE; // Abundance & Happiness - Productivity & Strength spells (haste, etc)

	public static Item SELENITE_SHARD; // Minor Regen Aura
	public static Item POWDERED_SELENITE; // Cleansing & Healing - Healing-type Spells

	public static Item MOSS_CLUMP;

	// Plants

	public static void init() {
		// Material Components
		// Crystals
		POWDERED_AMETHYST = registerItem(new Item(new QuiltItemSettings().group(ItemGroup.MATERIALS)), "powdered_amethyst");
		POWDERED_QUARTZ = registerItem(new Item(new QuiltItemSettings().group(ItemGroup.MATERIALS)), "powdered_quartz");

		ROSE_QUARTZ_SHARD = registerItem(new ShardItem(new QuiltItemSettings().group(ItemGroup.MATERIALS), "guarded", 8), "rose_quartz_shard");
		POWDERED_ROSE_QUARTZ = registerItem(new Item(new QuiltItemSettings().group(ItemGroup.MATERIALS)), "powdered_rose_quartz");

		BLACK_TOURMALINE_SHARD = registerItem(new ShardItem(new QuiltItemSettings().group(ItemGroup.MATERIALS), "resistance", 8), "black_tourmaline_shard");
		POWDERED_BLACK_TOURMALINE = registerItem(new Item(new QuiltItemSettings().group(ItemGroup.MATERIALS)), "powdered_black_tourmaline");

		CITRINE_SHARD = registerItem(new ShardItem(new QuiltItemSettings().group(ItemGroup.MATERIALS), "haste", 8), "citrine_shard");
		POWDERED_CITRINE = registerItem(new Item(new QuiltItemSettings().group(ItemGroup.MATERIALS)), "powdered_citrine");

		SELENITE_SHARD = registerItem(new ShardItem(new QuiltItemSettings().group(ItemGroup.MATERIALS), "regeneration", 8), "selenite_shard");
		POWDERED_SELENITE = registerItem(new Item(new QuiltItemSettings().group(ItemGroup.MATERIALS)), "powdered_selenite");
	}

	public static Item registerItem(Item item, String name) {
		Registry.register(Registry.ITEM, new Identifier(Familiar.MODID, name), item);
		return item;
	}
}
