package io.github.feylostt.familiar.items;

import io.github.feylostt.familiar.Familiar;
import io.github.feylostt.familiar.spells.FamiliarSpells;
import io.github.feylostt.familiar.spells.Spell;
import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.EndRodBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class ShardItem extends Item {

	private final String auraSpell;
	private final int range;

	public ShardItem(Settings settings, String auraSpell, int range) {
		super(settings);
		this.auraSpell = auraSpell;
		this.range = range;
	}

	private Spell getAuraSpell() {
		return FamiliarSpells.SPELL.getOrEmpty(new Identifier(Familiar.MODID, this.auraSpell)).orElseThrow(() -> new IllegalArgumentException("No such spell " + this.auraSpell));
	}

	public void applyAuraEffect(LivingEntity entity) {
		if (!entity.world.isClient) {
			Spell spell = this.getAuraSpell();

			World world = entity.getWorld();
			BlockPos pos = entity.getBlockPos();

			Box box = (new Box(pos)).expand(range);

			List<LivingEntity> livingEntities = world.getNonSpectatingEntities(LivingEntity.class, box);
			Iterator<LivingEntity> iterator = livingEntities.iterator();
			LivingEntity livingEntity;
			while(iterator.hasNext()) {
				livingEntity = (LivingEntity) iterator.next();
				// Apply effect to every entity
				spell.applyUpdateEffect(livingEntity, 20);
			}
		}
	}

	private void applyParticles(LivingEntity entity) {
		for(int i = 0; i < 2; ++i) {
			entity.world.addParticle(
				ParticleTypes.END_ROD,
				entity.getParticleX(0.5),
				entity.getRandomBodyY() - 0.25,
				entity.getParticleZ(0.5),
				(entity.getRandom().nextDouble() - 0.5) * 2.0,
				-entity.getRandom().nextDouble(),
				(entity.getRandom().nextDouble() - 0.5) * 2.0
			);
		}
	}
}
