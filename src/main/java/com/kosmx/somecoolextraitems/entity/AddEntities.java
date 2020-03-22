package com.kosmx.somecoolextraitems.entity;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.Biome.SpawnEntry;

public class AddEntities {
    public static final EntityType<NetherZombieEntity> NETHER_ZOMBIE =
    Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("somecoolextraitems", "lava_zombie"),
        FabricEntityTypeBuilder.create(EntityCategory.MONSTER, (EntityType.EntityFactory<NetherZombieEntity>) NetherZombieEntity::new).size(EntityDimensions.fixed(0.6f,1.95f)).setImmuneToFire().build()
    );

    public AddEntities(){
        registerEntityToSpawn(NETHER_ZOMBIE, EntityCategory.MONSTER, 50, 1, 10, Biomes.NETHER);
    }

    public void registerEntityToSpawn(EntityType<?> entityType, EntityCategory classification, int amount, int min, int max, Biome... biomes ){
        for(Biome biome : biomes){
            biome.getEntitySpawnList(classification).add(new SpawnEntry(entityType, amount, min, max));
        }
    }

    public static void addServer(){

    }


}