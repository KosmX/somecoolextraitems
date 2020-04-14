package com.kosmx.somecoolextraitems.entity;

import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidSunlightGoal;
import net.minecraft.entity.ai.goal.EscapeSunlightGoal;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public abstract class AbstractMobLordEntity extends HostileEntity implements MobSpawningItnerface {
	private final FireballAttackGoal rangedAttackGoal = new FireballAttackGoal(this);
	private int summonCooldown = 0;
	private boolean doesRangedAttack = true;
	private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.2d, false); /*{
		public void stop(){
			super.stop();
			AbstractMobLordEntity.this.setAttacking(false);
		}
		public void start(){
			super.start();
			AbstractMobLordEntity.this.setAttacking(true);
		}
	};*/
	private float field_6743;
	private float field_6737 = 1f;
	//private int fireballStrenght = 1;
	protected AbstractMobLordEntity(EntityType<? extends HostileEntity> type, World world, int attackSpeed, int meleeRange) {
		super(type, world);
		//net.minecraft.entity.mob.AbstractSkeletonEntity
		//net.minecraft.entity.mob.SkeletonEntity
		//net.minecraft.entity.mob.BlazeEntity
		//net.minecraft.entity.mob.GhastEntity
		//net.minecraft.entity.passive.ChickenEntity
		this.goalSelector.add(1, this.rangedAttackGoal);
		//this.goalSelector.add(2, this.meleeAttackGoal);
	}
	
	public void tickMovement(){
		
		super.tickMovement();
		this.field_6743 = (float)((double)this.field_6743 + (double)(this.onGround ? -1 : 4) * 0.3D);
		this.field_6743 = MathHelper.clamp(this.field_6743, 0.0F, 1.0F);
		if (!this.onGround && this.field_6737 < 1.0F) {
		   this.field_6737 = 1.0F;
		}
  
		this.field_6737 = (float)((double)this.field_6737 * 0.9D);
		Vec3d vec3d = this.getVelocity();
		if (!this.onGround && vec3d.y < 0.0D) {
		   this.setVelocity(vec3d.multiply(1.0D, 0.6D, 1.0D));
		}
  
		//this.field_6741 += this.field_6737 * 2.0F;
	}

	protected AbstractMobLordEntity(EntityType<? extends HostileEntity> type, World world){
		this(type, world, 40, 8);
	}
    protected void initGoals(){
		this.goalSelector.add(3, new AvoidSunlightGoal(this));
		this.goalSelector.add(4, new EscapeSunlightGoal(this,1d));
		//this.goalSelector.add(1, this.rangedAttackGoal);
		//this.goalSelector.add(2, this.meleeAttackGoal);
		this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.6f));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 10));
		this.targetSelector.add(1, new RevengeGoal(this, SkeletonEntity.class));
		this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.add(3, new FollowTargetGoal<>(this, IronGolemEntity.class, true));
	}
	public boolean handleFallDamage(float d, float m){
        return false;
    }

	protected void initAttributes(){
		super.initAttributes();
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.3d);
	}

	protected void mobTick(){
		//this.world.addParticle(new DustParticleEffect(), alwaysSpawn, x, y, z, velocityX, velocityY, velocityZ);

		if(!isAiDisabled() && !this.world.isClient){
			this.updateAttackType();
			if(this.summonCooldown <= 0 && this.getTarget() != null && this.getTarget().canSee(this)){
				this.summonMinions();
				this.summonCooldown = setSummonCooldown();
			}
			else{
				--this.summonCooldown;
			}
		}
		super.mobTick();
	}

	protected int setSummonCooldown(){
		if (this.world.getDifficulty() == Difficulty.HARD){
			return this.getRandom().nextInt(180) + 160;
		}
		return this.getRandom().nextInt(240) + 160;
	}

	protected void playStepSound(){
		//It's floating, what do you think?
	}

	public EntityGroup getGroup(){
		return EntityGroup.ILLAGER;
	}

	
	protected void updateAttackType(){
		if(this.getTarget() != null){
			boolean isTargetFar = this.getTarget().squaredDistanceTo(this) > 8;
			if (isTargetFar && !this.doesRangedAttack){
				this.goalSelector.remove(this.meleeAttackGoal);
				this.goalSelector.add(1, this.rangedAttackGoal);
				this.doesRangedAttack = true;
			}
			else if (!isTargetFar && this.doesRangedAttack){
				this.goalSelector.remove(this.rangedAttackGoal);
				this.goalSelector.add(1, this.meleeAttackGoal);
				this.doesRangedAttack = false;
			}
		}
	}

	static class FireballAttackGoal extends	Goal{
		private final AbstractMobLordEntity mobLord;
		private int targetVisibility = 0;
		public int cooldown;
		private int attackSpeed;
		private int meleeRange;
		private double attackRange;
		private double speed;
		private int moveTick = -1;
		private boolean movingToLeft;
		private boolean movingToBack;

		public FireballAttackGoal(AbstractMobLordEntity mobLord, int attackSpeed, int meleeRange, double attackRange, double speed){
			this.mobLord = mobLord;
			this.attackSpeed = attackSpeed;
			this.meleeRange = meleeRange;
			this.attackRange = attackRange;
			this.speed = speed;
		}
		public FireballAttackGoal(AbstractMobLordEntity mobLord){
			this(mobLord, 30, 8, 512, 1d);
		}

		private boolean knownTarget(){
			return this.targetVisibility >=0;
		}

		public boolean canStart() {
			return this.mobLord.getTarget() != null && this.mobLord.getTarget().squaredDistanceTo(this.mobLord) > this.meleeRange;
		}

		public void start(){
			this.cooldown = 0;
		}

		private void moveControl(LivingEntity target, double distance){
			if (distance <= (double)this.attackRange && this.knownTarget()){
				if (this.moveTick == 20){
					this.mobLord.getNavigation().stop();
					if(this.mobLord.getRandom().nextFloat() < 0.3f){
						this.movingToLeft = !this.movingToLeft;
					}
					if(distance < (double)(this.attackRange * 0.25f)){
						this.movingToBack = true;
					}
					else if(distance > (double)(this.attackRange * 0.75f)){
						this.movingToBack = false;
					}
					else if (this.mobLord.getRandom().nextFloat() < 0.3f){
						this.movingToBack = !this.movingToBack;
					}

					this.mobLord.getMoveControl().strafeTo(this.movingToBack ? -0.5f : 0.5f, this.movingToLeft ? 0.5f : -0.5f);
					this.moveTick = 0;
				}
				else{
					++this.moveTick;
				}
				this.mobLord.getLookControl().lookAt(target, 30f, 30f);
			}
			else{
				this.mobLord.getNavigation().startMovingTo(target, this.speed);
			}

		}
		

		public void tick(){
			LivingEntity target = this.mobLord.getTarget();
			if(target != null){
				if (this.targetVisibility <= 60 && this.mobLord.canSee(target)){
					++this.targetVisibility;
				}
				else if(this.targetVisibility != 0 && !this.mobLord.canSee(target)){
					--this.targetVisibility;
				}
				double distance = target.squaredDistanceTo(this.mobLord);

				this.moveControl(target, distance);

				if (distance < 2048d && this.knownTarget()){
					++this.cooldown;
					if(this.cooldown >= this.attackSpeed){
						World world = this.mobLord.world;
						Vec3d vec3d = this.mobLord.getRotationVec(1.0f);
						double x = target.getX() - this.mobLord.getX() + vec3d.x;
						double z = target.getZ() - this.mobLord.getZ() + vec3d.z;
						double y = target.getY() - this.mobLord.getY();
						world.playLevelEvent((PlayerEntity)null, 1016, new BlockPos(this.mobLord), 0);
						MagicalFireballEntity fireball = new MagicalFireballEntity(world, this.mobLord, x, y, z);
						fireball.updatePosition(this.mobLord.getX() + vec3d.x , this.mobLord.getY() + 1.7d, this.mobLord.getZ() + vec3d.z);
						world.spawnEntity(fireball);
						this.cooldown = -10;
					}
				}
				else if(this.cooldown > 0 && this.knownTarget()){
					--this.cooldown;
				}
			}
		}
	}
	

}