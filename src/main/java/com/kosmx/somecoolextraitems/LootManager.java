package com.kosmx.somecoolextraitems;

import com.kosmx.somecoolextraitems.items.AddItems;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class LootManager{
    public LootManager(){
        AddMugs("chests/desert_pyramid", 1);
        AddMugs("chests/abandoned_mineshaft", 1);
        AddMugs("chests/buried_treasure", 1);
        AddMugs("chests/end_city_treasure", 1);
        AddMugs("chests/igloo_chest", 1);
        AddMugs("chests/jungle_temple", 1);
        AddMugs("chests/simple_dungeon", 1);
        AddMugs("chests/underwater_ruin_big", 1);
        AddMugs("chests/underwater_ruin_small", 1);
        AddMugs("chests/woodland_mansion", 1);
        AddMugs("chests/shipwreck_supply", 1);
        AddMugs("chests/shipwreck_treasure", 1);
    }


    private void AddLoot(String lootTable, Item item, int probability){
        Identifier identifier = new Identifier("minecraft", lootTable);
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (identifier.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                .withRolls(ConstantLootTableRange.create(probability))
                .withEntry(ItemEntry.builder(item));
                supplier.withPool(poolBuilder);
            }
        });
    }

    private void AddMugs(String lootTable, int chance){
        AddLoot(lootTable, AddItems.BPSMug, chance);
        AddLoot(lootTable, AddItems.StarwarsMug, chance);
        AddLoot(lootTable, AddItems.Mug, chance);
    }
}