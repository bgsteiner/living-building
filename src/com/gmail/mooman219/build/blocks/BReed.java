package com.gmail.mooman219.build.blocks;

import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.BlockReed;
import net.minecraft.server.Material;
import net.minecraft.server.StepSound;
import net.minecraft.server.World;

public class BReed extends BlockReed{
	
	// How tall can reeds grow! mofo
	private int maxHeight = 3;
	private boolean canPlaceAny = true;
	
	//Modified from "protected" to "public"
	public BReed(int i, int j, int max, boolean can) {
		super(i, j);
		// Mooman code
		maxHeight = max;
		canPlaceAny = can;
		//
		// Block.p()
		//doubble check this one
		this.cd = false;
		//
	}

	public BReed setHardness(float f) {
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

	//Growth Height
	@Override
	public void b(World world, int i, int j, int k, Random random) {
		if (world.isEmpty(i, j + 1, k)) {
			int l;

			for (l = 1; world.getTypeId(i, j - l, k) == this.id; ++l) {
				;
			}

			if (l < maxHeight) {
				int i1 = world.getData(i, j, k);

				if (i1 == 15) {
					world.setTypeId(i, j + 1, k, this.id);
					world.setData(i, j, k, 0);
				} else {
					world.setData(i, j, k, i1 + 1);
				}
			}
		}
	}

	@Override // This one is self explanatory
	public boolean canPlace(World world, int i, int j, int k) {
		if(canPlaceAny){ // MOOMANS Minecraft... protip: use this one
			return world.getTypeId(i, j, k) == 0 && world.getTypeId(i, j - 1, k) != 0;
		}else{ // Normal Minecraft
	        int l = world.getTypeId(i, j - 1, k);
	        return l == this.id ? true : (l != Block.GRASS.id && l != Block.DIRT.id && l != Block.SAND.id ? false : (world.getMaterial(i - 1, j - 1, k) == Material.WATER ? true : (world.getMaterial(i + 1, j - 1, k) == Material.WATER ? true : (world.getMaterial(i, j - 1, k - 1) == Material.WATER ? true : world.getMaterial(i, j - 1, k + 1) == Material.WATER))));
		}
	}

	@Override // Check out if the current placement is a valid one
	public boolean d(World world, int i, int j, int k) {
		if(canPlaceAny){
			return world.getTypeId(i, j - 1, k) == this.id ? true : world.getTypeId(i, j - 1, k) != 0;
		}else{
			return this.canPlace(world, i, j, k);
		}
	}
}
