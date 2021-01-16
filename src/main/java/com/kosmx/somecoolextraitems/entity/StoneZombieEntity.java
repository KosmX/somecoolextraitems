package com.kosmx.somecoolextraitems.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import java.util.Random;
import java.util.RandomAccess;


//import com.kosmx.somecoolextraitems.Main;

//import org.apache.logging.log4j.Level;


public class StoneZombieEntity extends ZombieEntity {
    //protected static final EntityAttribute SPAWN_REINFORCEMENTS = (new ClampedEntityAttribute("lava_zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D));

    public StoneZombieEntity(EntityType<? extends ZombieEntity> type, World world) {
        super(type, world);
        this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
    }

    public boolean isOnFire() {
        return false;
    }

    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source.getAttacker();
            if (player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof PickaxeItem) {
                //((PickaxeItem)player.getStackInHand(Hand.MAIN_HAND).getItem()).getMiningSpeed(player.getStackInHand(Hand.MAIN_HAND), new BlockState());
                //Main.log(Level.INFO, "uses pickaxe");
                int efficiency = EnchantmentHelper.getLevel(Enchantments.EFFICIENCY, player.getStackInHand(Hand.MAIN_HAND));
                amount = (float) (amount * 3f + (efficiency * (1 + efficiency / 5f) * (amount / 2f + 1)));

            }
        }
        return super.damage(source, amount);
    }

    protected boolean burnsInDaylight() {
        return false;
    }
    // net.minecraft.entity.mob.MagmaCubeEntity
    // net.minecraft.entity.mob.ZombiePigmanEntity
    // net.minecraft.entity.mob.EndermanEntity
    //net.minecraft.entity.passive.SheepEntity


    protected void initGoals() {
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.initCustomGoals();
    }

    //public void onDeath(DamageSource source){
    //  if
    //}

    protected void initCustomGoals() {
        this.goalSelector.add(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
        this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new FollowTargetGoal<>(this, IronGolemEntity.class, true));
    }

    /*@Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(35.0D);
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(2.0D);
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(35.0D);
        //this.getAttributes().register(SPAWN_REINFORCEMENTS).setBaseValue(this.random.nextDouble() * 0.1D);
    }

     */
    public static DefaultAttributeContainer.Builder createStoneZombieAttributes(){
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4d)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 35)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23000000417232513D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35)
                .add(EntityAttributes.GENERIC_ARMOR, 2);
    }

    protected void initEquipment(LocalDifficulty difficulty) {
    }

    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }


    public boolean canSpawn(WorldAccess iWorld_1, SpawnReason spawnType_1) {
        BlockPos entityPos = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        return !iWorld_1.isAir(entityPos) && this.getPathfindingFavor(entityPos) >= 0.0F && entityPos.getY() < 50;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLOCK_STONE_PLACE;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_STONE_BREAK;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_STONE_BREAK;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.BLOCK_STONE_STEP;
    }

    public static boolean canSpawn(EntityType<StoneZombieEntity> stoneZombieEntityEntityType, ServerWorldAccess iWorld, SpawnReason spawnType, BlockPos blockPos, Random random) {
        return canSpawnInDark(stoneZombieEntityEntityType, iWorld, spawnType, blockPos, random) && blockPos.getY() < 50;
    }
}
