package com.gmail.mooman219.build;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

import com.gmail.mooman219.build.permission.LivingPermissions;

public class LivingPlayerListener implements Listener{

	private LivingBuilding plugin;

	public LivingPlayerListener(LivingBuilding a){
		plugin = a;
	}

	@EventHandler
	public void onPlayerInteract (PlayerInteractEvent event){
		if(event.getItem() == null || event.getClickedBlock() == null)
			return;
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if(canUse(event.getItem().getType())){
		    Block eBlock = event.getClickedBlock().getRelative(event.getBlockFace());
		    //debug for ladder

			if(event.getClickedBlock().getType() == Material.LADDER){
				if(!(event.getBlockFace() == BlockFace.DOWN || event.getBlockFace() == BlockFace.UP)){
					return;
				}
			}

			BlockPlaceEvent customEvent = new BlockPlaceEvent(eBlock, eBlock.getState(), event.getClickedBlock(), event.getItem(), event.getPlayer(), true);
			this.plugin.getServer().getPluginManager().callEvent(customEvent);

			if(!customEvent.isCancelled()){

				byte blockData = correctData(event.getBlockFace(), event);
				if(eBlock.getType() == Material.AIR){
					if(isMechBlocks(event.getClickedBlock().getType())) return;
					eBlock.setType(event.getItem().getType());
					eBlock.setData(blockData);

					easyLadder(blockData, eBlock, event);

					removeItem(event);
				}
			}
		}
	}

	public boolean isMechBlocks(Material mat){
		switch (mat){
		case LEVER:
			return true;
		case TRAP_DOOR:
			return true;
		case LADDER:
			return false;
        case STONE_BUTTON:
            return true;
        case BREWING_STAND:
            return true;
        case FENCE_GATE:
            return true;
        case IRON_DOOR_BLOCK:
            return true;
        case WOODEN_DOOR:
            return true;
		default:
			return false;
		}
	}

	public boolean canUse(Material mat){
		switch (mat){
		case LADDER:
			return LivingConfig.canLadder && LivingConfig.eLadder;
		default:
			return false;
		}
	}

	public void easyLadder(byte blockData, Block givenBlock, PlayerInteractEvent event){
		Material item = event.getItem().getType();
		if(item == Material.LADDER){
			if(LivingConfig.easyLadder){
				BlockFace bFace = BlockFace.SELF;
				Player player = event.getPlayer();
				if(getCardinalDirection(player).equalsIgnoreCase("north") || getCardinalDirection(player).equalsIgnoreCase("northwest")){
					
					bFace = BlockFace.NORTH;
					
				}
				if(getCardinalDirection(player).equalsIgnoreCase("south") || getCardinalDirection(player).equalsIgnoreCase("southeast")){
					
					bFace = BlockFace.SOUTH;
					
				}
				if(getCardinalDirection(player).equalsIgnoreCase("east") || getCardinalDirection(player).equalsIgnoreCase("northeast")){
					
					bFace = BlockFace.EAST;
					
				}
				if(getCardinalDirection(player).equalsIgnoreCase("west") || getCardinalDirection(player).equalsIgnoreCase("southwest")){
					
					bFace = BlockFace.WEST;
					
				}
				
				/**switch (blockData){
				case 4:
					bFace = BlockFace.SOUTH;
					break;
				case 5:
					bFace = BlockFace.NORTH;
					break;
				case 2:
					bFace = BlockFace.WEST;
					break;
				case 3:
					bFace = BlockFace.EAST;
					break;
				default:
					return;
				}**/
				

				Block lBlock = givenBlock.getRelative(bFace);

				if(lBlock.getType() == Material.AIR){
					lBlock.setType(Material.LADDER);
					if(getCardinalDirection(player).equalsIgnoreCase("north") || getCardinalDirection(player).equalsIgnoreCase("northwest")){
						
						lBlock.setData((byte) 4);
						return;
						
					}
					if(getCardinalDirection(player).equalsIgnoreCase("south") || getCardinalDirection(player).equalsIgnoreCase("southeast")){
						
						lBlock.setData((byte) 5);
						return;
						
					}
					if(getCardinalDirection(player).equalsIgnoreCase("east") || getCardinalDirection(player).equalsIgnoreCase("northeast")){
						
						lBlock.setData((byte) 2);
						return;
						
					}
					if(getCardinalDirection(player).equalsIgnoreCase("west") || getCardinalDirection(player).equalsIgnoreCase("southwest")){
						
						lBlock.setData((byte) 3);
						return;
						
					}
					
					
					/**switch (blockData){
					case 4:
						lBlock.setData((byte) 5);
						return;
					case 5:
						lBlock.setData((byte) 4);
						return;
					case 2:
						lBlock.setData((byte) 3);
						return;
					case 3:
						lBlock.setData((byte) 2);
						return;
					default:
						return;
					}**/
				}
			}
		}
	}
	
	public static String getCardinalDirection(Player player) {
        double rot = (player.getLocation().getYaw() - 90) % 360;
        if (rot < 0) {
            rot += 360.0;
        }
        return getDirection(rot);
    }
	
	private static String getDirection(double rot) {
        if (0 <= rot && rot < 22.5) {
            return "North";
        } else if (22.5 <= rot && rot < 67.5) {
            return "Northeast";
        } else if (67.5 <= rot && rot < 112.5) {
            return "East";
        } else if (112.5 <= rot && rot < 157.5) {
            return "Southeast";
        } else if (157.5 <= rot && rot < 202.5) {
            return "South";
        } else if (202.5 <= rot && rot < 247.5) {
            return "Southwest";
        } else if (247.5 <= rot && rot < 292.5) {
            return "West";
        } else if (292.5 <= rot && rot < 337.5) {
            return "Northwest";
        } else if (337.5 <= rot && rot < 360.0) {
            return "North";
        } else {
            return null;
        }
    }

	public byte correctData(BlockFace clickBlockFace, PlayerInteractEvent event){
		Material item = event.getItem().getType();
		Player player = event.getPlayer();
		if(item == Material.LADDER){
			
			if(getCardinalDirection(player).equalsIgnoreCase("north") || getCardinalDirection(player).equalsIgnoreCase("northwest")){
				
				return 5;
				
			}
			if(getCardinalDirection(player).equalsIgnoreCase("south") || getCardinalDirection(player).equalsIgnoreCase("southeast")){
				
				return 4;
				
			}
			if(getCardinalDirection(player).equalsIgnoreCase("east") || getCardinalDirection(player).equalsIgnoreCase("northeast")){
				
				return 3;
				
			}
			if(getCardinalDirection(player).equalsIgnoreCase("west") || getCardinalDirection(player).equalsIgnoreCase("southwest")){
				
				return 2;
				
			}
			
			/**switch (clickBlockFace){
			case NORTH:
				return 4;
			case SOUTH:
				return 5;
			case EAST:
				return 2;
			case WEST:
				return 3;
			case UP:
				if(event.getClickedBlock().getType() == Material.LADDER)
					return event.getClickedBlock().getData();
			case DOWN:
				if(event.getClickedBlock().getType() == Material.LADDER)
					return event.getClickedBlock().getData();
			}**/
		}
		return 3;
	}

	public void removeItem(PlayerInteractEvent event){
		if(event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
		if(event.getItem().getAmount() > 1){
			event.getItem().setAmount(event.getItem().getAmount() - 1);
		}else{
			event.getPlayer().setItemInHand(null);
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		if(LivingPermissions.checkPapers(e.getPlayer())){
			if(LivingConfig.fullAccess && LivingConfig.chatCommands){
				e.getPlayer().sendMessage(ChatColor.GRAY + "Bui: This server is running version " + plugin.version + " with debug commands");
			}else{
				e.getPlayer().sendMessage(ChatColor.GRAY + "Bui: This server is running version " + plugin.version);
			}
		}
	}
}