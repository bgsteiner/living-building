package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockLightStone;
import net.minecraft.server.Material;
import net.minecraft.server.StepSound;

public class BLightstone extends BlockLightStone{

    public BLightstone(int i, int j, Material material){
        super(i, j, material);
    }

    public BLightstone setHardness(float f) {
        this.strength = f;
        if (this.durability < f * 5.0F) {
            this.durability = f * 5.0F;
        }

        return this;
    }

    public BLightstone setLightValue(float f) {
        lightEmission[this.id] = (int) (15.0F * f);
        return this;
    }

    public Block setSound(StepSound stepsound) {
        this.stepSound = stepsound;
        return this;
    }
}
