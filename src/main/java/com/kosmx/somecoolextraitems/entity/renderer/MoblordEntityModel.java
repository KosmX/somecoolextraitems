package com.kosmx.somecoolextraitems.entity.renderer;

import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;

public class MoblordEntityModel<T extends LivingEntity> extends PlayerEntityModel<T> {

    public MoblordEntityModel(float scale, boolean thinArms) {
        super(scale, thinArms);
    }

    
    public void setAngles(T livingEntity, float f, float g, float h, float i, float j){
        super.setAngles(livingEntity, f, g, h, i, j);
        this.rightArm.pitch = 0f;
        this.leftArm.pitch = 0;
        this.rightLeg.pitch = 0;
        this.leftLeg.pitch = 0;
        this.rightPantLeg.pitch = 0;
        this.leftPantLeg.pitch = 0;
        this.rightSleeve.pitch = 0f;
        this.leftSleeve.pitch = 0;
        if(livingEntity.getMainArm() == Arm.RIGHT){
            this.rightArm.pitch = -1.4f;
            this.rightSleeve.pitch = -1.4f;
        }
        else{
            this.leftArm.pitch = -1.4f;
            this.leftSleeve.pitch = -1.4f;
        }
    }

}