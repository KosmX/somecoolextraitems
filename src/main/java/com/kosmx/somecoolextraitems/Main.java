package com.kosmx.somecoolextraitems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;

//import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

//import com.kosmx.somecoolextraitems.NetherGold;
import com.kosmx.somecoolextraitems.Materials.*;
import com.kosmx.somecoolextraitems.entity.AddEntities;
import com.kosmx.somecoolextraitems.tools.*;
//import com.mojang.datafixers.types.templates.Tag;
import com.kosmx.somecoolextraitems.items.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "somecoolextraitems";
    public static final String MOD_NAME = "somecoolextraitems";

    public static final Item NetherGoldIngot = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item NetherGoldNugget = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item DiamondIngot = new DiamondIngotItem(new Item.Settings());
    public static final Item NGoldPickaxe = new NetherGoldPickaxe(new ToolMaterialNethergold());

    //public static final Item NetherGoldHelmet = new ArmorItem(Armor.NetherGold, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    //Tag


    public static final Block NetherGoldOre = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(toolTags.PICKAXES, 2).strength(3, 0.4f).build());
    public static final Block NetherGoldBlock = new Block(FabricBlockSettings.of(Material.METAL).breakByHand(false).breakByTool(toolTags.PICKAXES, 2).strength(3, 0.4f).build());


    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");

        //net.minecraft.item.Items
        //net.minecraft.item.ItemStack

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_ingot"), NetherGoldIngot);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_nugget"), NetherGoldNugget);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "diamond_ingot"), DiamondIngot);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_pickaxe"), NGoldPickaxe);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_axe"), new NetherGoldAxe(new ToolMaterialNethergold()));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_shovel"), new NetherGoldShovel(new ToolMaterialNethergold()));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_sword"), new NetherGoldSword(new ToolMaterialNethergold()));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_hoe"), new NetherGoldHoe(new ToolMaterialNethergold()));

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_helmet"), new ArmorItem(Armor.NetherGold, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_chestplate"), new ArmorItem(Armor.NetherGold, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_leggings"), new ArmorItem(Armor.NetherGold, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_boots"), new ArmorItem(Armor.NetherGold, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

        Registry.register(Registry.BLOCK, new Identifier("somecoolextraitems", "nethergold_ore"), NetherGoldOre);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_ore"), new BlockItem(NetherGoldOre, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("somecoolextraitems", "nethergold_block"), NetherGoldBlock);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_block"), new BlockItem(NetherGoldBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.BIOME.forEach(this::netherGoldGeneration);

        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, dentifier, biome) -> netherGoldGeneration(biome));
        new AddEntities();
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    private void netherGoldGeneration(Biome biome){
        if(biome.getCategory() == Biome.Category.NETHER){
            biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(
                new OreFeatureConfig(
                    OreFeatureConfig.Target.NETHERRACK, 
                    NetherGoldOre.getDefaultState(), 
                    8)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(
                        new RangeDecoratorConfig(1, 3, 0, 127)
                    ))
            );
        }
    }
    


}