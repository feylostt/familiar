package io.github.feylostt.familiar.blocks;

import io.github.feylostt.familiar.registry.TagsRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KettleBlock extends Block {

	public KettleBlock(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if(world.isClient) {
			return ActionResult.SUCCESS;
		} else {
			player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
			player.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
			return ActionResult.CONSUME;
		}
	}

	private boolean isHeated(World world, BlockPos pos) {
		return world.getBlockState(pos.down()).isIn(TagsRegistry.HEAT_SOURCES);
	}
}
