package me.jacklin213.linlevels.commands;

import me.jacklin213.linlevels.LinLevels;

import org.bukkit.command.CommandSender;

public class CommandStats extends SubCommand{
	
	private LinLevels LL;

	public CommandStats(LinLevels instance) {
		LL = instance;
	}

	@Override
	public void start(CommandSender sender, String[] args, String permissionNode) {
		if(args.length == 1){
			LL.MSG.commandReply(sender.getName(), LL.configHandler.getInfoColor() + "Usage: /linlevels stats <playername>: To see player stats");
		}
		if(args.length == 2){
			sender.sendMessage(LL.configHandler.getInfoColor() + "#=====[Player Stats]=====#" + "\n" +  LL.configHandler.getPlayerStats(args[1]));
		}
	}
	
}
