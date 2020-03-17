package com.kosmx.somecoolextraitems.tools;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;

public class NetherGoldSword extends SwordItem {

    public NetherGoldSword(ToolMaterial material) {
        super(material, 3, -2.4f, new Item.Settings().group(ItemGroup.COMBAT));
    }

}