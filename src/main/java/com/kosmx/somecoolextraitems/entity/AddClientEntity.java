package com.kosmx.somecoolextraitems.entity;


import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class AddClientEntity{
    public AddClientEntity(){
        EntityRendererRegistry.INSTANCE.register(AddEntities.NETHER_ZOMBIE, (entityRenderDispatcher, contex) -> new NetherZombieRenderer(entityRenderDispatcher));
    }
}