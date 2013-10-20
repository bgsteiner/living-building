package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockFlower;
import net.minecraft.server.StepSound;
import net.minecraft.server.World;

public class SBFlower extends BlockFlower {

	public SBFlower(int i, int j) {
		super(i, j);
	}

	@Override
	public boolean canPlace(World world, int i, int j, int k) {
		//return world.getTypeId(i, j, k) == 0 && world.getTypeId(i, j - 1, k) != 0;
		return true;
	}

	@Override // canblockStay
	public boolean d(World world, int i, int j, int k) {
		return d_(world.getTypeId(i, j - 1, k));
	}


	@Override // canThisPlantGrowOnThisBlockID
	public boolean d_(int i) {
		return i != 0;
	}

	public SBFlower setHardness(float f) {
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
