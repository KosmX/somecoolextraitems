package com.kosmx.somecoolextraitems.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.world.World;

public abstract class AbstractSkeletonHelper extends AbstractSkeletonEntity {

    protected AbstractSkeletonHelper(EntityType<? extends AbstractSkeletonEntity> type, World world) {
        super(type, world);
    }

}