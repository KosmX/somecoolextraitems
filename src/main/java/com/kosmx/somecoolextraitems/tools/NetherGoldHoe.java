package com.kosmx.somecoolextraitems.tools;

import net.minecraft.item.Item;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;

public class NetherGoldHoe extends HoeItem {

    public NetherGoldHoe(ToolMaterial material) {
        super(material, 1f, new Item.Settings().group(ItemGroup.TOOLS));
    }

}