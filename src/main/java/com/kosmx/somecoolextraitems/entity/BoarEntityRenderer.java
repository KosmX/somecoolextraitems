package com.kosmx.somecoolextraitems.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.util.Identifier;

public class BoarEntityRenderer extends MobEntityRenderer<BoarEntity, PigEntityModel<BoarEntity>>{
    //net.minecraft.client.render.entity.PigEntityRenderer

    public BoarEntityRenderer(EntityRenderDispatcher renderManager) {
        super(renderManager, new PigEntityModel<>(), 0.7f);
        //this.addFeature(new BoarSaddleFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(BoarEntity entity) {
        return new Identifier("somecoolextraitems:textures/entity/boar.png");
    }

}