package me.xasz.xMinecartSpeed;



import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class xEventListener implements Listener{
	private final xMinecartSpeed x;
	private double normalspeed = 0;
	private Map<String,String> haveSpeed = new HashMap<String,String>(); 
	public xEventListener (final xMinecartSpeed instance){
		x = instance;		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock() != null)
			if(event.getClickedBlock().getType() == Material.DIAMOND_BLOCK ){
				if(haveSpeed.containsKey(event.getPlayer().getName())){
					if(haveSpeed.get(event.getPlayer().getName()).equals("true")) {
						x.sendMessageToPlayer(event.getPlayer(), "Speed Disabled");
						haveSpeed.put(event.getPlayer().getName(), "false");
					}else{
						x.sendMessageToPlayer(event.getPlayer(), "Speed Enabled");		
						haveSpeed.put(event.getPlayer().getName(), "true");			
					}
				}else{
					haveSpeed.put(event.getPlayer().getName(), "true");
					x.sendMessageToPlayer(event.getPlayer(), "Speed Enabled");
				}
			}
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleEnterEvent (VehicleEnterEvent event){
		if(event.getEntered() instanceof Player){
			if(haveSpeed.get(((Player)event.getEntered()).getName()).equals("true")){				
				if(event.getVehicle() instanceof Minecart){
					x.sendMessageToPlayer((Player)event.getEntered(), "Super Speed On");	
					Minecart m = (Minecart)event.getVehicle();
					normalspeed = m.getMaxSpeed();
					m.setMaxSpeed(m.getMaxSpeed()*3);
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleExitEvent (VehicleExitEvent event){
		if(event.getExited() instanceof Player){
			if(haveSpeed.get(((Player)event.getExited()).getName()).equals("true")){				
				if(event.getVehicle() instanceof Minecart){
					x.sendMessageToPlayer((Player)event.getExited(), "Super Speed Off");	
					Minecart m = (Minecart)event.getVehicle();
					m.setMaxSpeed(normalspeed);
				}
			}
		}
	}
}
