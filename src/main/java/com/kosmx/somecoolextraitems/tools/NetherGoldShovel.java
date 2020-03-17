package com.kosmx.somecoolextraitems.tools;

import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;

public class NetherGoldShovel extends ShovelItem {

    public NetherGoldShovel(ToolMaterial material) {
        super(material, 1.5f, -3f, new Item.Settings().group(ItemGroup.TOOLS));
    }

}