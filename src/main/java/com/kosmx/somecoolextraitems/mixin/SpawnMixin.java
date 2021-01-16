package com.kosmx.somecoolextraitems.mixin;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mixin(SpawnSettings.Builder.class)
public class SpawnMixin {


    @Shadow
    private float creatureSpawnProbability;

    @Shadow @Final private Map<SpawnGroup, List<SpawnSettings.SpawnEntry>> spawners;

    @Shadow @Final private Map<EntityType<?>, SpawnSettings.SpawnDensity> spawnCosts;

    @Shadow private boolean playerSpawnFriendly;

    @Inject(method = "build", at = @At(value = "HEAD"), cancellable = true)
    private void build(CallbackInfoReturnable<SpawnSettings> callbackInfo) {
        new SpawnSettings(this.creatureSpawnProbability, (Map)this.spawners.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, (entry) -> {
            return ImmutableList.copyOf((Collection)entry.getValue());
        })), ImmutableMap.copyOf(this.spawnCosts), this.playerSpawnFriendly);
    }
}
