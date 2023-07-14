package io.github.feylostt.familiar.mixin;

import io.github.feylostt.familiar.items.ShardItem;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AllayEntity.class)
public class AllayEntityMixin {

	@Inject(method = "tick", at = @At("HEAD"))
	private void familiar$tick(CallbackInfo ci) {
		AllayEntity thisAllay = ((AllayEntity)(Object)this);
		if(!thisAllay.world.isClient) {
			ItemStack itemStack = thisAllay.getStackInHand(Hand.MAIN_HAND);

			int ticks = 0;
			if(thisAllay.getWorld().getServer() != null) {
				ticks = thisAllay.getWorld().getServer().getTicks();
			}

			if(itemStack.getItem() instanceof ShardItem && ticks % 20 == 0) {
				((ShardItem) itemStack.getItem()).applyAuraEffect(thisAllay);

			}
		}
	}
}
