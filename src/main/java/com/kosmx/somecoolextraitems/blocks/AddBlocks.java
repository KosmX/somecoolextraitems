package com.kosmx.somecoolextraitems.blocks;

import com.kosmx.somecoolextraitems.tools.toolTags;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class AddBlocks {
    public static final Block NetherGoldOre = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(toolTags.PICKAXES, 2).strength(3, 0.4f).build());
    public static final Block NetherGoldBlock = new Block(FabricBlockSettings.of(Material.METAL).breakByHand(false).breakByTool(toolTags.PICKAXES, 2).strength(3, 0.4f).build());





    public AddBlocks(){
        
        Registry.register(Registry.BLOCK, new Identifier("somecoolextraitems", "nethergold_ore"), NetherGoldOre);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_ore"), new BlockItem(NetherGoldOre, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("somecoolextraitems", "nethergold_block"), NetherGoldBlock);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_block"), new BlockItem(NetherGoldBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        
        Registry.BIOME.forEach(this::netherGoldGeneration);
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, dentifier, biome) -> netherGoldGeneration(biome));
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