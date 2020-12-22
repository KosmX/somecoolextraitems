package com.kosmx.somecoolextraitems.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class PizzaBlock extends Block {
   public static final IntProperty BITES;
   protected static final VoxelShape[] BITES_TO_SHAPE;

   protected PizzaBlock(Block.Settings settings) {
      super(settings);
      this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(BITES, 0));
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
      return BITES_TO_SHAPE[(Integer)state.get(BITES)];
   }

   public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      if (world.isClient) {
         ItemStack itemStack = player.getStackInHand(hand);
         if (this.tryEat(world, pos, state, player) == ActionResult.SUCCESS) {
            return ActionResult.SUCCESS;
         }

         if (itemStack.isEmpty()) {
            return ActionResult.CONSUME;
         }
      }

      return this.tryEat(world, pos, state, player);
   }

   private ActionResult tryEat(World world, BlockPos pos, BlockState state, PlayerEntity player) {
      if (!player.canConsume(false)) {
         return ActionResult.PASS;
      } else {
         player.incrementStat(Stats.EAT_CAKE_SLICE);
         player.getHungerManager().add(4, 1F);
         int i = (Integer)state.get(BITES);
         if (i < 7) {
            world.setBlockState(pos, (BlockState)state.with(BITES, i + 1), 3);
         } else {
            world.removeBlock(pos, false);
         }

         return ActionResult.SUCCESS;
      }
   }



   public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, World world, BlockPos pos, BlockPos neighborPos) {
      return facing == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
   }

   public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
      return world.getBlockState(pos.down()).getMaterial().isSolid();
   }

   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(BITES);
   }

   public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
      return (8 - (Integer)state.get(BITES));
   }

   public boolean hasComparatorOutput(BlockState state) {
      return true;
   }


   static {
      BITES = IntProperty.of("pizza_bites", 0, 7);
      BITES_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 2.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 2.0D, 16.0D), Block.createCuboidShape(.0D, 0.0D, 8.0D, 8.0D, 2.0D, 16.0D),Block.createCuboidShape(0.0D, 0.0D, 8.0D, 8.0D, 2.0D, 16.0D)};
   }
}
