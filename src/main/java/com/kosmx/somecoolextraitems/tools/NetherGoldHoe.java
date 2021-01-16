package com.kosmx.somecoolextraitems.tools;

import net.minecraft.item.Item;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;

public class NetherGoldHoe extends HoeItem {

    public NetherGoldHoe(ToolMaterial material) {
        super(material, 2, 1, new Item.Settings().group(ItemGroup.TOOLS));
    }

}