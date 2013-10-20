package com.gmail.mooman219.build.blocks;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.util.Vector;

import net.minecraft.server.Block;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.StatisticList;
import net.minecraft.server.StepSound;
import net.minecraft.server.World;

public class SBLongGrass extends SBFlower {
	// Moo
	private boolean isWild = false;
	private int maxHeight = 2;
	private HashMap<Vector, Byte> grass = new HashMap<Vector, Byte>();
	//
	public SBLongGrass(int i, int j, boolean wild, int max) {
		super(i, j);
		float f = 0.4F;

		this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
		
		// Moo
		isWild = wild;
		maxHeight = max;
		//
	}


    @Override
    public int a(int i, int j) {
        return j == 1 ? this.textureId : (j == 2 ? this.textureId + 16 + 1 : (j == 0 ? this.textureId + 16 : this.textureId));
    }

    @Override
    public int getDropType(int i, Random random, int j) {
        return random.nextInt(8) == 0 ? Item.SEEDS.id : -1;
    }

    @Override
    public int getDropCount(int i, Random random) {
        return (1 + random.nextInt(i * 2 + 1));
    }

    @Override
    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l) {
    	//Check entityhuman.S
        if (!world.isStatic && entityhuman.bC() != null && entityhuman.bC().id == Item.SHEARS.id) {
            entityhuman.a(StatisticList.C[this.id], 1);
            this.a(world, i, j, k, new ItemStack(Block.LONG_GRASS, 1, l));
        } else {
            super.a(world, entityhuman, i, j, k, l);
        }
    }
	
	// Moooooo
	@Override
	public boolean canPlace(World world, int i, int j, int k) {
		return world.getTypeId(i, j, k) == 0 ? world.getTypeId(i, j - 1, k) != 0 : false;
	}

	@Override //updateTick
	public void b(World world, int i, int j, int k, Random random) {
		if(!isWild) return;
		if (world.isEmpty(i, j + 1, k)) {
			int l;

			for (l = 1; world.getTypeId(i, j - l, k) == this.id; ++l) {}
			if(l >= maxHeight)
				return;
			
			byte growth;
			Vector grassID = new Vector(i,j,k); 
			if(!grass.containsKey(grassID))
				growth = 0;
			else
				growth = grass.get(grassID);

			if (growth >= 16) {
				world.setTypeId(i, j + 1, k, this.id);
				world.setData(i, j + 1, k, 1);
				world.setData(i, j, k, 1);
				growth = 0;
				grass.put(grassID, (byte) 0);
				if(l == maxHeight - 1){
					int x = i + random.nextInt(7) - 3;
					int z = k + random.nextInt(7) - 3;
					int y = j;
					if(world.getTypeId(x, y, z) != 0 || world.getTypeId(x, y - 1, z) == 0){
						y = y - 1;
						if(world.getTypeId(x, y, z) != 0 || world.getTypeId(x, y - 1, z) == 0)
							return;
					}
					grass.remove(grassID);
					world.setTypeId(x, y, z, this.id);
					world.setData(x, y, z, 1);
				}
			} else {
				growth++;
				grass.put(grassID, growth);
			}
		}
	}

	@Override
    public SBLongGrass setHardness(float f) {
		this.strength = f;
		if (this.durability < f * 5.0F) {
			this.durability = f * 5.0F;
		}

		return this;
	}

	@Override
    public Block setSound(StepSound stepsound) {
		this.stepSound = stepsound;
		return this;
	}
	//
}
