package me.jacklin213.linlevels.commands;

import me.jacklin213.linlevels.LinLevels;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LinLevelsCommands implements CommandExecutor{
	
	private LinLevels LL;
	
	public LinLevelsCommands(LinLevels instance){
		LL = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("linlevels")){
			if (args.length == 0){
				LL.MSG.commandReply(sender.getName(), "Command Under Constuction");
			}
			if (args[0].equalsIgnoreCase("reload")){
				new CommandReload(sender, args, "linlevels.reload");
			}
		}
		return false;
	}

}
