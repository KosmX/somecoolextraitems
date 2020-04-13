package com.kosmx.somecoolextraitems;

import net.fabricmc.api.ClientModInitializer;

import com.kosmx.somecoolextraitems.entity.renderer.AddClientEntity;

public class Client implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        new AddClientEntity();
    }
    
}