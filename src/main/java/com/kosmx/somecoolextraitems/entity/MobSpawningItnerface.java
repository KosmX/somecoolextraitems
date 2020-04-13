package com.kosmx.somecoolextraitems.entity;

public interface MobSpawningItnerface {
    default void summonMinions() {
        int counter = 0;
        for (int i = 0; i < this.maxTrials() && counter < this.amount(); i++) {
            if (this.trySpawnMinion()) {
                ++counter;
            }
        }
    }

    int amount();

    int maxTrials();

    boolean trySpawnMinion();
}