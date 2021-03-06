package com.kosmx.somecoolextraitems.blocks;

import com.kosmx.somecoolextraitems.tools.toolTags;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
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
    public static final Block SugarBlock = new Block(FabricBlockSettings.of(Material.ORGANIC).strength(0.5f, 0.1f).sounds(BlockSoundGroup.SAND).build());


    public static final Block Pizza = new PizzaBlock(FabricBlockSettings.of(Material.CAKE).strength(0.5f, 0f).sounds(BlockSoundGroup.WOOL).nonOpaque().build());



    public AddBlocks(){
        //net.minecraft.block.Blocks

        Registry.register(Registry.BLOCK, new Identifier("somecoolextraitems", "nethergold_ore"), NetherGoldOre);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_ore"), new BlockItem(NetherGoldOre, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("somecoolextraitems", "nethergold_block"), NetherGoldBlock);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_block"), new BlockItem(NetherGoldBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("somecoolextraitems", "sugar_cube"), SugarBlock);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "sugar_cube"), new BlockItem(SugarBlock, new Item.Settings().group(ItemGroup.DECORATIONS)));


        Registry.register(Registry.BLOCK, new Identifier("somecoolextraitems", "pizza"), Pizza);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "pizza"), new BlockItem(Pizza, new Item.Settings().maxCount(1).group(ItemGroup.FOOD)));
        
        Registry.BIOME.forEach(this::netherGoldGeneration);
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, dentifier, biome) -> netherGoldGeneration(biome));


        AddPillow("pillow_white", DyeColor.WHITE);
        AddPillow("pillow_red", DyeColor.RED);
        AddPillow("pillow_blue", DyeColor.BLUE);
        AddPillow("pillow_green", DyeColor.GREEN);
        AddPillow("pillow_brown", DyeColor.BROWN);
    }
    private void netherGoldGeneration(Biome biome){
        if(biome.getCategory() == Biome.Category.NETHER){
            biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(
                new OreFeatureConfig(
                    OreFeatureConfig.Target.NETHERRACK, 
                    NetherGoldOre.getDefaultState(), 
                    6)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(
                        new RangeDecoratorConfig(1, 11, 0, 127)
                    ))
            );
        }
    }

    private void AddPillow(String name, DyeColor color){
        Block pillow = new FeatherBlock(FabricBlockSettings.of(Material.WOOL).materialColor(color).strength(0.5f, 0f).sounds(BlockSoundGroup.WOOL).nonOpaque().build());
        Registry.register(Registry.BLOCK, new Identifier("somecoolextraitems", name), pillow);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", name), new BlockItem(pillow, new Item.Settings().group(ItemGroup.DECORATIONS)));
    }

}