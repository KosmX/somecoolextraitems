package com.kosmx.somecoolextraitems.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

public class NetherZombieRenderer extends MobEntityRenderer<NetherZombieEntity, ZombieEntityModel<NetherZombieEntity>> {


    public NetherZombieRenderer(EntityRenderDispatcher renderManager) {
        super(renderManager, new ZombieEntityModel<>(0.5f, false), 1);
    }

    @Override
    public Identifier getTexture(NetherZombieEntity entity) {
        return new Identifier("somecoolextraitems:textures/entity/lava_zombie/lava_zombie.png");

    //net.minecraft.client.render.entity.MagmaCubeEntityRenderer
    }

    protected int getBlockLight(NetherZombieEntity magmaCubeEntity, float f) {
        return 15;
    }


}
