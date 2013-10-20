package com.gmail.mooman219.build.blocks;

import com.gmail.mooman219.build.notch.MinecartTrackLogic;

import net.minecraft.server.Block;
import net.minecraft.server.BlockMinecartTrack;
import net.minecraft.server.StepSound;
import net.minecraft.server.World;

public class SBRail extends BlockMinecartTrack{

	public boolean a;

	public SBRail(int i, int j, boolean flag) {
		super(i, j, flag);
		r[this.id] = true;
		this.a = flag;
		this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}

	public SBRail setHardness(float f) {
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
	public boolean canPlace(World world, int i, int j, int k) {
		return world.getTypeId(i, j, k) == 0;
	}

	@Override
	public void doPhysics(World world, int i, int j, int k, int l) {
		if (!world.isStatic) {
			int i1 = world.getData(i, j, k);
			int j1 = i1;

			if (this.a) {
				j1 = i1 & 7;
			}

			if (this.id == Block.GOLDEN_RAIL.id) {
				boolean flag1 = world.isBlockIndirectlyPowered(i, j, k) || world.isBlockIndirectlyPowered(i, j + 1, k);

				flag1 = flag1 || this.a(world, i, j, k, i1, true, 0) || this.a(world, i, j, k, i1, false, 0);
				boolean flag2 = false;

				if (flag1 && (i1 & 8) == 0) {
					world.setData(i, j, k, j1 | 8);
					flag2 = true;
				} else if (!flag1 && (i1 & 8) != 0) {
					world.setData(i, j, k, j1);
					flag2 = true;
				}

				if (flag2) {
					world.applyPhysics(i, j - 1, k, this.id);
					if (j1 == 2 || j1 == 3 || j1 == 4 || j1 == 5) {
						world.applyPhysics(i, j + 1, k, this.id);
					}
				}
			} else if (l > 0 && Block.byId[l].isPowerSource() && !this.a && MinecartTrackLogic.a(new MinecartTrackLogic(this, world, i, j, k)) == 3) {
				this.a(world, i, j, k, false);
			}
		}
	}
	
	// Start Normal

	public void a(World world, int i, int j, int k, boolean flag) {
		if (!world.isStatic) {
			(new MinecartTrackLogic(this, world, i, j, k)).a(world.isBlockIndirectlyPowered(i, j, k), flag);
		}
	}

	public boolean a(World world, int i, int j, int k, int l, boolean flag, int i1) {
		if (i1 >= 8) {
			return false;
		} else {
			int j1 = l & 7;
			boolean flag1 = true;

			switch (j1) {
			case 0:
				if (flag) {
					++k;
				} else {
					--k;
				}
				break;

			case 1:
				if (flag) {
					--i;
				} else {
					++i;
				}
				break;

			case 2:
				if (flag) {
					--i;
				} else {
					++i;
					++j;
					flag1 = false;
				}

				j1 = 1;
				break;

			case 3:
				if (flag) {
					--i;
					++j;
					flag1 = false;
				} else {
					++i;
				}

				j1 = 1;
				break;

			case 4:
				if (flag) {
					++k;
				} else {
					--k;
					++j;
					flag1 = false;
				}

				j1 = 0;
				break;

			case 5:
				if (flag) {
					++k;
					++j;
					flag1 = false;
				} else {
					--k;
				}

				j1 = 0;
			}

			return this.a(world, i, j, k, flag, i1, j1) ? true : flag1 && this.a(world, i, j - 1, k, flag, i1, j1);
		}
	}

	private boolean a(World world, int i, int j, int k, boolean flag, int l, int i1) {
		int j1 = world.getTypeId(i, j, k);

		if (j1 == Block.GOLDEN_RAIL.id) {
			int k1 = world.getData(i, j, k);
			int l1 = k1 & 7;

			if (i1 == 1 && (l1 == 0 || l1 == 4 || l1 == 5)) {
				return false;
			}

			if (i1 == 0 && (l1 == 1 || l1 == 2 || l1 == 3)) {
				return false;
			}

			if ((k1 & 8) != 0) {
				if (!world.isBlockIndirectlyPowered(i, j, k) && !world.isBlockIndirectlyPowered(i, j + 1, k)) {
					return this.a(world, i, j, k, k1, flag, l + 1);
				}

				return true;
			}
		}

		return false;
	}
}
