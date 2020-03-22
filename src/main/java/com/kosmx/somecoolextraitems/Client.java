package com.kosmx.somecoolextraitems;

import net.fabricmc.api.ClientModInitializer;

import com.kosmx.somecoolextraitems.entity.AddClientEntity;

public class Client implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        new AddClientEntity();
    }
    
}