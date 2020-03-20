package com.kosmx.somecoolextraitems;

import net.fabricmc.api.ModInitializer;

//import net.fabricmc.fabric.api.block.FabricBlockSettings;


//import com.kosmx.somecoolextraitems.NetherGold;
import com.kosmx.somecoolextraitems.entity.AddEntities;
import com.kosmx.somecoolextraitems.items.AddItems;
import com.kosmx.somecoolextraitems.blocks.AddBlocks;
//import com.mojang.datafixers.types.templates.Tag;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "somecoolextraitems";
    public static final String MOD_NAME = "somecoolextraitems";


    //public static final Item NetherGoldHelmet = new ArmorItem(Armor.NetherGold, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    //Tag


    
    
    //net.minecraft.potion.Potions



    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");

        //net.minecraft.item.Items
        //net.minecraft.item.ItemStack
        new AddBlocks();
        new AddEntities();
        new AddItems();
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}