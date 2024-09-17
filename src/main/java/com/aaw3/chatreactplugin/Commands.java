package com.aaw3.chatreactplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor {
    private App app;
    private ChatListener chatListener;
    public Commands(App app, ChatListener chatListener)
    {
        this.app = app;
        this.chatListener = chatListener;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {

        // if (sender instanceof Player OR ConsoleCommandSender OR BlockCommandSender)
        if (args.length == 0)
            return false;
        
        if (sender instanceof Player && !sender.hasPermission("chatreact.reload"))
        {
            Player p = (Player)sender;
            p.sendMessage(ChatColor.RED + "You don't have permission to this command!");
            return true;
        }
        else
        {
            if (args[0].equals("reload"))
            {
                Player p = (Player)sender;
                chatListener.ResetSounds(Cfg.GetSounds(app));
                p.sendMessage(ChatColor.GREEN + "Chat React reloaded.");
                return true;
            }
        }
        return false;
    }
}
