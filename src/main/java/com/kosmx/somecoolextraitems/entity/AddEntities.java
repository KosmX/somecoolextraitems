package com.kosmx.somecoolextraitems.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings;
import org.lwjgl.system.CallbackI;

import java.util.List;


public class AddEntities {


    //minecraft's code
    //net.minecraft.entity.SpawnRestriction;
    //net.minecraft.entity.EntityType
    public static final EntityType<NetherZombieEntity> NETHER_ZOMBIE =
    Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("somecoolextraitems", "lava_zombie"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, NetherZombieEntity::new).dimensions(EntityDimensions.changing(0.6f, 1.95f)).fireImmune().build()
    )
    ;

    public static final EntityType<StoneZombieEntity> STONE_ZOMBIE = 
    Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("somecoolextraitems", "stone_golem"),
        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, StoneZombieEntity::new).dimensions(EntityDimensions.changing(0.6f, 1.95f)).build()
    )
    ;

    public static final EntityType<BoarEntity> BOAR_ENTITY = 
    Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("somecoolextraitems", "boar"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BoarEntity::new).dimensions(EntityDimensions.changing(0.9f, 0.9f)).build()
    )
    ;

    public static final EntityType<WitchEntity> WITCH_ENTITY = 
    Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("somecoolextraitems", "witch"),
        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, WitchEntity::new).dimensions(EntityDimensions.fixed(0.65f, 1.95f)).fireImmune().build()
    )
    ;


    public static final EntityType<MagicalFireballEntity> MAGIC_PROJECTILE =
    Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("somecoolextraitems", "magic_projectile"),
        FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<MagicalFireballEntity>) MagicalFireballEntity::new).dimensions(EntityDimensions.fixed(0.3125F, 0.3125F)).build()
    )
    ;

    public static final EntityType<SkeletonMinion> SKELETON_MINION =
    Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("somecoolextraitems", "skeleton"),
        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SkeletonMinion::new).dimensions(EntityDimensions.changing(0.6F, 1.99F)).build()
    );



    public AddEntities(){

        FabricDefaultAttributeRegistry.register(NETHER_ZOMBIE, NetherZombieEntity.createNetherZombieDataTracker());
        FabricDefaultAttributeRegistry.register(STONE_ZOMBIE, StoneZombieEntity.createStoneZombieAttributes());
        FabricDefaultAttributeRegistry.register(BOAR_ENTITY, BoarEntity.createBoarAttributes());
        FabricDefaultAttributeRegistry.register(WITCH_ENTITY, WitchEntity.createWitchAttributes());
        FabricDefaultAttributeRegistry.register(SKELETON_MINION, SkeletonMinion.createAbstractSkeletonAttributes());

        registerEntityToSpawn(NETHER_ZOMBIE, SpawnGroup.MONSTER, 50, 1, 10, BiomeKeys.NETHER_WASTES);
        registerEntityToSpawn(STONE_ZOMBIE, SpawnGroup.MONSTER, 125, 4, 4, BiomeKeys.PLAINS, BiomeKeys.DESERT, BiomeKeys.MOUNTAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.SWAMP, BiomeKeys.RIVER, BiomeKeys.FROZEN_OCEAN, BiomeKeys.FROZEN_RIVER, BiomeKeys.SNOWY_TUNDRA, BiomeKeys.SNOWY_MOUNTAINS, BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.MUSHROOM_FIELD_SHORE, BiomeKeys.BEACH, BiomeKeys.DESERT_HILLS, BiomeKeys.WOODED_HILLS, BiomeKeys.TAIGA_HILLS, BiomeKeys.MOUNTAIN_EDGE, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE_HILLS, BiomeKeys.JUNGLE_EDGE, BiomeKeys.DEEP_OCEAN, BiomeKeys.STONE_SHORE, BiomeKeys.SNOWY_BEACH, BiomeKeys.BIRCH_FOREST, BiomeKeys.BIRCH_FOREST_HILLS, BiomeKeys.DARK_FOREST, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_TAIGA_HILLS, BiomeKeys.GIANT_TREE_TAIGA, BiomeKeys.GIANT_TREE_TAIGA_HILLS, BiomeKeys.WOODED_MOUNTAINS, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.BADLANDS, BiomeKeys.WOODED_BADLANDS_PLATEAU, BiomeKeys.BADLANDS_PLATEAU, BiomeKeys.WARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_WARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.THE_VOID, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.DESERT_LAKES, BiomeKeys.GRAVELLY_MOUNTAINS, BiomeKeys.FLOWER_FOREST, BiomeKeys.TAIGA_MOUNTAINS, BiomeKeys.SWAMP_HILLS, BiomeKeys.ICE_SPIKES, BiomeKeys.MODIFIED_JUNGLE, BiomeKeys.MODIFIED_JUNGLE_EDGE, BiomeKeys.TALL_BIRCH_FOREST, BiomeKeys.TALL_BIRCH_HILLS, BiomeKeys.DARK_FOREST_HILLS, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.GIANT_SPRUCE_TAIGA, BiomeKeys.GIANT_SPRUCE_TAIGA_HILLS, BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS, BiomeKeys.SHATTERED_SAVANNA, BiomeKeys.SHATTERED_SAVANNA_PLATEAU, BiomeKeys.ERODED_BADLANDS, BiomeKeys.MODIFIED_WOODED_BADLANDS_PLATEAU, BiomeKeys.MODIFIED_BADLANDS_PLATEAU, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.BAMBOO_JUNGLE_HILLS);
        registerEntityToSpawn(WITCH_ENTITY, SpawnGroup.MONSTER, 4, 1, 1, BiomeKeys.PLAINS, BiomeKeys.DESERT, BiomeKeys.MOUNTAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.SWAMP, BiomeKeys.RIVER, BiomeKeys.SNOWY_TUNDRA, BiomeKeys.SNOWY_MOUNTAINS, BiomeKeys.BEACH, BiomeKeys.DESERT_HILLS, BiomeKeys.WOODED_HILLS, BiomeKeys.TAIGA_HILLS, BiomeKeys.MOUNTAIN_EDGE, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE_HILLS, BiomeKeys.JUNGLE_EDGE, BiomeKeys.DEEP_OCEAN, BiomeKeys.STONE_SHORE, BiomeKeys.SNOWY_BEACH, BiomeKeys.BIRCH_FOREST, BiomeKeys.BIRCH_FOREST_HILLS, BiomeKeys.DARK_FOREST, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_TAIGA_HILLS, BiomeKeys.GIANT_TREE_TAIGA, BiomeKeys.GIANT_TREE_TAIGA_HILLS, BiomeKeys.WOODED_MOUNTAINS, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.BADLANDS, BiomeKeys.WOODED_BADLANDS_PLATEAU, BiomeKeys.BADLANDS_PLATEAU, BiomeKeys.WARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_WARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.THE_VOID, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.DESERT_LAKES, BiomeKeys.GRAVELLY_MOUNTAINS, BiomeKeys.FLOWER_FOREST, BiomeKeys.TAIGA_MOUNTAINS, BiomeKeys.SWAMP_HILLS, BiomeKeys.ICE_SPIKES, BiomeKeys.MODIFIED_JUNGLE, BiomeKeys.MODIFIED_JUNGLE_EDGE, BiomeKeys.TALL_BIRCH_FOREST, BiomeKeys.TALL_BIRCH_HILLS, BiomeKeys.DARK_FOREST_HILLS, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.GIANT_SPRUCE_TAIGA, BiomeKeys.GIANT_SPRUCE_TAIGA_HILLS, BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS, BiomeKeys.SHATTERED_SAVANNA, BiomeKeys.SHATTERED_SAVANNA_PLATEAU, BiomeKeys.ERODED_BADLANDS, BiomeKeys.MODIFIED_WOODED_BADLANDS_PLATEAU, BiomeKeys.MODIFIED_BADLANDS_PLATEAU, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.BAMBOO_JUNGLE_HILLS);
        registerEntityToSpawn(BOAR_ENTITY, SpawnGroup.CREATURE, 20, 1, 3, BiomeKeys.SNOWY_TAIGA, BiomeKeys.TAIGA, BiomeKeys.GIANT_TREE_TAIGA, BiomeKeys.GIANT_SPRUCE_TAIGA, BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST);
        registerEntityToSpawn(BOAR_ENTITY, SpawnGroup.CREATURE, 75, 4, 6, BiomeKeys.SWAMP);

    }

    public void registerEntityToSpawn(EntityType<?> entityType, SpawnGroup classification, int amount, int min, int max, RegistryKey<Biome>... biomes ){
        for(RegistryKey<Biome> biomeKey : biomes){
            Biome biome = BuiltinRegistries.BIOME.get(biomeKey);
            if(biome == null) return;
            List<SpawnSettings.SpawnEntry> l = biome.getSpawnSettings().getSpawnEntry(classification);
            l.add(new SpawnSettings.SpawnEntry(entityType, amount, min, max));
            //biome.getEntitySpawnList(classification).add(new SpawnSettings.SpawnEntry(entityType, amount, min, max));
        }

    }


}