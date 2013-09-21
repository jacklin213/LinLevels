package me.jacklin213.linlevels.commands;

import me.jacklin213.linlevels.LinLevels;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class CommandSet extends SubCommand{
	
	private LinLevels LL;

	public CommandSet(LinLevels instance) {
		LL = instance;
	}

	@Override
	public void start(CommandSender sender, String[] args, String permissionNode) {
		if (sender.hasPermission(permissionNode)){
			if (args.length == 1){
				LL.MSG.commandReply(sender.getName(), LL.configHandler.getInfoColor() + "Usage: /linlevels set <playername> <stat type> <value>: Sets the stat type to desired value");
			} else
			if (args.length == 2 || args.length == 3){
				LL.MSG.commandReply(sender.getName(), LL.configHandler.getInfoColor() + "Not Enough Args! Usage: /linlevels set <playername> <stat type>: Sets the stat type to desired value");
			} else 
			if (args.length > 4){
				LL.MSG.commandReply(sender.getName(), LL.configHandler.getInfoColor() + "Too many Args! Usage: /linlevels set <playername> <stat type>: Sets the stat type to desired value");
			} else
			if (args.length == 4){
				String playerName = args[1];
				String statType = args[2];
				String value = args[3];
				YamlConfiguration playerConfig = LL.configHandler.getPlayerConfig(playerName);
				if (LL.configHandler.RELOAD){
					if(statType.equalsIgnoreCase("level")){
						try {
							int intValue = Integer.parseInt(value);
							playerConfig.set("Level", intValue);
							LL.MSG.commandReply(sender.getName(), playerName+"'s Level has been set to " + value);
							LL.configHandler.savePlayerConfig(playerName, playerConfig);
							return;
						} catch (NumberFormatException e){
							LL.getLogger().warning("Value contains invalid characters");
							LL.MSG.commandReply(sender.getName(), "Value contains invalid characters");
							return;
						}
					}
					if(statType.equalsIgnoreCase("xp")){
						try {
							int intValue = Integer.parseInt(value);
							playerConfig.set("XP", intValue);
							LL.MSG.commandReply(sender.getName(), playerName+"'s XP has been set to " + value);
							LL.configHandler.savePlayerConfig(playerName, playerConfig);
							return;
						} catch (NumberFormatException e){
							LL.getLogger().warning("Value contains invalid characters");
							LL.MSG.commandReply(sender.getName(), "Value contains invalid characters");
							return;
						}
					}
					if(!(statType.equalsIgnoreCase("xp") || statType.equalsIgnoreCase("level"))){
						LL.MSG.commandReply(sender.getName(), "Invalid Stat Type!");
						return;
					}
				}
				LL.MSG.commandReply(sender.getName(), "Player file: " + playerName.toLowerCase() + ".yml does not exist.");
			}
		}
		
	}

}
