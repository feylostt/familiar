package io.github.feylostt.familiar.spells;

import io.github.feylostt.familiar.Familiar;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;

public class SpellEffect extends StatusEffect {

	private final String spellName;

	public SpellEffect(StatusEffectType type, int color, String spellName) {
		super(type, color);
		this.spellName = spellName;
	}

	public String getSpellName() {
		return spellName;
	}

	private Spell getSpell() {
		return FamiliarSpells.SPELL.getOrEmpty(new Identifier(Familiar.MODID, spellName)).orElseThrow(() -> new IllegalArgumentException("No such item " + spellName));
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		Spell spell = getSpell();
		spell.applyUpdateEffect(entity);
	}
}
