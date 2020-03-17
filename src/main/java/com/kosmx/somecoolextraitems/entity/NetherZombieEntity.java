package com.kosmx.somecoolextraitems.entity;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class NetherZombieEntity extends ZombieEntity {

    public NetherZombieEntity(EntityType<? extends ZombieEntity> type, World world) {
		super(type, world);
    }

    public boolean isOnFire(){
        return false;
    }
    //net.minecraft.entity.mob.MagmaCubeEntity

}
