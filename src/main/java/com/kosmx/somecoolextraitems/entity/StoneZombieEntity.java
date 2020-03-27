package com.kosmx.somecoolextraitems.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import java.util.RandomAccess;

//import com.kosmx.somecoolextraitems.Main;

//import org.apache.logging.log4j.Level;



public class StoneZombieEntity extends ZombieEntity {
  protected static final EntityAttribute SPAWN_REINFORCEMENTS = (new ClampedEntityAttribute((EntityAttribute) null,
      "lava_zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setName("Spawn Reinforcements Chance");

  public StoneZombieEntity(EntityType<? extends ZombieEntity> type, World world) {
    super(type, world);
    this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
  }

  public boolean isOnFire() {
    return false;
  }
  public boolean damage(DamageSource source, float amount) {
    if(source.getAttacker() instanceof PlayerEntity){
      PlayerEntity player = (PlayerEntity)source.getAttacker();
      if(player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof PickaxeItem){
        //((PickaxeItem)player.getStackInHand(Hand.MAIN_HAND).getItem()).getMiningSpeed(player.getStackInHand(Hand.MAIN_HAND), new BlockState());
        //Main.log(Level.INFO, "uses pickaxe");
        amount = amount * 25f;
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



  protected void initGoals() {
    this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
    this.goalSelector.add(8, new LookAroundGoal(this));
    this.initCustomGoals();
  }

  protected void initCustomGoals() {
    this.goalSelector.add(2, new ZombieAttackGoal(this, 1.0D, false));
    this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
    this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
    this.targetSelector.add(3, new FollowTargetGoal<>(this, IronGolemEntity.class, true));
  }

  protected void initAttributes() {
    super.initAttributes();
    this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
    this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    this.getAttributeInstance(EntityAttributes.ARMOR).setBaseValue(2.0D);
    this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(25.0D);
    this.getAttributes().register(SPAWN_REINFORCEMENTS).setBaseValue(this.random.nextDouble() * 0.1D);
  }

  protected void initEquipment(LocalDifficulty difficulty) {
  }

  protected ItemStack getSkull() {
    return ItemStack.EMPTY;
  }

  public static boolean canSpawn(EntityType<NetherZombieEntity> type, IWorld world, SpawnType spawnType, BlockPos pos,
      RandomAccess random) {
  return world.getDifficulty() != Difficulty.PEACEFUL && !world.isAir(pos.add(0, -1, 0));
}



  public boolean canSpawn(IWorld iWorld_1, SpawnType spawnType_1) {
    BlockPos entityPos = new BlockPos(this.getX(), this.getY()-1, this.getZ());
    return !iWorld_1.isAir(entityPos) && this.getPathfindingFavor(entityPos) >= 0.0F;
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
}
