package com.kosmx.somecoolextraitems.entity.renderer;

import com.kosmx.somecoolextraitems.entity.AddEntities;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class AddClientEntity{
    public AddClientEntity(){
        EntityRendererRegistry.INSTANCE.register(AddEntities.NETHER_ZOMBIE, (entityRenderDispatcher, contex) -> new NetherZombieRenderer(entityRenderDispatcher));
        EntityRendererRegistry.INSTANCE.register(AddEntities.STONE_ZOMBIE, (entityRenderDispatcher, contex) -> new StoneZombieRenderer(entityRenderDispatcher));
        EntityRendererRegistry.INSTANCE.register(AddEntities.BOAR_ENTITY, (entityRenderDispatcher, ctx) -> new BoarEntityRenderer(entityRenderDispatcher));
        EntityRendererRegistry.INSTANCE.register(AddEntities.WITCH_ENTITY, (entityRenderDispatcher, ctx) -> new WitchEntityRenderer<>(entityRenderDispatcher));
        EntityRendererRegistry.INSTANCE.register(AddEntities.MAGIC_PROJECTILE, (entityRenderDispatcher, ctx) -> new FlyingItemEntityRenderer<>(entityRenderDispatcher, ctx.getItemRenderer(), 0.75f, true));
    }
}