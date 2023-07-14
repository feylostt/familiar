package io.github.feylostt.familiar.registry;

import io.github.feylostt.familiar.Familiar;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class TagsRegistry {

	public static final TagKey<Block> HEAT_SOURCES = create("heat_sources", Registry.BLOCK_KEY);

	private static <E> TagKey<E> create(String pathName, RegistryKey<? extends Registry<E>> registry) {
		return TagKey.of(registry, new Identifier(Familiar.MODID, pathName));
	}

	private TagsRegistry() throws InstantiationException {
		throw new InstantiationException("Constant class cannot be instantiate");
	}
}
