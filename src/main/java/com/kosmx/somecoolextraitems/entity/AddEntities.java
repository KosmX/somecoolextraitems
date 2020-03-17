package com.kosmx.somecoolextraitems.entity;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.Biome.SpawnEntry;

//import com.kosmx.somecoolextraitems.entity.LavaDrownedEntity;

public class AddEntities {
    public static final EntityType<NetherZombieEntity> NETHER_ZOMBIE =
    Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("somecoolextraitems", "lava_zombie"),
        FabricEntityTypeBuilder.create(EntityCategory.MONSTER, (EntityType.EntityFactory<NetherZombieEntity>) NetherZombieEntity::new).size(EntityDimensions.fixed(1,2)).setImmuneToFire().build()
    );

    public AddEntities(){
        registerEntityToSpawn(NETHER_ZOMBIE, EntityCategory.MONSTER, 20, 1, 10, Biomes.NETHER);
    }

    public void registerEntityToSpawn(EntityType<?> entityType, EntityCategory classification, int amount, int min, int max, Biome... biomes ){
        for(Biome biome : biomes){
            biome.getEntitySpawnList(classification).add(new SpawnEntry(entityType, amount, min, max));
        }
    }

    public static void addServer(){

    }

    public static void addClient(){
        EntityRendererRegistry.INSTANCE.register(NETHER_ZOMBIE, (entityRenderDispatcher, contex) -> new NetherZombieRenderer(entityRenderDispatcher));
    }
}