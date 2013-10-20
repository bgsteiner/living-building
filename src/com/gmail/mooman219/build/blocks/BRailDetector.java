package com.gmail.mooman219.build.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityMinecart;
import net.minecraft.server.IBlockAccess;
import net.minecraft.server.World;

public class BRailDetector extends SBRail {

    public BRailDetector(int i, int j) {
        super(i, j, true);
        //Changed
        r[this.id] = true;
        //doubble check this
        this.b(true);
    }

    @Override
	public int p_() {
        return 20;
    }

    @Override
	public boolean isPowerSource() {
        return true;
    }

    @Override
	public void a(World world, int i, int j, int k, Entity entity) {
        if (!world.isStatic) {
            int l = world.getData(i, j, k);

            if ((l & 8) == 0) {
                this.f(world, i, j, k, l);
            }
        }
    }

    @Override
	public void b(World world, int i, int j, int k, Random random) {
        if (!world.isStatic) {
            int l = world.getData(i, j, k);

            if ((l & 8) != 0) {
                this.f(world, i, j, k, l);
            }
        }
    }

    @Override
	public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return (iblockaccess.getData(i, j, k) & 8) != 0;
    }

    @Override
	public boolean c(World world, int i, int j, int k, int l) {
        return (world.getData(i, j, k) & 8) == 0 ? false : l == 1;
    }

    private void f(World world, int i, int j, int k, int l) {
        boolean flag = (l & 8) != 0;
        boolean flag1 = false;
        float f = 0.125F;
        List list = world.a(EntityMinecart.class, AxisAlignedBB.a((i + f), j, (k + f), ((i + 1) - f), j + 0.25D, ((k + 1) - f)));

        if (list.size() > 0) {
            flag1 = true;
        }

        if (flag1 && !flag) {
            world.setData(i, j, k, l | 8);
            world.applyPhysics(i, j, k, this.id);
            world.applyPhysics(i, j - 1, k, this.id);
            world.d(i, j, k, i, j, k);
        }

        if (!flag1 && flag) {
            world.setData(i, j, k, l & 7);
            world.applyPhysics(i, j, k, this.id);
            world.applyPhysics(i, j - 1, k, this.id);
            world.d(i, j, k, i, j, k);
        }

        if (flag1) {
            world.a(i, j, k, this.id, this.p_());
        }
    }
}
