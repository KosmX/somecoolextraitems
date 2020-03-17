package com.kosmx.somecoolextraitems.Materials;

//import java.util.logging.Level;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import com.kosmx.somecoolextraitems.items.AddItems;

//import org.apache.logging.log4j.Level;


public class ToolMaterialNethergold implements ToolMaterial {
    @Override
    public int getDurability() {
        return 2200;
    }

    @Override
    public float getMiningSpeed() {
        //LOGGER.log(Level.INFO, "["+"somecoolextraitems"+"] " + "mining speed has been requested");
        return 10f;
    }

    @Override
    public float getAttackDamage() {
        return 4.0f;
    }

    @Override
    public int getMiningLevel() {
        //Main.log(Level.INFO, "mining level has been requested");
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 13;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(AddItems.NetherGoldIngot);
    }
    
}