package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockFence;
import net.minecraft.server.StepSound;
import net.minecraft.server.World;

public class BFence extends BlockFence {

    public BFence(int i, int j) {
        super(i, j);
    }

    @Override // isOpaqueCube - True for 'fixed'
    public boolean d() {
        return true;
    }
    
    //check this class name
    @Override // isACube - True for 'fixed'
    public boolean c() {
        return true;
    }
    
    @Override
    public boolean canPlace(World world, int i, int j, int k) {
        return world.getTypeId(i, j, k) == 0 ? true : world.getTypeId(i, j, k) >= 8 && world.getTypeId(i, j, k) <= 12 ? true : false;
    }
    
    public BFence setHardness(float f) {
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
    
    public BFence setResistance(float f) {
    	this.durability = f * 3.0F;
        return this;
    }
}