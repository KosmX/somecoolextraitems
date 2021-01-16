package com.kosmx.somecoolextraitems.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.RandomAccess;

import com.kosmx.somecoolextraitems.items.AddItems;
//net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

public class NetherZombieEntity extends ZombieEntity {
    //protected static final EntityAttribute SPAWN_REINFORCEMENTS = (new ClampedEntityAttribute(("lava_zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D
    //)).setName("Spawn Reinforcements Chance");

    public NetherZombieEntity(EntityType<? extends ZombieEntity> type, World world) {
        super(type, world);
        this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
    }

    protected void mobTick() {
        if (isWet()) {
            this.damage(DamageSource.DROWN, 1.0F);
        }
        super.mobTick();
    }

    public boolean isOnFire() {
        return false;
    }

    protected boolean burnsInDaylight() {
        return false;
    }
    // net.minecraft.entity.mob.MagmaCubeEntity
    // net.minecraft.entity.mob.ZombiePigmanEntity
    // net.minecraft.entity.mob.EndermanEntity

    public boolean tryAttack(Entity target) {
        boolean bl = super.tryAttack(target);
        if (bl) {
            float f = this.world.getLocalDifficulty(new BlockPos(this.getPos())).getLocalDifficulty();
            if (this.getMainHandStack().isEmpty()) {
                target.setOnFireFor(3 * (int) f);
            }
        }

        return bl;
    }


    protected boolean canConvertInWater() {
        return false;
    }

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

    //@Override
    //protected void initDataTracker() {
    //    super.initDataTracker();
    //    this.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(35.0D);
    //    this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    //    this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(6.0D);
    //    this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(2.0D);
    //    this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(25.0D);
    //}

    public static DefaultAttributeContainer.Builder createNetherZombieDataTracker(){
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 45d)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 23000000417232513D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6)
                .add(EntityAttributes.GENERIC_ARMOR, 2)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 25);
    }

    protected void initEquipment(LocalDifficulty difficulty) {
        int i = this.random.nextInt(6);
        if (i == 0) {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(AddItems.NGoldAxe));
        }
        else if (i <= 3) {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(AddItems.NGoldSword));
        }
        if (i <= 3) {
            this.handDropChances[EquipmentSlot.MAINHAND.getEntitySlotId()] = 0.03f;
        }
    }

    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }

    public static boolean canSpawn(EntityType<NetherZombieEntity> type, WorldAccess world, SpawnReason spawnType, BlockPos pos,
                                   RandomAccess random) {
        return world.getDifficulty() != Difficulty.PEACEFUL && !world.isAir(pos.add(0, -1, 0));
    }



  /*public boolean canSpawn(IWorld iWorld_1, SpawnType spawnType_1) {
    BlockPos entityPos = new BlockPos(this.getX(), this.getY(), this.getZ());
    return !iWorld_1.isAir(entityPos.add(0, -1, 0));
  }*/


}
