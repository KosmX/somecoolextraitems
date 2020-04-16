package com.kosmx.somecoolextraitems.entity;

import java.util.Random;
import java.util.RandomAccess;

import javax.annotation.Nullable;

import com.kosmx.somecoolextraitems.items.AddItems;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

public class WitchEntity extends AbstractMobLordEntity {

    protected WitchEntity(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);
        //net.minecraft.block.SpawnerBlock
    }


    public void tickMovement(){
        super.tickMovement();
        if (this.world.isClient){
            this.world.addParticle(new DustParticleEffect(0.5f, 0.6f, 1f, 1.3f), false, this.getParticleX(0.3d) , this.getY() + this.getRandom().nextFloat()/2, this.getParticleZ(0.3d), (this.getRandom().nextFloat()-0.5f)/8, -0.1f, (this.getRandom().nextFloat()-0.5f)/8);
            this.world.addParticle(new DustParticleEffect(0.5f, 0.6f, 1f, 1.3f), false, this.getParticleX(0.5d) , this.getY() + this.getRandom().nextFloat()/3, this.getParticleZ(0.5d), (this.getRandom().nextFloat()-0.5f)/8, -0.1f, (this.getRandom().nextFloat()-0.5f)/8);            this.world.addParticle(new DustParticleEffect(0.4f, 0.5f, 1f, 1.6f), false, this.getParticleX(0.3d) , this.getY() + this.getRandom().nextFloat()/3, this.getParticleZ(0.3d), (this.getRandom().nextFloat()-0.5f)/8, -0.1f, (this.getRandom().nextFloat()-0.5f)/8);
            this.world.addParticle(new DustParticleEffect(0.5f, 0.6f, 1f, 1.3f), false, this.getParticleX(0.7d) , this.getY() + this.getRandom().nextFloat()/6, this.getParticleZ(0.7d), (this.getRandom().nextFloat()-0.5f)/8, -0.1f, (this.getRandom().nextFloat()-0.5f)/8);
        }
    }



    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(5d);
        this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(20d);
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.5d);
    }

    @Nullable
   public EntityData initialize(IWorld world, LocalDifficulty difficulty, SpawnType spawnType, @Nullable EntityData entityData, @Nullable CompoundTag entityTag){
        EntityData data = super.initialize(world, difficulty, spawnType, entityData, entityTag);
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(AddItems.SkeletonRod));
        this.handDropChances[EquipmentSlot.MAINHAND.getArmorStandSlotId()] = 0.2f;
        return data;
   }

    @Override
    public boolean trySpawnMinion() {
        double x = this.getRandom().nextInt(9) + (int)this.getX() - 5.5;
        double y = this.getRandom().nextInt(9) + (int)this.getY() - 5.5;
        double z = this.getRandom().nextInt(9) + (int)this.getZ() - 5.5;
        boolean bl = SkeletonEntity.canSpawnIgnoreLightLevel(EntityType.SKELETON, this.world, SpawnType.SPAWNER, new BlockPos((double)x, (double)y, (double)z), new Random()) && this.world.isAir(new BlockPos(x, y, z)) && !this.world.isAir(new BlockPos(x, y-1, z));
        //System.out.print("Spawn ");
        if (bl){
            //System.out.println("success");
            //for (int i = 0; i< 128; i++){
            //    this.world.addParticle(new DustParticleEffect(0.6f, 0.6f, 0.6f, 1f), true, x-0.5f + this.getRandom().nextFloat() , y - 0.5f + this.getRandom().nextFloat(), z - 0.5 + this.getRandom().nextFloat(), this.getRandom().nextFloat(), this.getRandom().nextFloat(), this.getRandom().nextFloat());
            //}
            SkeletonEntity skeleton = (SkeletonEntity)EntityType.SKELETON.create(this.world);
            skeleton.resetPosition(x, y, z);
            skeleton.updatePosition(x, y, z);
            skeleton.initialize(this.world, this.world.getLocalDifficulty(new BlockPos(x, y, z)), SpawnType.SPAWNER, (EntityData)null, (CompoundTag)null);
            if(this.getRandom().nextBoolean() && (this.world.getDifficulty() != Difficulty.HARD || (this.getTarget() != null && this.getTarget().squaredDistanceTo(this) > 10))){
                skeleton.equipStack(EquipmentSlot.MAINHAND, new ItemStack( Items.BOW));
            }
            else{
                skeleton.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            }
            
            //skeleton.updateAttackType();
            //skeleton.setCanPickUpLoot(this.random.nextFloat() < 0.55F * this.world.getLocalDifficulty(this.getBlockPos()).getClampedLocalDifficulty());
            skeleton.setTarget(this.getTarget());
            this.world.spawnEntity(skeleton);
            this.world.playLevelEvent(2004, new BlockPos(skeleton), 0);
            skeleton.playSpawnEffects();
        }
        //else{System.out.println("failed");}
        return bl;
    }

    @Override
    public int amount() {
        if(this.world.getDifficulty() == Difficulty.HARD){
            return 4;
        }
        else{
            return 3;
        }
    }

    @Override
    public int maxTrials() {
        return 256;
    }
    public static boolean canSpawn(EntityType<NetherZombieEntity> type, IWorld world, SpawnType spawnType, BlockPos pos,RandomAccess random) {
        return world.getDifficulty() != Difficulty.PEACEFUL && !world.isAir(pos.add(0, -1, 0)) && world.getLightLevel(pos) < 7;
    }



    public boolean canSpawn(IWorld iWorld_1, SpawnType spawnType_1) {BlockPos entityPos = new BlockPos(this.getX(), this.getY(), this.getZ());
        return !iWorld_1.isAir(entityPos.add(0, -1, 0)) && world.getLightLevel(entityPos) < 7;
    }

}