package me.xasz.xMinecartSpeed;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class xMinecartSpeed extends JavaPlugin{
	
	PluginManager pm = null;
    xEventListener listener = null;
   @Override
    public void onEnable() {
 	    this.getConfig().options().copyDefaults(true);
	    saveConfig();
	    
    	System.out.println("[xMinecartSpeed] enabled");
		pm = getServer().getPluginManager();
		listener = new xEventListener(this);
		pm.registerEvents(listener,this);
	}
    
    @Override
    public void onDisable() {
    	System.out.println("[xMinecartSpeed] disabled");
    	
    }
    public void sendMessageToPlayer(Player player, String message){
    	if(player != null){
    		player.sendMessage(ChatColor.BLUE+"[xMinecartSpeed] "+ChatColor.WHITE+message);
    	}
    }
    public void sendBroadCastMessageToWorld(World world,String message){
    	for(Player p :world.getPlayers()){
        	p.sendMessage(ChatColor.BLUE+"[xMinecartSpeed] "+ChatColor.WHITE+message);
    	}
    }
}
