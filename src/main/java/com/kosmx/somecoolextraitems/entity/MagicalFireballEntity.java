package com.kosmx.somecoolextraitems.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class MagicalFireballEntity extends AbstractFireballEntity {
   public MagicalFireballEntity(EntityType<? extends MagicalFireballEntity> entityType, World world) {
      super(entityType, world);
   }

   public MagicalFireballEntity(World world, LivingEntity owner, double velocityX, double velocityY, double velocityZ) {
      super(EntityType.SMALL_FIREBALL, owner, velocityX, velocityY, velocityZ, world);
   }

   public MagicalFireballEntity(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
      super(EntityType.SMALL_FIREBALL, x, y, z, velocityX, velocityY, velocityZ, world);
   }

   protected void onCollision(HitResult hitResult) {
      super.onCollision(hitResult);
      if (!this.world.isClient) {
         if (hitResult.getType() == HitResult.Type.ENTITY && !(((EntityHitResult)hitResult).getEntity() instanceof SkeletonEntity)) {
            Entity entity = ((EntityHitResult)hitResult).getEntity();
            if (!entity.isFireImmune()) {
               int i = entity.getFireTicks();
               entity.setOnFireFor(5);
               boolean bl = entity.damage(DamageSource.explosiveProjectile(this, this.owner), 5.0F);
               if (bl) {
                  this.dealDamage(this.owner, entity);
               } else {
                  entity.setFireTicks(i);
               }
            }
         } else if (this.owner == null || !(this.owner instanceof MobEntity) || this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
            BlockHitResult blockHitResult = (BlockHitResult)hitResult;
            BlockPos blockPos = blockHitResult.getBlockPos().offset(blockHitResult.getSide());
            if (this.world.isAir(blockPos)) {
               this.world.setBlockState(blockPos, Blocks.FIRE.getDefaultState());
            }
         }

         this.remove();
      }

   }

   public boolean collides() {
      return false;
   }

   public boolean damage(DamageSource source, float amount) {
      return false;
   }
}
