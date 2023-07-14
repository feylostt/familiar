package io.github.feylostt.familiar.blocks;

import io.github.feylostt.familiar.Familiar;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class FamiliarBlocks {

	// Crafting Benches
	public static Block KETTLE;

	public static void init() {
		KETTLE = registerBlock(new Block(QuiltBlockSettings.of(Material.METAL, MapColor.BLACK)), "kettle");
	}

	private static Block registerBlock(Block block, String name) {
		Registry.register(Registry.BLOCK, new Identifier(Familiar.MODID, name), block);
		Registry.register(Registry.ITEM, new Identifier(Familiar.MODID, name), new BlockItem(block, new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		return block;
	}
}
