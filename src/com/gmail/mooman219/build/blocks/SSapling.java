package com.gmail.mooman219.build.blocks;

import java.util.Random;

import net.minecraft.server.World;
import net.minecraft.server.WorldGenBigTree;
import net.minecraft.server.WorldGenForest;
import net.minecraft.server.WorldGenTaiga2;
import net.minecraft.server.WorldGenTrees;
import net.minecraft.server.WorldGenerator;

public class SSapling extends SBFlower {
	// To update this, just copy and paste the BlockSapling class

	public SSapling(int i, int j) {
		super(i, j);
		float f = 0.4F;
		this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		// Block.g()
		//Changed
		r[this.id] = true;
		//
	}

	@Override
	public void b(World world, int i, int j, int k, Random random) {
		if (!world.isStatic) {
			super.b(world, i, j, k, random);
			if (world.getLightLevel(i, j + 1, k) >= 9 && random.nextInt(30) == 0) {
				int l = world.getData(i, j, k);

				if ((l & 8) == 0) {
					world.setData(i, j, k, l | 8);
				} else {
					this.b(world, i, j, k, random);
				}
			}
		}
	}

	@Override
	public int a(int i, int j) {
		j &= 3;
		return j == 1 ? 63 : (j == 2 ? 79 : super.a(i, j));
	}

	public void c(World world, int i, int j, int k, Random random) {
		int l = world.getData(i, j, k) & 3;

		world.setRawTypeId(i, j, k, 0);
		Object object = null;

		if (l == 1) {
			object = new WorldGenTaiga2(true);
		} else if (l == 2) {
			object = new WorldGenForest(true);
		} else {
			object = new WorldGenTrees(true);
			if (random.nextInt(10) == 0) {
				object = new WorldGenBigTree(true);
			}
		}

		if (!((WorldGenerator) object).a(world, random, i, j, k)) {
			world.setRawTypeIdAndData(i, j, k, this.id, l);
		}
	}

	public int b(int i) {
		return i & 3;
	}
}