package com.kosmx.somecoolextraitems.Materials;

import net.minecraft.item.Items;

//import java.util.logging.Level;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;


//import org.apache.logging.log4j.Level;


public class ToolMaterialMagic implements ToolMaterial {
    @Override
    public int getDurability() {
        return 8;
    }

    @Override
    public float getMiningSpeed() {
        //LOGGER.log(Level.INFO, "["+"somecoolextraitems"+"] " + "mining speed has been requested");
        return 1f;
    }

    @Override
    public float getAttackDamage() {
        return 2.0f;
    }

    @Override
    public int getMiningLevel() {
        //Main.log(Level.INFO, "mining level has been requested");
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 13;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.TOTEM_OF_UNDYING);
    }
    
}