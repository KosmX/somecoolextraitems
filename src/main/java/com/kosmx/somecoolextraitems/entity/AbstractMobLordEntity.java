package com.kosmx.somecoolextraitems.entity;

import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
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
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class AbstractMobLordEntity extends HostileEntity implements RangedAttackMob{
	private boolean doesRangedAttack = true;
	private final FireballAttackGoal rangedAttackGoal;
	private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.2d, false){
		public void stop(){
			super.stop();
			AbstractMobLordEntity.this.setAttacking(false);
		}
		public void start(){
			super.start();
			AbstractMobLordEntity.this.setAttacking(true);
		}
	};
	//private int fireballStrenght = 1;
	protected AbstractMobLordEntity(EntityType<? extends HostileEntity> type, World world, int attackSpeed, int meleeRange) {
		super(type, world);
		this.rangedAttackGoal = new FireballAttackGoal(this);
		//TODO this.updateAttackType();
		//net.minecraft.entity.mob.AbstractSkeletonEntity
		//net.minecraft.entity.mob.SkeletonEntity
		//net.minecraft.entity.mob.BlazeEntity
	}

	protected AbstractMobLordEntity(EntityType<? extends HostileEntity> type, World world){
		this(type, world, 40, 8);
	}
    protected void initGoals(){
		this.goalSelector.add(3, new AvoidSunlightGoal(this));
		this.goalSelector.add(4, new EscapeSunlightGoal(this,1d));
		this.goalSelector.add(1, rangedAttackGoal);
		this.goalSelector.add(2, meleeAttackGoal);
		this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.6f));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 10));
		this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));	//TODO don't revenge on its own skeletons
		this.targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.add(3, new FollowTargetGoal<>(this, IronGolemEntity.class, true));
	}

	protected void initAttributes(){
		super.initAttributes();
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.3d);
	}


	protected void playStepSound(){
		//It's floating, what do you think?
	}

	public EntityGroup getGroup(){
		return EntityGroup.UNDEAD;
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
			this. mobLord = mobLord;
			this.attackSpeed = attackSpeed;
			this.meleeRange = meleeRange;
			this.attackRange = attackRange;
			this.speed = speed;
		}
		public FireballAttackGoal(AbstractMobLordEntity mobLord){
			this(mobLord, 30, 8, 81, 1d);
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
						double y = target.getY() - this.mobLord.getY() + 1;
						world.playLevelEvent((PlayerEntity)null, 1016, new BlockPos(this.mobLord), 0);
						SmallFireballEntity fireball = new SmallFireballEntity(world, this.mobLord, x, y, z);
						fireball.updatePosition(this.mobLord.getX() + vec3d.x, this.mobLord.getY() + 1, this.mobLord.getZ() + vec3d.z);
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