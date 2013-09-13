package me.jacklin213.linlevels;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener{
	
	public static LinLevels plugin;
	
	public PlayerListener(LinLevels instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if (!player.hasPlayedBefore()){
			plugin.configHandler.createPlayerConfig(player.getName());
		}
	}
	
}
