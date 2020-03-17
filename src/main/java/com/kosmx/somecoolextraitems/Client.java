package com.kosmx.somecoolextraitems;

import net.fabricmc.api.ClientModInitializer;

import com.kosmx.somecoolextraitems.entity.AddEntities;

public class Client implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        AddEntities.addClient();
    }
    
}