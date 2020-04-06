package com.kosmx.somecoolextraitems.entity;


import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class AddClientEntity{
    public AddClientEntity(){
        EntityRendererRegistry.INSTANCE.register(AddEntities.NETHER_ZOMBIE, (entityRenderDispatcher, contex) -> new NetherZombieRenderer(entityRenderDispatcher));
        EntityRendererRegistry.INSTANCE.register(AddEntities.STONE_ZOMBIE, (entityRenderDispatcher, contex) -> new StoneZombieRenderer(entityRenderDispatcher));
        EntityRendererRegistry.INSTANCE.register(AddEntities.BOAR_ENTITY, (entityRenderDispatcher, ctx) -> new BoarEntityRenderer(entityRenderDispatcher));
    }
}