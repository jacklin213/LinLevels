package me.jacklin213.linlevels;

import java.util.logging.Logger;

import me.jacklin213.linlevels.commands.CommandReload;
import me.jacklin213.linlevels.commands.CommandSet;
import me.jacklin213.linlevels.commands.CommandStats;
import me.jacklin213.linlevels.commands.LinLevelsCommands;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LinLevels extends JavaPlugin{
	
	public static LinLevels plugin;
	public Logger log = Logger.getLogger("Minecraft");
	public ConfigHandler configHandler = new ConfigHandler(this);
	public PlayerListener listener = new PlayerListener(this);
	public LinLevelsCommands LLCommand = new LinLevelsCommands(this);
	public Messenger MSG = new Messenger(this);
	
	//Command classes
	public CommandReload cmdReload = new CommandReload(this);
	public CommandStats cmdStats = new CommandStats(this);
	public CommandSet cmdSet = new CommandSet(this);
	
	@Override
	public void onEnable() {
		configHandler = new ConfigHandler(this.getDataFolder().getAbsolutePath(), getConfig());
		configHandler.createConfig();
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(listener, this);
		
		getCommand("linlevels").setExecutor(LLCommand);
		
		log.info(String.format("[%s] Version %s by jacklin213 has been Enabled!", getDescription()
				.getName(), getDescription().getVersion()));
	}
	
	@Override
	public void onDisable() {
		log.info(String.format("[%s] Disabled Version %s", getDescription()
				.getName(), getDescription().getVersion()));
	}

}
