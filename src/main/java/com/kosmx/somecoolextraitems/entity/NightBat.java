package com.kosmx.somecoolextraitems.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class NightBat extends HostileEntity {
    /** {@link net.minecraft.entity.passive.BeeEntity}.       //code from here
     *
     */
    protected NightBat(EntityType<? extends HostileEntity> type, World world) {
        super(type, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
        this.setPathfindingPenalty(PathNodeType.WATER, -1f);
        this.setPathfindingPenalty(PathNodeType.COCOA, -1f);
        this.setPathfindingPenalty(PathNodeType.FENCE, -1f);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, NightBat.BatAttackGoal(this));
        //TODO this.targetSelector.add(new ...);
    }


    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(32d);
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.4d);
        this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(2d);     //it's just a "stupid" bat
        this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(2d);        //you can kill it with only 1 hit
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BAT_AMBIENT;
    }

    protected SoundEvent getHurtSound(){
        return SoundEvents.ENTITY_BAT_HURT;
    }

    protected SoundEvent getDeathSound(){
        return SoundEvents.ENTITY_BAT_DEATH;
    }

    private static Goal BatAttackGoal(NightBat nightBat) {
    }
}
