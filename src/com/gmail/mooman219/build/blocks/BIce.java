package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockIce;
import net.minecraft.server.StepSound;

public class BIce extends BlockIce{
	public BIce(int i, int j) {
		super(i, j);
	}

	@Override
	public boolean d(){ // isOpaqueCube - True for 'fixed'
		return true;
	}

	public BIce setHardness(float f){
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
