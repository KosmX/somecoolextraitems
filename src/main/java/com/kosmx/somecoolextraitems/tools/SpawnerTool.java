package com.kosmx.somecoolextraitems.tools;

import java.util.function.Consumer;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class SpawnerTool extends ToolItem{

    public SpawnerTool(ToolMaterial material, Settings settings) {
        super(material, settings);
        //net.minecraft.item.Items
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack item = user.getStackInHand(hand);
        if(!world.isClient){
            this.summonMinions(user);
            user.world.syncWorldEvent(2004, user.getBlockPos(), 0);
            item.damage(1, (LivingEntity)user, (Consumer<LivingEntity>)((p) -> {p.sendToolBreakStatus(user.getActiveHand());}));

        }

        user.increaseStat(Stats.USED.getOrCreateStat(this), 1);

        return TypedActionResult.success(item);
    }

    protected void summonMinions(LivingEntity user){
        int counter = 0;
        for (int i = 0; i < this.maxTrials() && counter < this.amount(); i++) {
            if (this.trySpawnMinion(user)) {
                ++counter;
            }
        }
    }

    protected int maxTrials(){
        return 1024;
    }

    protected int amount(){
        return 4;
    }

    
    protected boolean trySpawnMinion(LivingEntity user){
        return false;
    }

}