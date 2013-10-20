package com.gmail.mooman219.build;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.mooman219.build.permission.LivingPermissions;

public class LivingCommander {

	public static LivingBuilding plugin;
	public static LivingConfig livingConfig;
	public static LivingBlockListener livingBlockListener;

	public LivingCommander (LivingBuilding a){
		plugin = a;
		livingConfig = plugin.livingConfig;
		livingBlockListener = plugin.livingBlockListener;
	}

	public static void message(Player p, String m){
		if(p != null)
			p.sendMessage(m);
		else
			LivingBuilding.log.info(LivingBuilding.cast + m);
	}

	public static boolean procesCommand( CommandSender sender, Command command, String label, String[] args ) {
		Player player = null;
		if(sender instanceof Player)
			player = (Player) sender;

		if(LivingConfig.chatCommands == true){
			// livingbuilding || bu
			if(label.equalsIgnoreCase("bu") || label.equalsIgnoreCase("livingbuilding")){
				if(args.length == 0){
					if(LivingPermissions.has(player, "livingbuliding.chat.select")){
						if(player.getGameMode() == GameMode.CREATIVE){
							for(Block b : player.getLineOfSight(null, 6)){
								if (b.getType() != Material.AIR) { 
									ItemStack a = new ItemStack(b.getType(), 1, b.getData());
									player.setItemInHand(a);
									return true;
								} 
							}
						}
					}
				}
				else if(args.length == 1){
					// reload || r
					if(args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("reload")){
						if(LivingPermissions.has(player, "livingbuliding.chat.reload")){
							//livingConfig.loadkeys();
							message(player,ChatColor.GRAY + "The reload command is currently removed.");
							message(player,ChatColor.GRAY + "This is to avoid instability surrounding");
							message(player,ChatColor.GRAY + "the modifications to the net.minecraft.server");
							return true;
						}
					}
					//
					// switch || s
					if(args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("switch")){
						if(LivingPermissions.has(player, "livingbuliding.chat.switch")){
							if(player.getGameMode() == GameMode.SURVIVAL)
								player.setGameMode(GameMode.CREATIVE);
							else
								player.setGameMode(GameMode.SURVIVAL);
							message(player,ChatColor.GRAY + "Gamemode changed");
							return true;
						}else{
							message(player,ChatColor.GRAY + "Permissions Denied.");
							return true;
						}
					}
					//
				}
				message(player,ChatColor.GRAY + "Invalid Living Building Command.");
			}
		}
		return true;
	}
}
