package me.jacklin213.linlevels;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler {
	
	public static LinLevels plugin;
	private FileConfiguration config;
	private File configFile;
	private String pluginFolder;
	private File playerFolder;
	
	//TODO: Create FILE to store PlayerNames to load Configurations on startup
	private HashMap<String, FileConfiguration>playerConfigs = new HashMap<String, FileConfiguration>();
	//Constructor
	public ConfigHandler(String dataFolder, FileConfiguration config){
		pluginFolder = dataFolder;
		createPlayerFolder(pluginFolder);
		setConfig(config);
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
	
	public void createPlayerFolder(String dataFolder){
		playerFolder = new File(dataFolder + "/players");
		if (!playerFolder.exists()){
			playerFolder.mkdir();
		}
	}
	
	// Player Config stuff
	public void createPlayerConfig(String playerName){
		File playerFile = new File(getPlayerFolder() + File.separator + playerName + ".yml");
		FileConfiguration playerConfig  = YamlConfiguration.loadConfiguration(playerFile);
		
		playerConfig.set("Name", playerName);
		playerConfig.set("Level", 1);
		playerConfig.set("XP", 0);
		try {
			playerConfig.save(playerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			plugin.log.severe("Unable to create configuration for " + playerName);
			plugin.log.severe("Please send the error to jacklin213 on http://dev.bukkit.org/profiles/jacklin213");
			e.printStackTrace();
		}
		
		playerConfigs.put(playerName, playerConfig);
	}
	
	public FileConfiguration getPlayerConfig(String playerName){
		if(playerConfigs.containsKey(playerName)){
            return playerConfigs.get(playerName);
        } 
		plugin.log.severe("Cannot find specified player config");
		return null;
    }
	
	public void reloadPlayerConfig(String playerName){
		File playerFile = new File(getPlayerFolder() + File.separator + playerName + ".yml");
		FileConfiguration playerConfig  = YamlConfiguration.loadConfiguration(playerFile);
		
		playerConfigs.put(playerName, playerConfig);
	}
	
	public void savePlayerConfig(String playerName){
		try{
			getPlayerConfig(playerName).save(new File(getPlayerFolder(), playerName + ".yml"));
		} catch (Exception ex){ 
			plugin.getLogger().severe("Could not save player config: " + playerName);
		}
	}
	
	//Getters and Setters
	
	public File getPlayerFolder(){
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

}
