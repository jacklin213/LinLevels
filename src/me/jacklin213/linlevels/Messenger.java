package me.jacklin213.linlevels;

import org.bukkit.entity.Player;

public class Messenger {
public String pluginName;
	
	private LinLevels LL;
	
	public Messenger(LinLevels instance){
		LL = instance;
	}
	
	public void commandReply(String target, String message){
		Player reciever = LL.getServer().getPlayer(target);
		if (reciever != null){
			reciever.sendMessage(LL.configHandler.getPluginName() + " " + message);
		}
	}
	
}
