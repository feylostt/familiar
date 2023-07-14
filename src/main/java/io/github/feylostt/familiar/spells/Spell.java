package io.github.feylostt.familiar.spells;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Monster;

import java.awt.*;

public class Spell {

	public static final String SPELL_KEY = "Spells";
	public static final String EFFECT_KEY = "SpellEffect";
	public static final String DURATION_KEY = "SpellDuration";

	private final SpellType type;
	private final int color;

	public Spell(SpellType type, int color) {
		this.type = type;
		this.color = color;
	}

	public void applyUpdateEffect(LivingEntity entity, int duration) {
		if(this == FamiliarSpells.REGENERATION) {
			if (entity.getHealth() < entity.getMaxHealth() && !(entity instanceof Monster)) {
				entity.heal(1.0F);
			}
		} else if(this == FamiliarSpells.GUARDED) {
			if(entity instanceof Monster) {
				entity.damage(DamageSource.MAGIC, 2.0F);
			}
		} else if(this == FamiliarSpells.HASTE) {
			if(!(entity instanceof Monster)) {
				entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 21, 1, true, false, false));

			}
		} else if(this == FamiliarSpells.RESISTANCE) {
			if(!(entity instanceof Monster)) {
				entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 21, 1, true, false, false));
			}
		}
	}
}
