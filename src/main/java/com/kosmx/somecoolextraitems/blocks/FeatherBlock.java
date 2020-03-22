package com.kosmx.somecoolextraitems.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FeatherBlock extends Block {
    public FeatherBlock(Settings settings) {
        super(settings);
    }

    public void onLandedUpon(World world, BlockPos pos, Entity entity, float distance) {
        entity.handleFallDamage(distance/4, 1f);
    }
}