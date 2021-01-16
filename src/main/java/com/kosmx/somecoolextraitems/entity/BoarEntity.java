package com.kosmx.somecoolextraitems.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
//import net.minecraft.entity.ai.goal.AttackGoal;

public class BoarEntity extends AnimalEntity {
    // net.minecraft.entity.mob.ZombiePigmanEntity
    // net.minecraft.entity.passive.WolfEntity
    //private static final TrackedData<Boolean> SADDLED;
    private static final TrackedData<Integer> field_6815;
    private static final Ingredient BREEDING_INGREDIENT;
    private static final EntityAttributeModifier ATTACKING_SPEED_BOOST;
    private static final UUID ATTACKING_SPEED_BOOST_UUID = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private boolean field_6814;
    private int field_6812;
    private int field_6813;
    private int anger;
    private UUID angerTarget;

    public BoarEntity(EntityType<? extends BoarEntity> entityType, World world) {
        super(entityType, world);
    }

    public void setAttacker(@Nullable LivingEntity attacker) {
        super.setAttacker(attacker);
        if (attacker != null) {
            this.angerTarget = attacker.getUuid();
        }
    }

    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.3d, true));
        this.goalSelector.add(2, new SwimGoal(this));
        // this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25D)); //-it'll attack.
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0D));
        // this.goalSelector.add(4, new TemptGoal(this, 1.2D,
        // Ingredient.ofItems(Items.CARROT_ON_A_STICK), false));
        this.goalSelector.add(4, new TemptGoal(this, 1.2D, false, BREEDING_INGREDIENT));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(1, new BoarEntity.FollowPlayerIfAngryGoal(this));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        //this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(12.0D);
        //this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25D);
        // this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        // this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        //this.getAttributes().(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(4d);
        this.dataTracker.startTracking(field_6815, 0);
    }

    public static DefaultAttributeContainer.Builder createBoarAttributes(){
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4d).add(EntityAttributes.GENERIC_MAX_HEALTH, 12d).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25d).add(EntityAttributes.GENERIC_FOLLOW_RANGE).add(EntityAttributes.GENERIC_ARMOR, 1);
    }

    @Nullable
    //public Entity getPrimaryPassenger() {
    //    return this.getPassengerList().isEmpty() ? null : (Entity) this.getPassengerList().get(0);
    //}

    public boolean tryAttack(Entity target) {
        boolean bl = target.damage(DamageSource.mob(this), (float) ((int) this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue()));
        if (bl) {
            this.dealDamage(this, target);
        }
        return bl;
    }

    public void mobTick() {
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        LivingEntity entity = getAttacker();
        if (this.isAngry()) {
            if (!this.isBaby() && entityAttributeInstance.hasModifier(ATTACKING_SPEED_BOOST)) {
                entityAttributeInstance.addTemporaryModifier(ATTACKING_SPEED_BOOST);
            }

            this.anger--;
            LivingEntity target = entity != null ? entity : this.getTarget();
            if (!this.isAngry() && target != null) {
                if (!this.canSee(target)) {
                    this.setAttacker((LivingEntity) null);
                    this.setTarget((LivingEntity) null);
                }
                else {
                    this.anger = this.calculateAnger();
                }
            }
        }
        else if (entityAttributeInstance.hasModifier(ATTACKING_SPEED_BOOST)) {
            entityAttributeInstance.removeModifier(ATTACKING_SPEED_BOOST);
        }

        if (this.isAngry() && this.angerTarget != null && entity == null) {
            PlayerEntity player = this.world.getPlayerByUuid(this.angerTarget);
            this.setAttacker(player);
            this.attackingPlayer = player;
            this.playerHitTimer = this.getLastAttackTime();
        }
        super.mobTick();
    }

    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        else {
            if (!this.isBaby()) {
                Entity entity = source.getAttacker();
                if (entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative() && this.canSee(entity)) {
                    this.setAnger((LivingEntity) source.getAttacker());
                }
            }
            return super.damage(source, amount);
        }
    }

    public void setAnger(LivingEntity entity) {
        this.anger = this.calculateAnger();
        this.setAttacker(entity);
    }

    private int calculateAnger() {
        return 400 + this.random.nextInt(400);
    }

    public boolean isAngry() {
        return this.anger > 0;
    }

    public boolean isAngryAt(PlayerEntity player) {
        return this.isAngry();
    }

    static class FollowPlayerIfAngryGoal extends FollowTargetGoal<PlayerEntity> {

        public FollowPlayerIfAngryGoal(BoarEntity entity) {
            super(entity, PlayerEntity.class, true);
        }

        public boolean canStart() {
            return ((BoarEntity) this.mob).isAngry() && super.canStart();
        }

    }

    public boolean canBeControlledByRider() {
        Entity entity = this.getPrimaryPassenger();
        if (!(entity instanceof PlayerEntity)) {
            return false;
        }
        else {
            PlayerEntity playerEntity = (PlayerEntity) entity;
            return playerEntity.getMainHandStack().getItem() == Items.CARROT_ON_A_STICK
                    || playerEntity.getOffHandStack().getItem() == Items.CARROT_ON_A_STICK;
        }
    }

    public void onTrackedDataSet(TrackedData<?> data) {
        if (field_6815.equals(data) && this.world.isClient) {
            this.field_6814 = true;
            this.field_6812 = 0;
            this.field_6813 = (Integer) this.dataTracker.get(field_6815);
        }

        super.onTrackedDataSet(data);
    }

    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        //tag.putBoolean("Saddle", this.isSaddled());
        tag.putShort("Anger", (short) this.anger);
        if (this.angerTarget != null) {
            tag.putString("HurtBy", this.angerTarget.toString());
        }
        else {
            tag.putString("HurtBy", "");
        }
    }

    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        //this.setSaddled(tag.getBoolean("Saddle"));
        this.anger = tag.getShort("Anger");
        String attacker = tag.getString("HurtBy");
        if (!attacker.isEmpty()) {
            this.angerTarget = UUID.fromString(attacker);
            PlayerEntity player = this.world.getPlayerByUuid(this.angerTarget);
            this.setAttacker(player);
            if (player != null) {
                this.attackingPlayer = player;
                this.playerHitTimer = this.getLastAttackTime();
            }
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_PIG_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PIG_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.NAME_TAG) {
            itemStack.useOnEntity(player, this, hand);
            return ActionResult.SUCCESS;
            //} else if (this.isSaddled() && !this.hasPassengers()) {
            //    if (!this.world.isClient) {
            //        player.startRiding(this);
            //    }

            //    return true;
            //} else {
            //    return itemStack.getItem() == Items.SADDLE && itemStack.useOnEntity(player, this, hand);
        }
        else {
            return super.interactMob(player, hand);
        }
    }

    //protected void dropInventory() {
    //super.dropInventory();
    //if (this.isSaddled()) {
    //    this.dropItem(Items.SADDLE);
    //}

    //}

    //public boolean isSaddled() {
    //    return (Boolean) this.dataTracker.get(SADDLED);
    //}

    //public void setSaddled(boolean saddled) {
    //    if (saddled) {
    //        this.dataTracker.set(SADDLED, true);
    //    } else {
    //        this.dataTracker.set(SADDLED, false);
    //    }
    //}


    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        ZombifiedPiglinEntity zombiePigmanEntity = (ZombifiedPiglinEntity) EntityType.ZOMBIFIED_PIGLIN.create(this.world);
        zombiePigmanEntity.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
        zombiePigmanEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.yaw, this.pitch);
        zombiePigmanEntity.setAiDisabled(this.isAiDisabled());
        if (this.hasCustomName()) {
            zombiePigmanEntity.setCustomName(this.getCustomName());
            zombiePigmanEntity.setCustomNameVisible(this.isCustomNameVisible());
        }

        this.world.spawnEntity(zombiePigmanEntity);
        this.remove();
    }

    public void travel(Vec3d movementInput) {
        if (this.isAlive()) {
            Entity entity = this.getPassengerList().isEmpty() ? null : (Entity) this.getPassengerList().get(0);
            if (this.hasPassengers() && this.canBeControlledByRider()) {
                this.yaw = entity.yaw;
                this.prevYaw = this.yaw;
                this.pitch = entity.pitch * 0.5F;
                this.setRotation(this.yaw, this.pitch);
                this.bodyYaw = this.yaw;
                this.headYaw = this.yaw;
                this.stepHeight = 1.0F;
                this.flyingSpeed = this.getMovementSpeed() * 0.1F;
                if (this.field_6814 && this.field_6812++ > this.field_6813) {
                    this.field_6814 = false;
                }

                if (this.isLogicalSideForUpdatingMovement()) {
                    float f = (float) this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).getValue() * 0.225F;
                    if (this.field_6814) {
                        f += f * 1.15F * MathHelper.sin((float) this.field_6812 / (float) this.field_6813 * 3.1415927F);
                    }

                    this.setMovementSpeed(f);
                    super.travel(new Vec3d(0.0D, 0.0D, 1.0D));
                    this.bodyTrackingIncrements = 0;
                }
                else {
                    this.setVelocity(Vec3d.ZERO);
                }

                this.lastLimbDistance = this.limbDistance;
                double d = this.getX() - this.prevX;
                double e = this.getZ() - this.prevZ;
                float g = MathHelper.sqrt(d * d + e * e) * 4.0F;
                if (g > 1.0F) {
                    g = 1.0F;
                }

                this.limbDistance += (g - this.limbDistance) * 0.4F;
                this.limbAngle += this.limbDistance;
            }
            else {
                this.stepHeight = 0.5F;
                this.flyingSpeed = 0.02F;
                super.travel(movementInput);
            }
        }
    }

    public boolean method_6577() {
        if (this.field_6814) {
            return false;
        }
        else {
            this.field_6814 = true;
            this.field_6812 = 0;
            this.field_6813 = this.getRandom().nextInt(841) + 140;
            this.getDataTracker().set(field_6815, this.field_6813);
            return true;
        }
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BoarEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        return AddEntities.BOAR_ENTITY.create(this.world);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    static {
        //SADDLED = DataTracker.registerData(BoarEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        field_6815 = DataTracker.registerData(BoarEntity.class, TrackedDataHandlerRegistry.INTEGER);
        BREEDING_INGREDIENT = Ingredient.ofItems(Items.CARROT, Items.POTATO, Items.BEETROOT);
        ATTACKING_SPEED_BOOST = (new EntityAttributeModifier(ATTACKING_SPEED_BOOST_UUID, "Attacking speed boost", 0.2d, EntityAttributeModifier.Operation.ADDITION));
    }

    /*public static boolean canSpawn(EntityType<BoarEntity> type, IWorld world, SpawnType spawn, BlockPos pos, RandomAccess random){
        return !world.isAir(pos.add(0, -1, 0)) && pos.getY() >= 62;
    }

    public boolean canSpawn(IWorld world, SpawnType spawnType){
        BlockPos pos = new BlockPos(this.getX(), this.getY(), this.getZ());
        return !world.isAir(pos.add(0, -1, 0)) && this.getY() >= 62;
    }*/

}