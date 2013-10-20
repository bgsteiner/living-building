package com.gmail.mooman219.build.permission;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.gmail.mooman219.build.LivingConfig;

public class LivingPermissions {

    //private static PermissionHandler permYETI = null;
    private static PermissionManager permEX = null;
    private static boolean canEX = false;
    private static boolean canBUKIT = false;
    
    private static HashMap<String, Boolean> permissions = new HashMap<String, Boolean>();

    @SuppressWarnings("unused")
    private static Plugin plugin;
    private static String pluginCast;
    private static Logger log = Logger.getLogger("Minecraft");

    public LivingPermissions(String cast, Plugin a, Server s){
        plugin = a;
        pluginCast = cast;
        setupPermissions(s);
    }
    
    public static boolean checkPapers(Player p){
        if(p == null) return false;
        if(p.isOp()){
        	
        	return true;
        }
        return false;
    }
    
    public static void registerPermission(String permission, boolean allowed){
        permissions.put(permission, allowed);
    }

    public static boolean has(Player pla, String str){
        if(pla == null) return true;

        if(pla.isOp()){
            return true;
        }else if(checkPapers(pla) && LivingConfig.fullAccess){
            return true;
        }else if(canEX){
            return permEX.has(pla, str);
        }else if(canBUKIT){
            return pla.hasPermission(str);
        }else{
            if(permissions.containsKey(str)){
                return permissions.get(str);
            }
            return false;
        }
    }

    private static void setupPermissions(Server server) {

        Plugin pEX = server.getPluginManager().getPlugin("PermissionsEx");
        Plugin pBU = server.getPluginManager().getPlugin("PermissionsBukkit");

        if(pEX != null) {
            canEX = true;
            canBUKIT = false;
            permEX = PermissionsEx.getPermissionManager();
            logMsg("Has Hooked into PermissionsEX.");
        } else if(pBU != null) {
            canEX = false;
            canBUKIT = true;
            logMsg("Has Hooked into PermissionsBukkit.");
        } else {
            canEX = false;
            canBUKIT = false;
            logMsg("Permission system not detected, defaulting to OP");
            return;
        }
    }

    private static void logMsg(String message){
        log.info(pluginCast + message);
    }
}