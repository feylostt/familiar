package io.github.feylostt.familiar.spells;

import net.minecraft.util.Formatting;

public enum SpellType {
	PASSIVE(Formatting.BLUE),
	HARMFUL(Formatting.RED),
	ABILITY(Formatting.LIGHT_PURPLE);

	private final Formatting formatting;

	SpellType(Formatting format) {
		this.formatting = format;
	}

	public Formatting getFormatting() {
		return this.formatting;
	}
}
