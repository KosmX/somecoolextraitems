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

    public static final EntityType<StoneZombieEntity> STONE_ZOMBIE = 
    Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("somecoolextraitems", "stone_golem"),
        FabricEntityTypeBuilder.create(EntityCategory.MONSTER, (EntityType.EntityFactory<StoneZombieEntity>) StoneZombieEntity::new).size(EntityDimensions.fixed(0.6f, 1.95f)).build()
    );

    public static final EntityType<BoarEntity> BOAR_ENTITY = 
    Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("somecoolextraitems", "boar"),
        FabricEntityTypeBuilder.create(EntityCategory.CREATURE, (EntityType.EntityFactory<BoarEntity>) BoarEntity::new).size(EntityDimensions.fixed(0.9f, 0.9f)).build()
        );

    public AddEntities(){
        registerEntityToSpawn(NETHER_ZOMBIE, EntityCategory.MONSTER, 50, 1, 10, Biomes.NETHER);
        registerEntityToSpawn(STONE_ZOMBIE, EntityCategory.MONSTER, 125, 4, 4, Biomes.PLAINS, Biomes.DESERT, Biomes.MOUNTAINS, Biomes.FOREST, Biomes.TAIGA, Biomes.SWAMP, Biomes.RIVER, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER, Biomes.SNOWY_TUNDRA, Biomes.SNOWY_MOUNTAINS, Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE, Biomes.BEACH, Biomes.DESERT_HILLS, Biomes.WOODED_HILLS, Biomes.TAIGA_HILLS, Biomes.MOUNTAIN_EDGE, Biomes.JUNGLE, Biomes.JUNGLE_HILLS, Biomes.JUNGLE_EDGE, Biomes.DEEP_OCEAN, Biomes.STONE_SHORE, Biomes.SNOWY_BEACH, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.WOODED_MOUNTAINS, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.BADLANDS, Biomes.WOODED_BADLANDS_PLATEAU, Biomes.BADLANDS_PLATEAU, Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN, Biomes.THE_VOID, Biomes.SUNFLOWER_PLAINS, Biomes.DESERT_LAKES, Biomes.GRAVELLY_MOUNTAINS, Biomes.FLOWER_FOREST, Biomes.TAIGA_MOUNTAINS, Biomes.SWAMP_HILLS, Biomes.ICE_SPIKES, Biomes.MODIFIED_JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS, Biomes.DARK_FOREST_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.MODIFIED_BADLANDS_PLATEAU, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS);
        registerEntityToSpawn(BOAR_ENTITY, EntityCategory.CREATURE, 20, 1, 3, Biomes.SNOWY_TAIGA, Biomes.TAIGA, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA, Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST);
        registerEntityToSpawn(BOAR_ENTITY, EntityCategory.CREATURE, 75, 4, 6, Biomes.SWAMP);
    }

    public void registerEntityToSpawn(EntityType<?> entityType, EntityCategory classification, int amount, int min, int max, Biome... biomes ){
        for(Biome biome : biomes){
            biome.getEntitySpawnList(classification).add(new SpawnEntry(entityType, amount, min, max));
        }
    }


}