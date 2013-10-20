package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockLeaves;
import net.minecraft.server.StepSound;

public class BLeaves extends BlockLeaves{
	
	public BLeaves(int i, int j) {
		super(i, j);
		//check this class below
		this.c= false;
	}

	@Override
	//doubble check this
	public boolean d() {
		return true;
	}

	public BLeaves setHardness(float f) {
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
