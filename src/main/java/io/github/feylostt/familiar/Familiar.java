package io.github.feylostt.familiar;

import io.github.feylostt.familiar.blocks.FamiliarBlocks;
import io.github.feylostt.familiar.items.FamiliarItems;
import io.github.feylostt.familiar.spells.FamiliarSpells;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Familiar implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("Familiar");

	public static final String MODID = "familiar";

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		// Initializations
		FamiliarItems.init();
		FamiliarBlocks.init();

		FamiliarSpells.init();
	}
}
