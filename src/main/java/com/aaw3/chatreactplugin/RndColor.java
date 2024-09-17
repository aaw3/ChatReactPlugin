package com.aaw3.chatreactplugin;
import org.bukkit.Color;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;

public class RndColor {
    private static ChatColor[] colors = new ChatColor[] 
    {
        ChatColor.RED,
        ChatColor.GOLD,
        ChatColor.YELLOW,
        ChatColor.GREEN,
        ChatColor.AQUA,
        ChatColor.LIGHT_PURPLE
    };

    public static ChatColor Random()
    {
        return colors[(int) (Math.random() * colors.length)];
    }
}
