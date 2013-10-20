package com.gmail.mooman219.build;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class LivingEntityListener implements Listener{

    @SuppressWarnings("unused")
    private LivingBuilding plugin;

    public LivingEntityListener(LivingBuilding a){
        plugin = a;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamage (EntityDamageEvent e){
        if(e.isCancelled()){
            return;
        }else if(LivingConfig.forceDamage){
            return;
        }else if(e.getCause() == DamageCause.SUFFOCATION){
            Entity a = e.getEntity();
            if(a instanceof Player){
                Player b = (Player) a;
                switch(b.getWorld().getBlockAt(b.getEyeLocation()).getType()){
                case FENCE:
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                case IRON_FENCE:
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                case THIN_GLASS:
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                case AIR:
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                case LONG_GRASS:
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                default:
                    return;
                }
            }else{
                switch(a.getWorld().getBlockAt(a.getLocation()).getType()){
                case FENCE:
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                case IRON_FENCE:
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                case THIN_GLASS:
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                case AIR:
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                case LONG_GRASS:
                    e.setDamage(0);
                    e.setCancelled(true);
                    return;
                default:
                    return;
                }
            }
        }
    }
}
