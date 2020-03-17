package com.kosmx.somecoolextraitems.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.List;

public class DiamondIngotItem extends Item{

    public DiamondIngotItem(Settings settings) {
        super(settings);
    }
        
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> list, TooltipContext tooltipContext) {
        list.add(new TranslatableText("diamond_ingot_text"));
    }
}