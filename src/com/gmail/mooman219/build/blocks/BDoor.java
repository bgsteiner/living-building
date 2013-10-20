package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockDoor;
import net.minecraft.server.Material;
import net.minecraft.server.StepSound;
import net.minecraft.server.World;

public class BDoor extends BlockDoor{
	public BDoor(int i, Material material) {
		super(i, material);
		r[this.id] = true;
		//check this class after this.
		this.cd = false;
	}

	public BDoor setHardness(float f) {
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

	@Override
	public void doPhysics(World world, int i, int j, int k, int l) {
		int i1 = world.getData(i, j, k);

		if ((i1 & 8) != 0) {
			if (world.getTypeId(i, j - 1, k) != this.id) {
				world.setTypeId(i, j, k, 0);
				return;
			}else if (l > 0 && Block.byId[l].isPowerSource()) {
				this.doPhysics(world, i, j - 1, k, l);
			}
		} else {

			if (l > 0 && Block.byId[l].isPowerSource()) {
				boolean flag1 = world.isBlockIndirectlyPowered(i, j, k) || world.isBlockIndirectlyPowered(i, j + 1, k);

				this.setDoor(world, i, j, k, flag1);
			}
		}
	}
}
