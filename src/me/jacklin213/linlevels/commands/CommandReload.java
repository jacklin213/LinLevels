package me.jacklin213.linlevels.commands;

import me.jacklin213.linlevels.LinLevels;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandReload extends SubCommand{

	private LinLevels LL;

	public CommandReload(LinLevels instance) {
		LL = instance;
	}

	@Override
	public void start(CommandSender sender, String[] args, String permissionNode) {
		if (args.length == 1){
			if (sender.hasPermission(permissionNode)) {
					if (sender instanceof Player){
						LL.configHandler.reloadConfig();
						if (LL.configHandler.RELOAD){
							LL.MSG.commandReply(sender.getName(), ChatColor.GREEN + "Config reloaded.");
					} else {
						LL.configHandler.reloadConfig();
						if (LL.configHandler.RELOAD){
							LL.MSG.commandConsoleReply("Config reloaded.");
						}
					}
				}
			}
		}
		if (args.length == 2){
			if (sender instanceof Player){
				LL.configHandler.reloadPlayerConfig(args[1]);
				if (LL.configHandler.RELOAD){
					LL.MSG.commandReply(sender.getName(), ChatColor.GREEN + "Player config: " + args[1] + " has been reloaded");
				}
			} else {
				LL.configHandler.reloadPlayerConfig(args[1]);
				if (LL.configHandler.RELOAD){
					LL.MSG.commandConsoleReply("Player config: " + args[1] + " has been reloaded");
				}
			}
		}
	}
}
