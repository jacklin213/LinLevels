package me.jacklin213.linlevels;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener{
	
	public static LinLevels plugin;
	
	public PlayerListener(LinLevels instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		plugin.configHandler.createPlayerConfig(player.getName());
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		plugin.configHandler.savePlayerConfig(player.getName(), plugin.configHandler.getPlayerConfig(player.getName()));
	}
	
}
