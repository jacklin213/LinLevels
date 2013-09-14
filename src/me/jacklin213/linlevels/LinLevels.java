package me.jacklin213.linlevels;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class LinLevels extends JavaPlugin{
	
	public static LinLevels plugin;
	public Logger log;
	public ConfigHandler configHandler;
	
	@Override
	public void onEnable() {
		plugin = this;
		log = getLogger();
		configHandler = new ConfigHandler(this.getDataFolder().getAbsolutePath(), getConfig());
	}
	
	@Override
	public void onDisable() {
		plugin = null;
		log = null;
		configHandler = null;
	}
}
