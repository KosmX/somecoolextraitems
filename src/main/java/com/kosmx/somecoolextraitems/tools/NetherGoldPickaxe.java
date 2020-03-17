package com.kosmx.somecoolextraitems.tools;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;

public class NetherGoldPickaxe extends PickaxeItem {

    public NetherGoldPickaxe(ToolMaterial material) {
        super(material, 1, -2.8f, new Item.Settings().group(ItemGroup.TOOLS));
    }

}