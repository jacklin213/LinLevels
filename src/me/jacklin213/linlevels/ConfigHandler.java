package me.jacklin213.linlevels;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler {
	
	public static LinLevels plugin;
	private FileConfiguration config;
	private File configFile;
	private String pluginFolder;
	private String playerFolder = "plugins" + File.separator + "LinLevels" + File.separator + "players";
	private String pluginName;
	
	//Constructors
	public ConfigHandler(LinLevels instance){
		plugin = instance;
	}
	
	public ConfigHandler(String dataFolder, FileConfiguration config){
		pluginFolder = dataFolder;
		createPlayerFolder();
		setConfig(config);
		setPluginName();
	}
	
	//Methods
	
	public void createConfig(){
		configFile = new File(pluginFolder + File.separator + "config.yml");
		
		if (!configFile.exists()){
			plugin.log.info(String.format("[%s] Cannot find config.yml, Generating now....", plugin.getDescription().getName()));
			plugin.log.info(String.format("[%s] Config generated !", plugin.getDescription().getName()));
			getConfig().options().copyDefaults(true);
			plugin.saveDefaultConfig();
		}
	}
	
	public void reloadConfig() {
		plugin.reloadConfig();
	}
	
	public void createPlayerFolder(){
		File folder = new File(playerFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}
	}
	
	// Player Config stuff
	public void createPlayerConfig(String playerName){
		plugin.log.info("Creating player config for: " + playerName.toLowerCase());
		File playerFile = new File(getPlayerFolder() + File.separator + playerName.toLowerCase() + ".yml");
		if (!playerFile.exists()) {
			 try {
				 playerFile.createNewFile();
				 YamlConfiguration playerConfig  = YamlConfiguration.loadConfiguration(playerFile);
				 	
				 playerConfig.set("Name", playerName);
				 playerConfig.set("Level", 1);
				 playerConfig.set("XP", 0);
				 playerConfig.save(playerFile);
			 } catch (IOException e) {
				 plugin.log.severe("Unable to create configuration for " + playerName);
				 plugin.log.severe("Please send the error to jacklin213 on http://dev.bukkit.org/profiles/jacklin213");
				 e.printStackTrace();
			 }
		 }
	}
	
	public YamlConfiguration getPlayerConfig(String playerName){
		 File file = new File(playerFolder + File.separator + playerName.toLowerCase() + ".yml");
		 if (!file.exists()) {
			 try {
				 file.createNewFile();
			 } catch (IOException e) {
				 plugin.log.severe("Unable to create configuration for " + playerName);
				 plugin.log.severe("Please send the error to jacklin213 on http://dev.bukkit.org/profiles/jacklin213");
				 e.printStackTrace();
			 }
		 }
		 YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(file);
		 return playerConfig;
	}
	
	public YamlConfiguration reloadPlayerConfig(String playerName){
		File playerFile = new File(getPlayerFolder() + File.separator + playerName.toLowerCase() + ".yml");
		if (!playerFile.exists()) {
			plugin.log.warning("Player file: " + playerName.toLowerCase() + ".yml does not exist.");
		}
		YamlConfiguration playerConfig  = YamlConfiguration.loadConfiguration(playerFile);
		return playerConfig;
	}
	
	public void savePlayerConfig(String playerName){
		try{
			getPlayerConfig(playerName).save(new File(getPlayerFolder(), playerName.toLowerCase() + ".yml"));
		} catch (Exception ex){ 
			plugin.getLogger().severe("Could not save player config: " + playerName.toLowerCase());
		}
	}
	
	//Getters and Setters
	
	public String getPlayerFolder(){
		return playerFolder;
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public void setConfig(FileConfiguration config) {
		this.config = config;
	}

	public File getConfigFile() {
		return configFile;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName() {
		String pluginName = ChatColor.translateAlternateColorCodes('&', config.getString("Format.PluginName"));
		this.pluginName = pluginName;
	}

}
