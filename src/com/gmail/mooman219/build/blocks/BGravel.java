package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.StepSound;

public class BGravel extends SBSand{

	public BGravel(int i, int j) {
		super(i, j);
	}

	@Override
    public SBSand setHardness(float f){
		this.strength = f;
		if (this.durability < f * 5.0F) {
			this.durability = f * 5.0F;
		}
		return this;
	}

	@Override
    public Block setSound(StepSound stepsound){
		this.stepSound = stepsound;
		return this;
	}
}
