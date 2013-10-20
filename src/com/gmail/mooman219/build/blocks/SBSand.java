package com.gmail.mooman219.build.blocks;

import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.BlockSand;
import net.minecraft.server.StepSound;
import net.minecraft.server.World;

// The Extra 'S' is there because BGravel extends this
public class SBSand extends BlockSand{

    public SBSand(int i, int j) {
        super(i, j);
    }

    @Override // This calls the fall method
    public void doPhysics(World world, int i, int j, int k, int l) {
      //DO NOTHING HURR DURR  
    }

    @Override // This calls the fall method
    public void b(World world, int i, int j, int k, Random random) {
      //DO NOTHING HURR DURR  
    }

    public SBSand setHardness(float f){
        this.strength = f;
        if (this.durability < f * 5.0F) {
            this.durability = f * 5.0F;
        }
        return this;
    }

    public Block setSound(StepSound stepsound){
        this.stepSound = stepsound;
        return this;
    }

}
