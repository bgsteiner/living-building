package com.gmail.mooman219.build.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockTrapdoor;
import net.minecraft.server.Material;
import net.minecraft.server.StepSound;
import net.minecraft.server.World;

public class BTrapDoor extends BlockTrapdoor{

	public BTrapDoor(int i, Material material) {
		super(i, material);
		//doubble check this
		this.cd = false;
		//Changed
		r[this.id] = true;
	}

    @Override
	public void doPhysics(World world, int i, int j, int k, int l) {
        if (!world.isStatic) {
            if (l == 0 || l > 0 && Block.byId[l] != null && Block.byId[l].isPowerSource()) {
                this.setOpen(world, i, j, k, world.isBlockIndirectlyPowered(i, j, k));
            }
        }
    }
    
    @Override
	public boolean canPlace(World world, int i, int j, int k, int l) {
    	return world.getTypeId(i, j, k) == 0 ? true : world.getTypeId(i, j, k) >= 8 && world.getTypeId(i, j, k) <= 12 ? true : false;
    }
    
	public BTrapDoor setHardness(float f) {
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
