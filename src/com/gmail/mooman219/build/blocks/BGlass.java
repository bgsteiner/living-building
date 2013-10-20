package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockGlass;
import net.minecraft.server.Material;
import net.minecraft.server.StepSound;

public class BGlass extends BlockGlass {

    public BGlass(int i, int j, Material material, boolean flag) {
        super(i, j, material, flag);
    }

    @Override // isOpaqueCube - True for 'fixed'
    public boolean d() {
        return true;
    }
    
    public BGlass setHardness(float f) {
        this.strength = f;
        if (this.durability < f * 5.0F) {
            this.durability = f * 5.0F;
        }

        return this;
    }
    
    public Block setSound(StepSound stepsound) {
        this.stepSound = stepsound;
        return this;
    }
}