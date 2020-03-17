package com.kosmx.somecoolextraitems.tools;

import net.minecraft.item.Item;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;

public class NetherGoldAxe extends AxeItem {

    public NetherGoldAxe(ToolMaterial material) {
        super(material, 4.1f, -2.8f, new Item.Settings().group(ItemGroup.TOOLS));
    }

}