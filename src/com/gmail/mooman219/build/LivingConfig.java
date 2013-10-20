package com.gmail.mooman219.build;

import java.io.File;

import org.bukkit.plugin.PluginDescriptionFile;

import com.gmail.mooman219.build.config.FileIOConfig;

public class LivingConfig {

    // Config
    public static int version = 39;
    public static int pluginVersion = 0;
    public static int subVersion = 0;
    // Enabled
    public static boolean eWildGrass = false;
    public static boolean canWildGrass = true;
    public static boolean wildGrass = true;
    public static int wildGrassMaxHeight = 2;
    //
    public static boolean eCactus = false;
    public static boolean canCactus = true;
    public static boolean fixCactus = true;
    public static int cactusMaxHeight = 3;
    //
    public static boolean eSugarcane = false;
    public static boolean canSugarcane = true;
    public static int sugarcaneMaxHeight = 3;
    //
    public static boolean eLadder = false;
    public static boolean canLadder = true;
    public static boolean easyLadder = true;
    //
    public static boolean eFence = false;
    public static boolean canFence = true;
    public static boolean fixFence = true;
    //
    public static boolean eSand = false;
    public static boolean sandPhysics = true;
    //
    public static boolean eGravel = false;
    public static boolean gravelPhysics = true;
    //
    public static boolean eWoodPlate = false;
    public static boolean canWoodPlate = true;
    //
    public static boolean eStonePlate = false;
    public static boolean canStonePlate = true;
    //
    public static boolean eSign = false;
    public static boolean canSign = true;
    //
    public static boolean ePortal = false;
    public static boolean canPortal = true;
    //
    public static boolean eHalfDoor = false;
    public static boolean canHalfDoor = true;
    //
    public static boolean eRail = false;
    public static boolean canRail = true;
    //
    public static boolean eRailPowered = false;
    public static boolean canRailPowered = true;
    //
    public static boolean eRailDetector = false;
    public static boolean canRailDetector = true;
    //
    public static boolean eTrapDoor = false;
    public static boolean canTrapDoor = true;
    //
    public static boolean eRedFlower = false;
    public static boolean canRedFlower = true;
    //
    public static boolean eYellowFlower = false;
    public static boolean canYellowFlower = true;
    //
    public static boolean eSapling = false;
    public static boolean canSapling = true;
    //
    public static boolean eIce = false;
    public static boolean fixIce = true;
    //
    public static boolean eGlass = false;
    public static boolean fixGlass = true;
    //
    public static boolean eLeaves = false;
    public static boolean fixLeaves = true;
    //
    public static boolean eIronFence = false;
    public static boolean fixIronFence = true;
    //
    public static boolean eThinGlass = false;
    public static boolean fixThinGlass = true;
    //
    public static boolean eGlowstone = false;
    public static boolean fixGlowstone = true;
    // Special
    public static boolean forceDamage = false;
    // Living
    public static boolean autoUpdate = true;
    public static boolean chatCommands = true;
    public static boolean fullAccess = false;

    public String directory = "plugins/LivingBuilding/"; 
    FileIOConfig configFile = new FileIOConfig(directory + File.separator + "config.yml", false);

    public void configCheck(PluginDescriptionFile p){
        pluginVersion = Integer.parseInt(p.getVersion());
        new File(directory).mkdir();

        loadkeys();
        if(subVersion != version){
            printout();
            configFile.wipeFile();
            addDefaults();
            loadkeys();
        }
    }

    private void printout(){
        System.out.println(LivingBuilding.cast);
        System.out.println(LivingBuilding.cast + "The Config has updated!!");
        System.out.println(LivingBuilding.cast + "Please check it for new info.");
        System.out.println(LivingBuilding.cast + "You may encounter issues if you don't.");
        System.out.println(LivingBuilding.cast);
    }

    public void addDefaults(){
        configFile.writeProperty("Config.ConfigVersion", version);
        configFile.writeProperty("Config.PluginVersion", pluginVersion);

        configFile.writeProperty("Block.WildGrass", eWildGrass);
        configFile.writeProperty("Block.WildGrass.PutAnywhere", canWildGrass);
        configFile.writeProperty("Block.WildGrass.CrazyWildGrass", wildGrass);
        configFile.writeProperty("Block.WildGrass.CrazyMaxGrowthHeight", wildGrassMaxHeight);

        configFile.writeProperty("Block.Cactus", eCactus);
        configFile.writeProperty("Block.Cactus.PutAnywhere", canCactus);
        configFile.writeProperty("Block.Cactus.PutAnythingOnIt", fixCactus);
        configFile.writeProperty("Block.Cactus.MaxGrowthHeight", cactusMaxHeight);

        configFile.writeProperty("Block.SugarCane", eSugarcane);
        configFile.writeProperty("Block.SugarCane.PutAnywhere", canSugarcane);
        configFile.writeProperty("Block.SugarCane.MaxGrowthHeight", sugarcaneMaxHeight);

        configFile.writeProperty("Block.Ladder", eLadder);
        configFile.writeProperty("Block.Ladder.PutAnywhere", canLadder);
        configFile.writeProperty("Block.Ladder.FancyLadder", easyLadder);

        configFile.writeProperty("Block.Fence", eFence);
        configFile.writeProperty("Block.Fence.PutAnywhere", canFence);
        configFile.writeProperty("Block.Fence.PutAnythingOnIt", fixFence);
        configFile.writeProperty("Block.Fence.ForceDamage", forceDamage);

        configFile.writeProperty("Block.Sand", eSand);
        configFile.writeProperty("Block.Sand.Physics", sandPhysics);
        configFile.writeProperty("Block.Gravel", eGravel);
        configFile.writeProperty("Block.Gravel.Physics", gravelPhysics);

        configFile.writeProperty("Block.WoodPlate", eWoodPlate);
        configFile.writeProperty("Block.WoodPlate.PutAnywhere", canWoodPlate);
        configFile.writeProperty("Block.StonePlate", eStonePlate);
        configFile.writeProperty("Block.StonePlate.PutAnywhere", canStonePlate);
        configFile.writeProperty("Block.Sign", eSign);
        configFile.writeProperty("Block.Sign.PutAnywhere", canSign);
        configFile.writeProperty("Block.Portal", ePortal);
        configFile.writeProperty("Block.Portal.PutAnywhere", ePortal);
        configFile.writeProperty("Block.Halfdoor", eHalfDoor);
        configFile.writeProperty("Block.Halfdoor.PutAnywhere", canHalfDoor);
        configFile.writeProperty("Block.Rail", eRail);
        configFile.writeProperty("Block.Rail.PutAnywhere", canRail);
        configFile.writeProperty("Block.RailPowered", eRailPowered);
        configFile.writeProperty("Block.RailPowered.PutAnywhere", canRailPowered);
        configFile.writeProperty("Block.RailDetector", eRailDetector);
        configFile.writeProperty("Block.RailDetector.PutAnywhere", canRailDetector);
        configFile.writeProperty("Block.TrapDoor", eTrapDoor);
        configFile.writeProperty("Block.TrapDoor.PutAnywhere", canTrapDoor);
        configFile.writeProperty("Block.RedFlower", eRedFlower);
        configFile.writeProperty("Block.RedFlower.PutAnywhere", canRedFlower);
        configFile.writeProperty("Block.YellowFlower", eYellowFlower);
        configFile.writeProperty("Block.YellowFlower.PutAnywhere", canYellowFlower);
        configFile.writeProperty("Block.Sapling", eSapling);
        configFile.writeProperty("Block.Sapling.PutAnywhere", canSapling);

        configFile.writeProperty("Block.Ice", eIce);
        configFile.writeProperty("Block.Ice.PutAnythingOnIt", fixIce);
        configFile.writeProperty("Block.Glass", eGlass);
        configFile.writeProperty("Block.Glass.PutAnythingOnIt", fixGlass);
        configFile.writeProperty("Block.Leaves", eLeaves);
        configFile.writeProperty("Block.Leaves.PutAnythingOnIt", fixLeaves);
        configFile.writeProperty("Block.IronFence", eIronFence);
        configFile.writeProperty("Block.IronFence.PutAnythingOnIt", fixIronFence);
        configFile.writeProperty("Block.ThinGlass", eThinGlass);
        configFile.writeProperty("Block.ThinGlass.PutAnythingOnIt", fixThinGlass);
        configFile.writeProperty("Block.Glowstone", eGlowstone);
        configFile.writeProperty("Block.Glowstone.PutAnythingOnIt", fixGlowstone);

        configFile.writeProperty("Living.AutoUpdate", autoUpdate);
        configFile.writeProperty("Living.ChatCommands", chatCommands);
        configFile.writeProperty("Living.DebugAccess", fullAccess);
        configFile.setWriteAddAfter("Don't Enable Unless Told To", 66);

        loadkeys();
    }

    public void loadkeys(){
        configFile.writeProperty("Config.PluginVersion", pluginVersion);
        subVersion = configFile.getInt("Config.ConfigVersion", subVersion);

        eWildGrass = configFile.getBoolean("Block.WildGrass", eWildGrass);
        canWildGrass = configFile.getBoolean("Block.WildGrass.PutAnywhere", canWildGrass);
        wildGrass = configFile.getBoolean("Block.WildGrass.CrazyWildGrass", wildGrass);
        wildGrassMaxHeight = configFile.getInt("Block.WildGrass.CrazyMaxGrowthHeight", wildGrassMaxHeight);

        eCactus = configFile.getBoolean("Block.Cactus", eCactus);
        canCactus = configFile.getBoolean("Block.Cactus.PutAnywhere", canCactus);
        fixCactus = configFile.getBoolean("Block.Cactus.PutAnythingOnIt", fixCactus);
        cactusMaxHeight = configFile.getInt("Block.Cactus.MaxGrowthHeight", cactusMaxHeight);

        eSugarcane = configFile.getBoolean("Block.SugarCane", eSugarcane);
        canSugarcane = configFile.getBoolean("Block.SugarCane.PutAnywhere", canSugarcane);
        sugarcaneMaxHeight = configFile.getInt("Block.SugarCane.MaxGrowthHeight", sugarcaneMaxHeight);

        eLadder = configFile.getBoolean("Block.Ladder", eLadder);
        canLadder = configFile.getBoolean("Block.Ladder.PutAnywhere", canLadder);
        easyLadder = configFile.getBoolean("Block.Ladder.FancyLadder", easyLadder);

        eFence = configFile.getBoolean("Block.Fence", eFence);
        canFence = configFile.getBoolean("Block.Fence.PutAnywhere", canFence);
        fixFence = configFile.getBoolean("Block.Fence.PutAnythingOnIt", fixFence);
        forceDamage = configFile.getBoolean("Block.Fence.ForceDamage", forceDamage);

        eSand = configFile.getBoolean("Block.Sand", eSand);
        sandPhysics = configFile.getBoolean("Block.Sand.Physics", sandPhysics);
        eGravel = configFile.getBoolean("Block.Gravel", eGravel);
        gravelPhysics = configFile.getBoolean("Block.Gravel.Physics", gravelPhysics);

        //
        
        eWoodPlate = configFile.getBoolean("Block.WoodPlate", eWoodPlate);
        canWoodPlate = configFile.getBoolean("WoodPlate.PutAnywhere", canWoodPlate);
        
        eStonePlate = configFile.getBoolean("Block.StonePlate", eStonePlate);
        canStonePlate = configFile.getBoolean("Block.StonePlate.PutAnywhere", canStonePlate);
        
        eSign = configFile.getBoolean("Block.Sign", eSign);
        canSign = configFile.getBoolean("Block.Sign.PutAnywhere", canSign);
        
        ePortal = configFile.getBoolean("Block.Portal", ePortal);
        canPortal = configFile.getBoolean("Block.Portal.PutAnywhere", canPortal);
        
        eHalfDoor = configFile.getBoolean("Block.Halfdoor", eHalfDoor);
        canHalfDoor = configFile.getBoolean("Block.Halfdoor.PutAnywhere", canHalfDoor);
        
        eRail = configFile.getBoolean("Block.Rail", eRail);
        canRail = configFile.getBoolean("Block.Rail.PutAnywhere", canRail);
        
        eRailPowered = configFile.getBoolean("Block.RailPowered", eRailPowered);
        canRailPowered = configFile.getBoolean("Block.RailPowered.PutAnywhere", canRailPowered);
        
        eRailDetector = configFile.getBoolean("Block.RailDetector", eRailDetector);
        canRailDetector = configFile.getBoolean("Block.RailDetector.PutAnywhere", canRailDetector);
        
        eTrapDoor = configFile.getBoolean("Block.TrapDoor", eTrapDoor);
        canTrapDoor = configFile.getBoolean("Block.TrapDoor.PutAnywhere", canTrapDoor);
        
        eRedFlower = configFile.getBoolean("Block.RedFlower", eRedFlower);
        canRedFlower = configFile.getBoolean("Block.RedFlower.PutAnywhere", canRedFlower);
        
        eYellowFlower = configFile.getBoolean("Block.YellowFlower", eYellowFlower);
        canYellowFlower = configFile.getBoolean("Block.YellowFlower.PutAnywhere", canYellowFlower);
        
        eSapling = configFile.getBoolean("Block.Sapling", eSapling);
        canSapling = configFile.getBoolean("Block.Sapling.PutAnywhere", canSapling);
        
        //
        
        eIce = configFile.getBoolean("Block.Ice", eWoodPlate);
        fixIce = configFile.getBoolean("Block.Ice", eWoodPlate);
        configFile.writeProperty("Block.Ice", eIce);
        configFile.writeProperty("Block.Ice.PutAnythingOnIt", fixIce);
        
        eGlass = configFile.getBoolean("Block.Glass", eGlass);
        fixGlass = configFile.getBoolean("Block.Glass.PutAnythingOnIt", fixGlass);
        
        eLeaves = configFile.getBoolean("Block.Leaves", eLeaves);
        fixLeaves = configFile.getBoolean("Block.Leaves.PutAnythingOnIt", fixLeaves);
        
        eIronFence = configFile.getBoolean("Block.IronFence", eIronFence);
        fixIronFence = configFile.getBoolean("Block.IronFence.PutAnythingOnIt", fixIronFence);
        
        eThinGlass = configFile.getBoolean("Block.ThinGlass", eThinGlass);
        fixThinGlass = configFile.getBoolean("Block.ThinGlass.PutAnythingOnIt", fixThinGlass);
        
        eGlowstone = configFile.getBoolean("Block.Glowstone", eGlowstone);
        fixGlowstone = configFile.getBoolean("Block.Glowstone.PutAnythingOnIt", fixGlowstone);

        autoUpdate = configFile.getBoolean("Living.AutoUpdate", autoUpdate);
        chatCommands = configFile.getBoolean("Living.ChatCommands", chatCommands);
        fullAccess = configFile.getBoolean("Living.DebugAccess", false);
        configFile.setDebugText(fullAccess);
    }
}
