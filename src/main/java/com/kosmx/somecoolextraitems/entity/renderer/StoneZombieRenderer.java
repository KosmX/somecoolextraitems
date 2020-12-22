package com.kosmx.somecoolextraitems.entity.renderer;

import com.kosmx.somecoolextraitems.entity.StoneZombieEntity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

public class StoneZombieRenderer extends MobEntityRenderer<StoneZombieEntity, ZombieEntityModel<StoneZombieEntity>> {


    public StoneZombieRenderer(EntityRenderDispatcher renderManager) {
        super(renderManager, new ZombieEntityModel<>(0.0f, false), 1);
        this.addFeature(new HeadFeatureRenderer<>(this));
        this.addFeature(new ElytraFeatureRenderer<>(this));
        this.addFeature(new HeldItemFeatureRenderer<>(this));
        this.addFeature(new ArmorFeatureRenderer<>(this, new ZombieEntityModel<>(0.5F, true), new ZombieEntityModel<>(1.0F, true)));
    }
    //net.minecraft.client.render.entity.PlayerEntityRenderer

    @Override
    public Identifier getTexture(StoneZombieEntity entity) {
        return new Identifier("somecoolextraitems:textures/entity/stone_golem.png");

    //net.minecraft.client.render.entity.ZombieEntityRenderer
    //net.minecraft.client.render.entity.ZombiePigmanEntityRenderer
    }


}
