package com.kosmx.somecoolextraitems.tools;

import java.util.Random;

import com.kosmx.somecoolextraitems.entity.AddEntities;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class SkeletonSpawner extends SpawnerTool {

    public SkeletonSpawner(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        if(world.getLocalDifficulty(user.getBlockPos()).getLocalDifficulty() == 0f){
            return TypedActionResult.fail(user.getStackInHand(hand));
        }
        else{
            return super.use(world, user, hand);
        }
    }


    @Override
    protected boolean trySpawnMinion(LivingEntity user) {
        double x = user.getRandom().nextInt(9) + (int)user.getX() - 5.5;
        double y = user.getRandom().nextInt(9) + (int)user.getY() - 5.5;
        double z = user.getRandom().nextInt(9) + (int)user.getZ() - 5.5;
        boolean bl = SkeletonEntity.canSpawnIgnoreLightLevel(EntityType.SKELETON, user.world, SpawnReason.SPAWNER, new BlockPos((double)x, (double)y, (double)z), new Random()) && user.world.isAir(new BlockPos(x, y, z)) && !user.world.isAir(new BlockPos(x, y-1, z));
        //System.out.print("Spawn ");
        if (bl){
            //System.out.println("success");
            //for (int i = 0; i< 128; i++){
            //    user.world.addParticle(new DustParticleEffect(0.6f, 0.6f, 0.6f, 1f), true, x-0.5f + user.getRandom().nextFloat() , y - 0.5f + user.getRandom().nextFloat(), z - 0.5 + user.getRandom().nextFloat(), user.getRandom().nextFloat(), user.getRandom().nextFloat(), user.getRandom().nextFloat());
            //}
            SkeletonEntity skeleton = (SkeletonEntity)AddEntities.SKELETON_MINION.create(user.world);
            skeleton.resetPosition(x, y, z);
            skeleton.updatePosition(x, y, z);
            skeleton.initialize((ServerWorldAccess) user.world, user.world.getLocalDifficulty(new BlockPos(x, y, z)), SpawnReason.SPAWNER, (EntityData)null, (CompoundTag)null);
            if(user.getRandom().nextBoolean()){
                skeleton.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
            }
            else{
                skeleton.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            }
            user.world.spawnEntity(skeleton);
            user.world.syncWorldEvent(2004, new BlockPos(skeleton.getPos()), 0);
            skeleton.playSpawnEffects(); //TODO fix it somehow
        }
        //else{System.out.println("failed");}
        return bl;
    }

    
}