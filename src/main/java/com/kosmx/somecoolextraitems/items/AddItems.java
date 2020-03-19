package com.kosmx.somecoolextraitems.items;

import com.kosmx.somecoolextraitems.Materials.*;
import com.kosmx.somecoolextraitems.tools.*;
import com.kosmx.somecoolextraitems.entity.AddEntities;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AddItems {
    public static final Item NetherGoldIngot = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item NetherGoldNugget = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item DiamondIngot = new DiamondIngotItem(new Item.Settings());
    public static final Item NGoldPickaxe = new NetherGoldPickaxe(new ToolMaterialNethergold());
    public static final Item NGoldAxe = new NetherGoldAxe(new ToolMaterialNethergold());
    public static final Item NGoldSword = new NetherGoldSword(new ToolMaterialNethergold());
    public static final Item NGoldHoe = new NetherGoldHoe(new ToolMaterialNethergold());
    public static final Item NGoldShovel = new NetherGoldShovel(new ToolMaterialNethergold());
    public static final Item NetherZombieEgg = new SpawnEggItem(AddEntities.NETHER_ZOMBIE, 0xffaa33, 0x3d0303, new Item.Settings().group(ItemGroup.MISC));
    //net.minecraft.item.Items
    //foods

    //todo BPS cup easter egg

    public static final Item Nutella = new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(6).saturationModifier(6).alwaysEdible().build()).group(ItemGroup.FOOD));
    public static final Item BreadWithNutella = new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(6).saturationModifier(6).snack().build()).group(ItemGroup.FOOD));
    public static final Item BreadWithHoney = new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(6).saturationModifier(6).snack().build()).group(ItemGroup.FOOD));
    public static final Item BPSMug = new Item(new Item.Settings());
    public static final Item BPSMugTee = new MugItem(BPSMug, ItemGroup.FOOD);


    public AddItems(){
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "mug_bps"), BPSMug);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "mug_tee_bps"), BPSMugTee);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "lava_zombie_spawn_egg"), NetherZombieEgg);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_ingot"), NetherGoldIngot);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_nugget"), NetherGoldNugget);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "diamond_ingot"), DiamondIngot);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_pickaxe"), NGoldPickaxe);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_axe"), NGoldAxe);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_shovel"), NGoldShovel);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_sword"), NGoldSword);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_hoe"), NGoldHoe);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nutella"), Nutella);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "honey_bread"), BreadWithHoney);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nutella_bread"), BreadWithNutella);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_helmet"), new ArmorItem(Armor.NetherGold, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_chestplate"), new ArmorItem(Armor.NetherGold, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_leggings"), new ArmorItem(Armor.NetherGold, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_boots"), new ArmorItem(Armor.NetherGold, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
    }
}