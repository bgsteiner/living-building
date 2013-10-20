package com.gmail.mooman219.build.blocks;

import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.BlockCactus;
import net.minecraft.server.StepSound;
import net.minecraft.server.World;

public class BCactus extends BlockCactus {

	private boolean canPlace = true;
	private boolean isFixed = true;
	private int maxHeight = 3;
	
	public BCactus(int i, int j, boolean can, boolean fixed, int max) {
		super(i, j);
		canPlace = can;
		isFixed = fixed;
		maxHeight = max;
	}
	
	@Override // isOpaqueCube - True for 'fixed'
	public boolean d() {
		return isFixed;
	}
	
    @Override // isACube - True for 'fixed'
    public boolean c() {
        return isFixed;
    }
	
	@Override
	public boolean canPlace(World world, int i, int j, int k) {
		return canPlace ? world.getTypeId(i, j, k) == 0 : !super.canPlace(world, i, j, k) ? false : this.d(world, i, j, k);
	}
	
    @Override // Max growth height! Like a boss
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
	
	@Override // CanBlockStay
	public boolean d(World world, int i, int j, int k) {
		if(canPlace){
			return true;
		} else if (world.getMaterial(i - 1, j, k).isBuildable()) {
            return false;
        } else if (world.getMaterial(i + 1, j, k).isBuildable()) {
            return false;
        } else if (world.getMaterial(i, j, k - 1).isBuildable()) {
            return false;
        } else if (world.getMaterial(i, j, k + 1).isBuildable()) {
            return false;
        } else {
            int l = world.getTypeId(i, j - 1, k);

            return ((l == Block.CACTUS.id) || (l == Block.SAND.id));
        }
	}
	
    public BCactus setHardness(float f) {
        this.strength = f;
        if (this.durability < f * 5.0F) {
            this.durability = f * 5.0F;
        }

        return this;
    }
    
    public BCactus setSound(StepSound stepsound) {
        this.stepSound = stepsound;
        return this;
    }

}
