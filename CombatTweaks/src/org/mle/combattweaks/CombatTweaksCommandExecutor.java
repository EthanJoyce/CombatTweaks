package org.mle.combattweaks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CombatTweaksCommandExecutor implements CommandExecutor {
    
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("combattweaks")) {
		    if(args != null && args.length > 0) {
		        if(args[0].equalsIgnoreCase("reload"))
		            return onReloadCommand(sender, cmd, label, args);
		        else if(args[0].equalsIgnoreCase("help"))
		            return onHelpCommand(sender, cmd, label, args);
		        else if(args[0].equalsIgnoreCase("version"))
		            return onVersionCommand(sender, cmd, label, args);
		        else {
		            sender.sendMessage(ChatColor.RED + "Unknown Sub-Command \"" + args[0] + "\"");
		            return true;
		        }
		    } else {
                sender.sendMessage(ChatColor.RED + "Please enter a Sub-Command.");
		        return true;
		    }
		}
		
		return true;
	}
	
	
	private boolean onReloadCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if(!sender.hasPermission("combattweaks.reload")) {
	        sender.sendMessage(ChatColor.RED + "You don't have permission to do that.");
	        
	        return true;
	    }
	    
	    CombatTweaks.instance.reloadConfig();
	    CombatTweaks.instance.loadConfiguration();
	    
	    sender.sendMessage(ChatColor.GREEN + "CombatTweaks reloaded successfully!");
	    return true;
	}
	
	
	private boolean onHelpCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if(!sender.hasPermission("combattweaks.help")) {
	        sender.sendMessage(ChatColor.RED + "You don't have permission to do that.");
	        
	        return true;
	    }
	    
	    sender.sendMessage(ChatColor.GOLD + "---------- CombatTweaks Help: ----------");
	    
	    sender.sendMessage(ChatColor.GREEN + " help" + ChatColor.RED + " - Shows this help text.");
	    sender.sendMessage(ChatColor.GREEN + " version" + ChatColor.RED + " - Shows the current plugin version.");
	    sender.sendMessage(ChatColor.GREEN + " reload" + ChatColor.RED + " - Reloads the plugin.");
	    
	    sender.sendMessage(ChatColor.GOLD + "------------------------------------");
	    
	    return true;
	}
	
	
	private boolean onVersionCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if(!sender.hasPermission("combattweaks.version")) {
	        sender.sendMessage(ChatColor.RED + "You don't have permission to do that.");
	        
	        return true;
	    }
	    
	    sender.sendMessage(ChatColor.GREEN + "Current Version: " + ChatColor.AQUA + CombatTweaks.instance.getDescription().getVersion());
	    return true;
	}
    
    
}
