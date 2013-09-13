package me.jacklin213.linlevels;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class LinLevels extends JavaPlugin{
	
	public static LinLevels plugin;
	public Logger log = Logger.getLogger("Minecraft");
	public ConfigHandler configHandler;
	
	@Override
	public void onEnable() {
		configHandler = new ConfigHandler(this.getDataFolder().getAbsolutePath(), getConfig());
		
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}

}
