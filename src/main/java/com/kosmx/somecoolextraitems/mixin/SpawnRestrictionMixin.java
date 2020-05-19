package com.kosmx.somecoolextraitems.mixin;

import com.kosmx.somecoolextraitems.entity.AddEntities;
import com.kosmx.somecoolextraitems.entity.StoneZombieEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SpawnRestriction.class)
public class SpawnRestrictionMixin {

    @Shadow
    private static <T extends MobEntity> void register(EntityType<T> type, SpawnRestriction.Location location, Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate) {
        //SpawnRestriction.Entry entry = (SpawnRestriction.Entry)RESTRICTIONS.put(type, new SpawnRestriction.Entry(heightmapType, location, predicate));
        //if (entry != null) {
        //    throw new IllegalStateException("Duplicate registration for type " + Registry.ENTITY_TYPE.getId(type));
        //}
    }

    static {
        register(AddEntities.NETHER_ZOMBIE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
        register(AddEntities.BOAR_ENTITY, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
        register(AddEntities.WITCH_ENTITY, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
        register(AddEntities.STONE_ZOMBIE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, StoneZombieEntity::canSpawn);
    }
}
