package com.kosmx.somecoolextraitems.items;

import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class CocoaBucketItem extends Item {
   public CocoaBucketItem(Item.Settings settings) {
      super(settings);
   }

   public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
      super.finishUsing(stack, world, user);
      if (user instanceof ServerPlayerEntity) {
         ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
         Criterions.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
         serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
      }
      if(!world.isClient){
         user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20, 2));
      }
      if (stack.isEmpty()) {
         return new ItemStack(Items.BUCKET);
      } else {
         if (user instanceof PlayerEntity && !((PlayerEntity)user).abilities.creativeMode) {
            ItemStack itemStack = new ItemStack(Items.BUCKET);
            PlayerEntity playerEntity = (PlayerEntity)user;
            if (!playerEntity.inventory.insertStack(itemStack)) {
               playerEntity.dropItem(itemStack, false);
            }
         }

         return stack;
      }
   }

   public int getMaxUseTime(ItemStack stack) {
      return 40;
   }

   public UseAction getUseAction(ItemStack stack) {
      return UseAction.DRINK;
   }

   public SoundEvent getDrinkSound() {
      return SoundEvents.ENTITY_GENERIC_DRINK;
   }

   public SoundEvent getEatSound() {
      return SoundEvents.ENTITY_GENERIC_DRINK;
   }

   public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
      user.setCurrentHand(hand);
      return TypedActionResult.success(user.getStackInHand(hand));
   }
}
