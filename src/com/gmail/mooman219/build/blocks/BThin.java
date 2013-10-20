package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockThinFence;
import net.minecraft.server.Material;
import net.minecraft.server.StepSound;

public class BThin extends BlockThinFence{

    public BThin(int i, int j, int k, Material material, boolean flag) {
        super(i, j, k, material, flag);
        
    }
    
    @Override
    //doubble check
    public boolean c() {
        return true;
    }

    @Override
    //doubble check
    public boolean d() {
        return true;
    }
    
    public BThin setHardness(float f) {
        this.strength = f;
        if (this.durability < f * 5.0F) {
            this.durability = f * 5.0F;
        }

        return this;
    }
    
    public BThin setResistance(float f) {
        this.durability = f * 3.0F;
        return this;
    }
    
    public Block setSound(StepSound stepsound) {
        this.stepSound = stepsound;
        return this;
    }

}
