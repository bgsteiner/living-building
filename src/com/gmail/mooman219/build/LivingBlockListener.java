package com.gmail.mooman219.build;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LivingBlockListener implements Listener {

    @SuppressWarnings("unused")
    private LivingBuilding plugin;

    public LivingBlockListener(LivingBuilding a){
        plugin = a;
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
    	if(checkUnlocked(event.getPlayer().getItemInHand().getType())){
    		if(event.getAction() == Action.RIGHT_CLICK_AIR){
    			Player player = event.getPlayer();
    			Block lBlock = player.getTargetBlock(null, 1);
    			Material item = event.getItem().getType();
    			Block relitive = lBlock.getRelative(getCardinalDirection(player));
    			if(item == Material.SIGN){
    				
    				relitive.setType(Material.SIGN);
    				
    			}
    			if(item == Material.STONE_PLATE){
    				
    				relitive.setType(Material.STONE_PLATE);
    				
    			}
    			if(item == Material.WOOD_PLATE){
    				
    				relitive.setType(Material.WOOD_PLATE);
    				
    			}
    			
    			
    			if(event.getPlayer().getGameMode() == GameMode.SURVIVAL){
    				event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
    			}
    			
    		}
    	
    	}
    	
    	
    }
    
    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event){
    	if (event.isBuildable())
            return;
        if (event.getBlock().getType().equals(Material.AIR)){
            if (checkUnlocked(event.getMaterial())){
                event.setBuildable(true);
            }
        }
    }

    @EventHandler
    public void onBlockPhysics (BlockPhysicsEvent event){
        if(event.isCancelled())
            return;

        if (checkPhy(event.getBlock().getType())){
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBlockBreak (BlockBreakEvent event){
        if(event.isCancelled() == false){
            if(LivingConfig.eLadder){
                if(event.getBlock().getType() == Material.LADDER){
                    if(LivingConfig.easyLadder){
                        BlockFace bFace;
                        byte bloData = event.getBlock().getData();
                        switch (bloData){
                        case 4:
                            bFace = BlockFace.SOUTH; break;
                        case 5:
                            bFace = BlockFace.NORTH; break;
                        case 2:
                            bFace = BlockFace.WEST; break;
                        case 3:
                            bFace = BlockFace.EAST; break;
                        default: return;
                        }
                        Block eBlock = event.getBlock().getRelative(bFace);
                        if(eBlock.getType() == Material.LADDER){
                            byte relData = eBlock.getData();
                            switch (bloData){
                            case 5:
                                if(relData == 4)
                                    eBlock.setType(Material.AIR);
                                return;
                            case 4:
                                if(relData == 5)
                                    eBlock.setType(Material.AIR);
                                return;
                            case 3:
                                if(relData == 2)
                                    eBlock.setType(Material.AIR);
                                return;
                            case 2:
                                if(relData == 3)
                                    eBlock.setType(Material.AIR);
                                return;
                            default:
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean checkPhy(Material m){
        switch(m){
        case LADDER:
            return LivingConfig.canLadder && LivingConfig.eLadder;
        case WOOD_PLATE:
            return LivingConfig.canWoodPlate && LivingConfig.eWoodPlate;
        case STONE_PLATE:
            return LivingConfig.canStonePlate && LivingConfig.eStonePlate;
        case SIGN:
            return LivingConfig.canSign && LivingConfig.eSign;
        default:
            return false;
        }
    }

    public boolean checkUnlocked(Material m){
        switch(m){
        case WOOD_PLATE:
            return LivingConfig.canWoodPlate && LivingConfig.eWoodPlate;
        case STONE_PLATE:
            return LivingConfig.canStonePlate && LivingConfig.eStonePlate;
        case SIGN:
            return LivingConfig.canSign && LivingConfig.eSign;
        default:
            return false;
        }
    }
    
    public static BlockFace getCardinalDirection(Player player) {
        double rot = (player.getLocation().getYaw() - 90) % 360;
        if (rot < 0) {
            rot += 360.0;
        }
        return getDirection(rot);
    }
    
    private static BlockFace getDirection(double rot) {
        if (0 <= rot && rot < 22.5) {
            return BlockFace.NORTH;
        } else if (22.5 <= rot && rot < 67.5) {
        	return BlockFace.NORTH;
        } else if (67.5 <= rot && rot < 112.5) {
        	return BlockFace.EAST;
        } else if (112.5 <= rot && rot < 157.5) {
        	return BlockFace.EAST;
        } else if (157.5 <= rot && rot < 202.5) {
        	return BlockFace.SOUTH;
        } else if (202.5 <= rot && rot < 247.5) {
        	return BlockFace.SOUTH;
        } else if (247.5 <= rot && rot < 292.5) {
        	return BlockFace.WEST;
        } else if (292.5 <= rot && rot < 337.5) {
        	return BlockFace.WEST;
        } else if (337.5 <= rot && rot < 360.0) {
        	return BlockFace.NORTH;
        } else {
            return null;
        }
    }
}