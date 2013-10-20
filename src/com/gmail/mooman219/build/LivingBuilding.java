package com.gmail.mooman219.build;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;

import com.gmail.mooman219.build.permission.LivingPermissions;

public class LivingBuilding extends JavaPlugin{	

    public static Logger log = Logger.getLogger("Minecraft");
    public PluginDescriptionFile pdfFile;
    public LivingNMS livingNet = new LivingNMS();
    public LivingBlockListener livingBlockListener = new LivingBlockListener(this);
    public LivingPlayerListener livingPlayerListener = new LivingPlayerListener(this);
    public LivingEntityListener livingEntityListener = new LivingEntityListener(this);
    public LivingConfig livingConfig = new LivingConfig();
    public LivingCommander livingCommander = new LivingCommander(this);
    public String version;

    public static String cast = "[Moo][LivingBuilding] ";

    // Mooman219 Permission System
    public LivingPermissions livingPermissions;
    // Add this to the onEnable: startPermissions();
    public void startPermissions(){
        // False
        // LivingBuilding.chat.reload
        // LivingBuilding.chat.switch
        // LivingBuilding.chat.select
        LivingPermissions.registerPermission("livingBuilding.chat.reload", false);
        LivingPermissions.registerPermission("livingBuilding.chat.switch", false);
        LivingPermissions.registerPermission("livingBuilding.chat.select", false);
        // True
        //

    }
    //

    @Override
    public void onEnable(){
        PluginDescriptionFile pdfFile = this.getDescription();
        livingConfig.configCheck(pdfFile);

        livingNet.start();
        pdfFile = getDescription();

        PluginManager pm = this.getServer().getPluginManager();
        
        if(pm.getPlugin("Spout") != null){
            //Set the block you're currently looking at to the block in your hand in Creative Mode'
            SpoutManager.getKeyBindingManager().registerBinding("getBlock", Keyboard.KEY_R, "Set the block you're currently looking at to the block in your hand in Creative Mode", LivingInputListener.aBadWorkAround(), this);
        }
        
        pm.registerEvents(livingBlockListener, this);
        pm.registerEvents(livingPlayerListener, this);
        pm.registerEvents(livingEntityListener, this);

        livingPermissions = new LivingPermissions(cast, this, this.getServer());
        startPermissions();
        
        version = pdfFile.getVersion();
        log.info(cast + version + " Enabled");
    }

    @Override
    public void onDisable(){
        livingNet.stop();
        PluginDescriptionFile pdfFile = this.getDescription();
        log.info(cast + pdfFile.getVersion() + " Has been disabled.");
    }

    public static void log(String a){
        log.info(a);
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {
        return LivingCommander.procesCommand(sender, command, label, args);
    }
}
