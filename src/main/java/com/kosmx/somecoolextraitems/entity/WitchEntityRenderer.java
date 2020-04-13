package com.kosmx.somecoolextraitems.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.ArmorBipedFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class WitchEntityRenderer extends MobEntityRenderer<WitchEntity, PlayerEntityModel<WitchEntity>> {

    public WitchEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new PlayerEntityModel<>(0.0f, true), 0.5f);
        this.addFeature(new HeldItemFeatureRenderer<>(this));
        this.addFeature(new ArmorBipedFeatureRenderer<>(this, new BipedEntityModel<>(0.0f), new BipedEntityModel<>((0.5f))));
        //this.addFeature(new HeadFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(WitchEntity entity) {
        return new Identifier("somecoolextraitems:textures/entity/witch.png");
    }
    //net.minecraft.client.render.entity.PlayerEntityRenderer

    
}