package me.jacklin213.linlevels.commands;

import me.jacklin213.linlevels.LinLevels;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandReload {

	private LinLevels LL;

	public CommandReload(LinLevels instance) {
		LL = instance;
	}

	public CommandReload(CommandSender sender, String[] args, String permissionNode) {
		if (args.length == 1){
			if (sender.hasPermission(permissionNode)) {
				LL.configHandler.reloadConfig();
				LL.MSG.commandReply(sender.getName(), ChatColor.GREEN + "Config reloaded.");
			}
		}
		if (args.length == 2){
			LL.configHandler.reloadPlayerConfig(args[1]);
			LL.MSG.commandReply(sender.getName(), ChatColor.GREEN + "Player config: " + args[1] + " has been reloaded");
		}
	}
}
