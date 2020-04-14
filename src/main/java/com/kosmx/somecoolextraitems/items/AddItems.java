package com.kosmx.somecoolextraitems.items;

import com.kosmx.somecoolextraitems.Materials.*;
import com.kosmx.somecoolextraitems.tools.*;
import com.kosmx.somecoolextraitems.entity.AddEntities;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AddItems {
    public static final Item NetherGoldIngot = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item NetherGoldNugget = new Item(new Item.Settings().group(ItemGroup.MISC));
    //public static final Item DiamondIngot = new DiamondIngotItem(new Item.Settings());
    public static final Item NGoldPickaxe = new NetherGoldPickaxe(new ToolMaterialNethergold());
    public static final Item NGoldAxe = new NetherGoldAxe(new ToolMaterialNethergold());
    public static final Item NGoldSword = new NetherGoldSword(new ToolMaterialNethergold());
    public static final Item NGoldHoe = new NetherGoldHoe(new ToolMaterialNethergold());
    public static final Item NGoldShovel = new NetherGoldShovel(new ToolMaterialNethergold());
    public static final Item SkeletonRod = new SkeletonSpawner(new ToolMaterialMagic(), new Item.Settings().group(ItemGroup.MISC));
    public static final Item NetherZombieEgg = new SpawnEggItem(AddEntities.NETHER_ZOMBIE, 0xffaa33, 0x3d0303, new Item.Settings().group(ItemGroup.MISC));
    public static final Item StoneGolemEgg = new SpawnEggItem(AddEntities.STONE_ZOMBIE, 0xb3b3b3, 0x8a8a8a, new Item.Settings().group(ItemGroup.MISC));
    //public static final Item DerpFish = new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1F).build()));
    //net.minecraft.item.Items
    //foods
    //net.minecraft.recipe.CraftingRecipe
 
    //witch

    public static final Item Nutella = new NutellaItem(new Item.Settings().food(new FoodComponent.Builder().hunger(6).saturationModifier(6).alwaysEdible().build()).recipeRemainder(Items.GLASS_BOTTLE).group(ItemGroup.FOOD));
    public static final Item BreadWithNutella = new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(6).saturationModifier(6).snack().build()).group(ItemGroup.FOOD));
    public static final Item BreadWithHoney = new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(6).saturationModifier(6).snack().build()).group(ItemGroup.FOOD));
    public static final Item BPSMug = new Item(new Item.Settings().group(ItemGroup.FOOD));
    public static final Item Mug = new Item(new Item.Settings().group(ItemGroup.FOOD));
    public static final Item BPSMugTea = new MugItem(BPSMug, ItemGroup.FOOD);
    public static final Item MugTea = new MugItem(Mug, ItemGroup.FOOD);
    public static final Item StarwarsMug = new Item(new Item.Settings().group(ItemGroup.FOOD));
    public static final Item StarwarsMugTea = new MugItem(StarwarsMug, ItemGroup.FOOD);
    public static final Item Nut = new Item(new Item.Settings().group(ItemGroup.FOOD));
    public static final Item Frankfurter = new Item( new Item.Settings().food(new FoodComponent.Builder().hunger(4).saturationModifier(2).snack().build()).group(ItemGroup.FOOD));
    public static final Item TeaFilter = new Item(new Item.Settings().group(ItemGroup.MISC));
    //public static final Item CocoaBucket = new CocoaBucketItem(new Item.Settings().food(new FoodComponent.Builder().hunger(1).alwaysEdible().saturationModifier(0).snack().build()).recipeRemainder(Items.BUCKET).group(ItemGroup.FOOD));

    public AddItems(){
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "mug_bps"), BPSMug);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "mug_tea_bps"), BPSMugTea);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "mug"), Mug);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "mug_tea"), MugTea);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "mug_starwars"), StarwarsMug);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "tea_filter"), TeaFilter);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "mug_tea_starwars"), StarwarsMugTea);
        //Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "cocoa_bucket"), CocoaBucket);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "lava_zombie_spawn_egg"), NetherZombieEgg);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "stone_golem_spawn_egg"), StoneGolemEgg);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "boar_spawn_egg"), new SpawnEggItem(AddEntities.BOAR_ENTITY, 0x965227, 0x5c2e11, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "witch_spawn_egg"), new SpawnEggItem(AddEntities.WITCH_ENTITY, 0x3134cc, 0xff00f2, new Item.Settings().group(ItemGroup.MISC)));

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nut"), Nut);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "frankfurter"), Frankfurter);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_ingot"), NetherGoldIngot);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergold_nugget"), NetherGoldNugget);
        //Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "diamond_ingot"), DiamondIngot);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_pickaxe"), NGoldPickaxe);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_axe"), NGoldAxe);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_shovel"), NGoldShovel);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_sword"), NGoldSword);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_hoe"), NGoldHoe);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "skeleton_rod"), SkeletonRod);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nutolla"), Nutella);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "honey_bread"), BreadWithHoney);
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nutolla_bread"), BreadWithNutella);
        //Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "derp_fish"), DerpFish);

        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_helmet"), new ArmorItem(Armor.NetherGold, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_chestplate"), new ArmorItem(Armor.NetherGold, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_leggings"), new ArmorItem(Armor.NetherGold, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
        Registry.register(Registry.ITEM, new Identifier("somecoolextraitems", "nethergolden_boots"), new ArmorItem(Armor.NetherGold, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
    }
}