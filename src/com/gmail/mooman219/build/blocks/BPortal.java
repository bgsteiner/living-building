package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockPortal;
import net.minecraft.server.StepSound;
import net.minecraft.server.World;

public class BPortal extends BlockPortal{
    public BPortal(int i, int j) {
		super(i, j);
	}
    
	public BPortal setHardness(float f) {
		this.strength = f;
		if (this.durability < f * 5.0F) {
			this.durability = f * 5.0F;
		}

		return this;
	}
	
	public BPortal setLightValue(float f) {
	    lightEmission[this.id] = (int) (15.0F * f);
        return this;
    }

	public Block setSound(StepSound stepsound) {
		this.stepSound = stepsound;
		return this;
	}

	@Override
	public void doPhysics(World world, int i, int j, int k, int l) {
		// Nothin to see here
    }
}
