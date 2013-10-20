package com.gmail.mooman219.build;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;

public class LivingInputListener{

	public LivingBuilding plugin;

	public LivingInputListener(LivingBuilding plugin){
		this.plugin = plugin;
	}
	
	public static BindingExecutionDelegate aBadWorkAround(){
		return new BindingExecutionDelegate(){

			@Override
			public void keyPressed(KeyBindingEvent e) {
				if(e.getScreenType() != ScreenType.GAME_SCREEN)
					return;
				if(e.getBinding().getId().equalsIgnoreCase("getBlock")){
					if(e.getPlayer().getGameMode() == GameMode.CREATIVE){
						for(Block b : e.getPlayer().getLineOfSight(null, 6)){
							if (b.getType() != Material.AIR) { 
								ItemStack a = new ItemStack(b.getType(), 1, b.getData());
								e.getPlayer().setItemInHand(a);
								break;
							} 
						}
					}
				}
				
			}

			@Override
			public void keyReleased(KeyBindingEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
