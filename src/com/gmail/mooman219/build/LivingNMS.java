package com.gmail.mooman219.build;

import java.lang.reflect.Field;

import com.gmail.mooman219.build.blocks.BCactus;
import com.gmail.mooman219.build.blocks.BDoor;
import com.gmail.mooman219.build.blocks.BFence;
import com.gmail.mooman219.build.blocks.BGlass;
import com.gmail.mooman219.build.blocks.BGravel;
import com.gmail.mooman219.build.blocks.BIce;
import com.gmail.mooman219.build.blocks.BLeaves;
import com.gmail.mooman219.build.blocks.BLightstone;
import com.gmail.mooman219.build.blocks.BPortal;
import com.gmail.mooman219.build.blocks.BRailDetector;
import com.gmail.mooman219.build.blocks.BThin;
import com.gmail.mooman219.build.blocks.SBLongGrass;
import com.gmail.mooman219.build.blocks.SBRail;
import com.gmail.mooman219.build.blocks.BReed;
import com.gmail.mooman219.build.blocks.BTrapDoor;
import com.gmail.mooman219.build.blocks.SBFlower;
import com.gmail.mooman219.build.blocks.SBSand;
import com.gmail.mooman219.build.blocks.SSapling;

import net.minecraft.server.Block;
import net.minecraft.server.Material;

public class LivingNMS{

    private static boolean eWildGrass;
    private static boolean eCactus;
    private static boolean eSugarcane;
    private static boolean eFence;
    private static boolean eSand;
    private static boolean eGravel;
    private static boolean ePortal;
    private static boolean eHalfDoor;
    private static boolean eRail;
    private static boolean eRailPowered;
    private static boolean eRailDetector;
    private static boolean eTrapDoor;
    private static boolean eRedFlower;
    private static boolean eYellowFlower;
    private static boolean eSapling;
    private static boolean eIce;
    private static boolean eGlass;
    private static boolean eLeaves;
    private static boolean eIronFence;
    private static boolean eThinGlass;
    private static boolean eGlowstone;

    private boolean canTrapDoor;
    private boolean canCactus;
    private boolean canRedFlower;
    private boolean canYellowFlower;
    private boolean canGrass;
    private boolean canSapling;
    private boolean canSugarcane;
    private boolean canRail;
    private boolean canRailPowered;
    private boolean canRailDetector;
    private boolean canPortal;
    private boolean canDoor;

    private boolean wildGrass;
    private int wildGrassMaxHeight;
    private int sugarcaneMaxHeight;
    private int cactusMaxHeight;
    private boolean disableSandFall;
    private boolean disableGravelFall;

    private boolean fixIce;
    private boolean fixFence;
    private boolean fixCactus;
    private boolean fixLeaves;
    private boolean fixGlass;
    private boolean fixIronFence;
    private boolean fixThinGlass;
    private boolean fixGlowstone;

    public void start(){
        //  Startup Like a boss
        eWildGrass = LivingConfig.eWildGrass;
        eCactus = LivingConfig.eCactus;
        eSugarcane = LivingConfig.eSugarcane;
        eFence = LivingConfig.eFence;
        eSand = LivingConfig.eSand;
        eGravel = LivingConfig.eGravel;
        ePortal = LivingConfig.ePortal;
        eHalfDoor = LivingConfig.eHalfDoor;
        eRail = LivingConfig.eRail;
        eRailPowered = LivingConfig.eRailPowered;
        eRailDetector = LivingConfig.eRailDetector;
        eTrapDoor = LivingConfig.eTrapDoor;
        eRedFlower = LivingConfig.eRedFlower;
        eYellowFlower = LivingConfig.eYellowFlower;
        eSapling = LivingConfig.eSapling;
        eIce = LivingConfig.eIce;
        eGlass = LivingConfig.eGlass;
        eLeaves = LivingConfig.eLeaves;
        eIronFence = LivingConfig.eIronFence;
        eThinGlass = LivingConfig.eThinGlass;
        eGlowstone = LivingConfig.eGlowstone;

        canTrapDoor = LivingConfig.canTrapDoor;
        canCactus = LivingConfig.canCactus;
        canRedFlower = LivingConfig.canRedFlower;
        canYellowFlower = LivingConfig.canYellowFlower;
        canGrass = LivingConfig.canWildGrass;
        canSapling = LivingConfig.canSapling;
        canSugarcane = LivingConfig.canSugarcane;
        canRail = LivingConfig.canRail;
        canRailPowered = LivingConfig.canRailPowered;
        canRailDetector = LivingConfig.canRailDetector;
        canPortal = LivingConfig.canPortal;
        canDoor = LivingConfig.canHalfDoor;

        wildGrass = LivingConfig.wildGrass;
        wildGrassMaxHeight = LivingConfig.wildGrassMaxHeight;
        sugarcaneMaxHeight = LivingConfig.sugarcaneMaxHeight;
        cactusMaxHeight = LivingConfig.cactusMaxHeight;
        disableSandFall = LivingConfig.sandPhysics;
        disableGravelFall = LivingConfig.gravelPhysics;

        fixIce = LivingConfig.fixIce;
        fixFence = LivingConfig.fixFence;
        fixCactus = LivingConfig.fixCactus;
        fixLeaves = LivingConfig.fixLeaves;
        fixGlass = LivingConfig.fixGlass;
        fixIronFence = LivingConfig.fixIronFence;
        fixThinGlass = LivingConfig.fixThinGlass;
        fixGlowstone = LivingConfig.fixGlowstone;
        // 1.0.0
        // 
        //-Block Class
        //--Blanks
        //---.p()
        //     this.bS = false;
        //---.g()
        //     t[this.id] = true;
        //---.n()
        //     return this.name;
        //---.i()
        //     t[this.id] = true;
        //--Needed
        //---.a(StepSound x)
        //     {
        //       this.stepSound = stepsound;
        //       return this;
        //     }
        // Alt .setSound(Block.x)
        //---.c(float f)
        //     {
        //       this.strength = f;
        //       if (this.durability < f * 5.0F) {
        //           this.durability = f * 5.0F;
        //       }
        //       return this;
        //     }
        // Alt .setHardness(float f)
        //---.b(float f)
        //     {
        //      this.durability = f * 3.0F;
        //      return this;
        //     }
        // Alt .setResistance(f)
        //---.a(float f)
        //     {
        //      s[this.id] = (int) (15.0F * f);
        //      return this;
        //     }
        // Alt .setLightValue(float f)
        //--Unneeded
        //---.a(String s)
        //     {
        //       this.name = "tile." + s;
        //       return this;
        //     }
        // Alt .No Alt is needed. 
        //--Block class variables
        //---Light Value
        //     Block.lightBlock[Block.NAME.id] = 0;
        // 
        
        //.b is replacing .a
        
        if(eIronFence){
            if(fixIronFence){
                // 1.0.0
                // IRON_FENCE = (new BlockThin(101, 85, 85, Material.ORE, true)).c(5.0F).b(10.0F).a(i).a("fenceIron");
                // 1.1
                // IRON_FENCE = (new BlockThinFence(101, 85, 85, Material.ORE, true)).c(5.0F).b(10.0F).a(i).a("fenceIron");
                Block.byId[Block.IRON_FENCE.id] = null;
                Block.byId[Block.IRON_FENCE.id] = new BThin(Block.IRON_FENCE.id, 85, 85, Material.ORE, true).setHardness(5.0F).setResistance(10.0F).setSound(Block.i).b("fenceIron");
                Block.lightBlock[Block.IRON_FENCE.id] = 0;
            }
        }
        if(eThinGlass){
            if(fixThinGlass){
                // 1.0.0
                // THIN_GLASS = (new BlockThin(102, 49, 148, Material.SHATTERABLE, false)).c(0.3F).a(j).a("thinGlass");
                // 1.1
                // THIN_GLASS = (new BlockThinFence(102, 49, 148, Material.SHATTERABLE, false)).c(0.3F).a(j).a("thinGlass");
                Block.byId[Block.THIN_GLASS.id] = null;
                Block.byId[Block.THIN_GLASS.id] = new BThin(Block.THIN_GLASS.id, 49, 148, Material.SHATTERABLE, false).setHardness(0.3F).setSound(Block.j).b("thinGlass");
                Block.lightBlock[Block.THIN_GLASS.id] = 0;
            }
        }
        if(eHalfDoor){
            if(canDoor){
                // 1.0.0
                // WOODEN_DOOR = (new BlockDoor(64, Material.WOOD)).c(3.0F).a(e).a("doorWood").p().i();
                // IRON_DOOR_BLOCK = (new BlockDoor(71, Material.ORE)).c(5.0F).a(i).a("doorIron").p().i();
                // 1.1
                // WOODEN_DOOR = (new BlockDoor(64, Material.WOOD)).c(3.0F).a(e).a("doorWood").p().i();
                // IRON_DOOR_BLOCK = (new BlockDoor(71, Material.ORE)).c(5.0F).a(i).a("doorIron").p().i();
                Block.byId[Block.WOODEN_DOOR.id] = null;
                Block.byId[Block.IRON_DOOR_BLOCK.id] = null;
                //lines broken
                Block.byId[Block.WOODEN_DOOR.id] = new BDoor(Block.WOODEN_DOOR.id, Material.WOOD).setHardness(3.0F).setSound(Block.e).b("doorWood");
                Block.byId[Block.IRON_DOOR_BLOCK.id] = new BDoor(Block.IRON_DOOR_BLOCK.id, Material.ORE).setHardness(5.0F).setSound(Block.i).b("doorIron");
            }
        }
        if(ePortal){
            if(canPortal){
                // 1.0.0
                // BlockPortal PORTAL = (BlockPortal) (new BlockPortal(90, 14)).c(-1.0F).a(j).a(0.75F).a("portal");
                // 1.1
                // BlockPortal PORTAL = (BlockPortal) (new BlockPortal(90, 14)).c(-1.0F).a(j).a(0.75F).a("portal");
                Block.byId[Block.PORTAL.id] = null;
                Block.byId[Block.PORTAL.id] = new BPortal(Block.PORTAL.id, 14).setHardness(-1.0F).setLightValue(0.75F).setSound(Block.j).b("portal");
            }
        }
        if(eRailPowered){
            if(canRailPowered){
                // 1.0.0
                // GOLDEN_RAIL = (new BlockMinecartTrack(27, 179, true)).c(0.7F).a(i).a("goldenRail").i();
                // 1.1
                // GOLDEN_RAIL = (new BlockMinecartTrack(27, 179, true)).c(0.7F).a(i).a("goldenRail").i();
                Block.byId[Block.GOLDEN_RAIL.id] = null;
                Block.byId[Block.GOLDEN_RAIL.id] = new SBRail(Block.GOLDEN_RAIL.id, 179, true).setHardness(0.7F).setSound(Block.i).b("goldenRail");
            }
        }
        if(eRailDetector){
            if(canRailDetector){
                // 1.0.0
                // DETECTOR_RAIL = (new BlockMinecartDetector(28, 195)).c(0.7F).a(i).a("detectorRail").i();
                // 1.1
                // DETECTOR_RAIL = (new BlockMinecartDetector(28, 195)).c(0.7F).a(i).a("detectorRail").i();
                Block.byId[Block.DETECTOR_RAIL.id] = null;
                Block.byId[Block.DETECTOR_RAIL.id] = new BRailDetector(Block.DETECTOR_RAIL.id, 195).setHardness(0.7F).setSound(Block.i).b("detectorRail");
            }
        }
        if(eRail){
            if(canRail){
                // 1.0.0
                // RAILS = (new BlockMinecartTrack(66, 128, false)).c(0.7F).a(i).a("rail").i();
                // 1.1
                // RAILS = (new BlockMinecartTrack(66, 128, false)).c(0.7F).a(i).a("rail").i();
                Block.byId[Block.RAILS.id] = null;
                Block.byId[Block.RAILS.id] = new SBRail(Block.RAILS.id, 128, false).setHardness(0.7F).setSound(Block.i).b("rail");
            }
        }
        if(eSand){
            if(disableSandFall){
                // 1.0.0
                // SAND = (new BlockSand(12, 18)).c(0.5F).a(l).a("sand");
                // 1.1
                // SAND = (new BlockSand(12, 18)).c(0.5F).a(l).a("sand");
                Block.byId[Block.SAND.id] = null;
                Block.byId[Block.SAND.id] = new SBSand(Block.SAND.id, 18).setHardness(0.5F).setSound(Block.l).b("sand");
            }
        }
        if(eGravel){
            if(disableGravelFall){
                // 1.0.0
                // GRAVEL = (new BlockGravel(13, 19)).c(0.6F).a(f).a("gravel");
                // 1.1
                // GRAVEL = (new BlockGravel(13, 19)).c(0.6F).a(f).a("gravel");
                Block.byId[Block.GRAVEL.id] = null;
                Block.byId[Block.GRAVEL.id] = new BGravel(Block.GRAVEL.id, 19).setHardness(0.6F).setSound(Block.f).b("gravel");
            }
        }
        if(eSugarcane){
            if(canSugarcane || sugarcaneMaxHeight != 3){
                // 1.0.0
                // SUGAR_CANE_BLOCK = (new BlockReed(83, 73)).c(0.0F).a(g).a("reeds").p();
                // 1.1
                // SUGAR_CANE_BLOCK = (new BlockReed(83, 73)).c(0.0F).a(g).a("reeds").p();
                Block.byId[Block.SUGAR_CANE_BLOCK.id] = null;
                Block.byId[Block.SUGAR_CANE_BLOCK.id] = new BReed(Block.SUGAR_CANE_BLOCK.id, 73, sugarcaneMaxHeight, canSugarcane).setHardness(0.0F).setSound(Block.g).b("reeds");
            }
        }
        if(eSapling){
            if(canSapling){
                // 1.0.0
                // SAPLING = (new BlockSapling(6, 15)).c(0.0F).a(g).a("sapling").i();
                // 1.1
                // SAPLING = (new BlockSapling(6, 15)).c(0.0F).a(g).a("sapling").i();
                Block.byId[Block.SAPLING.id] = null;
                Block.byId[Block.SAPLING.id] = new SSapling(Block.SAPLING.id, 15).setHardness(0.0F).setSound(Block.g).b("sapling");
            }
        }
        if(eRedFlower){
            if(canRedFlower){
                // 1.0.0
                // BlockFlower RED_ROSE = (BlockFlower) (new BlockFlower(38, 12)).c(0.0F).a(g).a("rose");
                // 1.1
                // BlockFlower RED_ROSE = (BlockFlower) (new BlockFlower(38, 12)).c(0.0F).a(g).a("rose");
                Block.byId[Block.RED_ROSE.id] = null;
                Block.byId[Block.RED_ROSE.id] = new SBFlower(Block.RED_ROSE.id, 12).setHardness(0.0F).setSound(Block.g).b("rose");
            }
        }
        if(eYellowFlower){
            if(canYellowFlower){
                // 1.0.0
                // BlockFlower YELLOW_FLOWER = (BlockFlower) (new BlockFlower(37, 13)).c(0.0F).a(g).a("flower");
                // 1.1
                // BlockFlower YELLOW_FLOWER = (BlockFlower) (new BlockFlower(37, 13)).c(0.0F).a(g).a("flower");
                Block.byId[Block.YELLOW_FLOWER.id] = null;
                Block.byId[Block.YELLOW_FLOWER.id] = new SBFlower(Block.YELLOW_FLOWER.id, 11).setHardness(0.0F).setSound(Block.g).b("flower");
            }
        }
        if(eTrapDoor){
            if(canTrapDoor){
                // 1.0.0
                // TRAP_DOOR = (new BlockTrapdoor(96, Material.WOOD)).c(3.0F).a(e).a("trapdoor").p().i();
                // 1.1
                // 
                Block.byId[Block.TRAP_DOOR.id] = null;
                Block.byId[Block.TRAP_DOOR.id] = new BTrapDoor(Block.TRAP_DOOR.id, Material.WOOD).setHardness(3.0F).setSound(Block.e).b("trapdoor");
            }
        }
        if(eWildGrass){
            if(canGrass){
                // 1.0.0
                // BlockLongGrass LONG_GRASS = (BlockLongGrass) (new BlockLongGrass(31, 39)).c(0.0F).a(g).a("tallgrass");
                // 1.1
                // BlockLongGrass LONG_GRASS = (BlockLongGrass) (new BlockLongGrass(31, 39)).c(0.0F).a(g).a("tallgrass");
                Block.byId[Block.LONG_GRASS.id] = null;
                Block.byId[Block.LONG_GRASS.id] = new SBLongGrass(Block.LONG_GRASS.id, 39, wildGrass, wildGrassMaxHeight).setHardness(0.0F).setSound(Block.g).b("tallgrass");
            }
        }
        if(eFence){
            if(fixFence){
                // 1.0.0
                // FENCE = (new BlockFence(85, 4)).c(2.0F).b(5.0F).a(e).a("fence");
                // 1.1
                // FENCE = (new BlockFence(85, 4)).c(2.0F).b(5.0F).a(e).a("fence");
                Block.byId[Block.FENCE.id] = null;
                Block.byId[Block.FENCE.id] = new BFence(Block.FENCE.id, 4).setHardness(0.3F).setResistance(5F).setSound(Block.e).b("fence");
                Block.lightBlock[Block.FENCE.id] = 0;
            }
        }
        if(eGlowstone){
            if(fixGlowstone){
                // 1.0.0
                // GLOWSTONE = (new BlockLightStone(89, 105, Material.SHATTERABLE)).c(0.3F).a(j).a(1.0F).a("lightgem");
                // 1.1
                // GLOWSTONE = (new BlockLightStone(89, 105, Material.SHATTERABLE)).c(0.3F).a(j).a(1.0F).a("lightgem");
                Block.byId[Block.GLOWSTONE.id] = null;
                Block.byId[Block.GLOWSTONE.id] = new BLightstone(Block.GLOWSTONE.id, 105, Material.SHATTERABLE).setHardness(0.3F).setLightValue(1.0F).setSound(Block.j).b("lightgem");
                try{
                    Field field = Material.SHATTERABLE.getClass().getDeclaredField("H");
                    field.setAccessible(true);
                    field.setBoolean(Material.SHATTERABLE, false);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(eCactus){
            if(fixCactus || canCactus || cactusMaxHeight != 3){
                // 1.0.0
                // CACTUS = (new BlockCactus(81, 70)).c(0.4F).a(k).a("cactus");
                // 1.1
                // 
                Block.byId[Block.CACTUS.id] = null;
                Block.byId[Block.CACTUS.id] = new BCactus(Block.CACTUS.id, 70, canCactus, fixCactus, cactusMaxHeight).setHardness(0.4F).setSound(Block.k).b("cactus");
                if(fixCactus){
                    try {
                        Field field = Material.CACTUS.getClass().getDeclaredField("H");
                        field.setAccessible(true);
                        field.setBoolean(Material.CACTUS, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(eLeaves){
            if(fixLeaves){
                // 1.0.0
                // BlockLeaves LEAVES = (BlockLeaves) (new BlockLeaves(18, 52)).c(0.2F).g(1).a(g).a("leaves").i();
                // 1.1
                // 
                Block.byId[Block.LEAVES.id] = null;
                Block.byId[Block.LEAVES.id] = new BLeaves(Block.LEAVES.id, 52).setHardness(0.2F).setSound(Block.g).b("leaves");
                try {
                    Field field = Material.LEAVES.getClass().getDeclaredField("H");
                    field.setAccessible(true);
                    field.setBoolean(Material.LEAVES, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Minecraft sets it to 255 when you change isOpaqueCube to true, method a().
                Block.lightBlock[Block.LEAVES.id] = 1;
                // Look into "t"  -  requiresSelfNotify[]
                // It seems important.
                //CHANGED
                Block.r[Block.LEAVES.id] = true;
            }
        }
        if(eGlass){
            if(fixGlass){
                // 1.0.0
                // GLASS = (new BlockGlass(20, 49, Material.SHATTERABLE, false)).c(0.3F).a(j).a("glass");
                // 1.1
                // 
                Block.byId[Block.GLASS.id] = null;
                Block.byId[Block.GLASS.id] = new BGlass(Block.GLASS.id, 49, Material.SHATTERABLE, true).setHardness(0.3F).setSound(Block.j).b("glass");
                try{
                    Field field = Material.SHATTERABLE.getClass().getDeclaredField("H");
                    field.setAccessible(true);
                    field.setBoolean(Material.SHATTERABLE, false);
                }catch(Exception e){
                    e.printStackTrace();
                }
                Block.lightBlock[Block.GLASS.id] = 0;
            }
        }
        if(eIce){
            if(fixIce){
                // 1.0.0
                // ICE = (new BlockIce(79, 67)).c(0.5F).g(3).a(j).a("ice");
                // 1.1
                // 
                Block.byId[Block.ICE.id] = null;
                Block.byId[Block.ICE.id] = new BIce(Block.ICE.id, 67).setHardness(0.5F).setSound(Block.j).b("ice");
                try {
                    Field field = Material.ICE.getClass().getDeclaredField("H");
                    field.setAccessible(true);
                    field.setBoolean(Material.ICE, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Minecraft sets it to 255 when you change isOpaqueCube to true, method a().
                Block.lightBlock[Block.ICE.id] = 3;
            }
        }
    }

    public void stop(){
        if(fixIronFence){
            Block.byId[Block.IRON_FENCE.id] = Block.IRON_FENCE;
        }
        if(fixThinGlass){
            Block.byId[Block.THIN_GLASS.id] = Block.THIN_GLASS;
        }
        if(canDoor){
            Block.byId[Block.WOODEN_DOOR.id] = Block.WOODEN_DOOR;
            Block.byId[Block.IRON_DOOR_BLOCK.id] = Block.IRON_DOOR_BLOCK;
        }
        if(canPortal){
            Block.byId[Block.PORTAL.id] = Block.PORTAL;
        }
        if(canRailPowered){
            Block.byId[Block.GOLDEN_RAIL.id] = Block.GOLDEN_RAIL;
        }
        if(canRailDetector){
            Block.byId[Block.DETECTOR_RAIL.id] = Block.DETECTOR_RAIL;
        }
        if(canRail){
            Block.byId[Block.RAILS.id] = Block.RAILS;
        }
        if(disableSandFall){
            Block.byId[Block.SAND.id] = Block.SAND;
        }
        if(disableGravelFall){
            Block.byId[Block.GRAVEL.id] = Block.GRAVEL;
        }
        if(canSugarcane || sugarcaneMaxHeight != 3){
            Block.byId[Block.SUGAR_CANE_BLOCK.id] = Block.SUGAR_CANE_BLOCK;
        }
        if(canRedFlower){
            Block.byId[Block.RED_ROSE.id] = Block.RED_ROSE;
        }
        if(canYellowFlower){
            Block.byId[Block.YELLOW_FLOWER.id] = Block.YELLOW_FLOWER;
        }
        if(canTrapDoor){
            Block.byId[Block.TRAP_DOOR.id] = Block.TRAP_DOOR;
        }
        if(canGrass){
            Block.byId[Block.LONG_GRASS.id] = Block.LONG_GRASS;
        }
        if(fixFence){
            Block.byId[Block.FENCE.id] = Block.FENCE;
        }
        if(fixGlowstone){
            Block.byId[Block.GLOWSTONE.id] = Block.GLOWSTONE;
            try {
                Field field = Material.SHATTERABLE.getClass().getDeclaredField("H");
                field.setAccessible(true);
                field.setBoolean(Material.SHATTERABLE, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(fixCactus || canCactus || cactusMaxHeight != 3){
            Block.byId[Block.CACTUS.id] = Block.CACTUS;
            if(fixCactus){
                try { // Reflection. Setting isTranslucent to true again
                    Field field = Material.CACTUS.getClass().getDeclaredField("H");
                    field.setAccessible(true);
                    field.setBoolean(Material.CACTUS, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if(fixGlass){
            Block.byId[Block.GLASS.id] = Block.GLASS;
            try {
                Field field = Material.SHATTERABLE.getClass().getDeclaredField("H");
                field.setAccessible(true);
                field.setBoolean(Material.SHATTERABLE, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(fixLeaves){
            Block.byId[Block.LEAVES.id] = Block.LEAVES;
            try {
                Field field = Material.LEAVES.getClass().getDeclaredField("H");
                field.setAccessible(true);
                field.setBoolean(Material.LEAVES, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
