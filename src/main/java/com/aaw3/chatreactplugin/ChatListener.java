package com.aaw3.chatreactplugin;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;


public class ChatListener implements Listener {

    Sound[] sounds;
    JavaPlugin app;
    public ChatListener(JavaPlugin app, Sounds sounds)
    {
        this.app = app;
        this.sounds = sounds.getSounds();
    }

    public void ResetSounds(Sounds sounds)
    {
        this.sounds = sounds.getSounds();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        //app.getLogger().info("Got chat message: " + event.getMessage());

        String msg = event.getMessage();

        ChatSoundData data = checkSound(msg);

        if (data == null)
            return;

        event.setMessage(Colorize(data));
        
        for (Player p : Bukkit.getOnlinePlayers())
        {
            p.playSound(p.getLocation(), data.getSoundObject().getSoundID(), SoundCategory.MASTER, 1.0f, 1.0f);
        }
        
    }

    public ChatSoundData checkSound(String msg)
    {
        for (Sound snd : sounds)
        {
            for (String pse : snd.getPhrases())
            {
                String localMsg = msg;
                if (!snd.getCaseSensitive())
                {
                    localMsg = localMsg.toLowerCase();
                    pse = pse.toLowerCase();
                }

                if (snd.getIgnorePunctuation())
                {
                    localMsg = localMsg.replaceAll("\\p{Punct}", "");
                }

                if (snd.getMatchIfContains())
                {
                    if (localMsg.contains(pse))
                    {
                        int index = localMsg.indexOf(pse, 0);   
                        return new ChatSoundData(msg, msg.substring(index, index + pse.length()), pse,  snd); //We'll use substring and indexof to replace set the phrase as the matched case as matchIfContains uses a different chat formatting method
                    }
                }
                else if (localMsg.equals(pse))
                {
                    //app.getLogger().info("Sound found: " + snd.getSoundIDs() + " : " + pse);
                    return new ChatSoundData(msg, pse, pse, snd);
                }
            }
        }
        //app.getLogger().info("Sound not found..");
        return null;
    }

    public String Colorize(ChatSoundData data)
    {
        String returnText = data.getOriginalMessage();
        ChatColor color = RndColor.Random();
        Sound soundObj = data.getSoundObject();
        if (!soundObj.getMatchIfContains())
        {
            return "" + RndColor.Random() + ChatColor.BOLD + returnText;
        }
        else
        {
            if (!data.getSoundObject().getCaseSensitive())
            {
                //if (!data.getOriginalMessage().equals(data.getMatchedPhraseOriginal()))
                //{
                //    returnText = returnText.replace(data.getMatchedPhraseOriginal().toLowerCase(), "" + color + ChatColor.BOLD + data.getMatchedPhraseOriginal().toLowerCase() + ChatColor.RESET);
                //}

                String[] splitString = returnText.split("(?i)" + Pattern.quote(data.getMatchedPhraseOriginal()));
                List<Integer> replacementLocations = new ArrayList<Integer>();

                int index = data.getOriginalMessage().toLowerCase().indexOf(data.getMatchedPhraseOriginal().toLowerCase());
                
                if (index > -1)
                    replacementLocations.add(index);
                
                    while (index >= 0)
                {
                    index = data.getOriginalMessage().toLowerCase().indexOf(data.getMatchedPhraseOriginal().toLowerCase(), index + 1);
                    if (index >= 0)
                        replacementLocations.add(index);
                }

                returnText = "";
                for (int i = 0; i < replacementLocations.size() + 1; i++) //Must set as 1 as splitString might be 0, while replacementLocations might be 1, otherwise they're the same. + 1 will make it only go over by one at most.
                {
                    if (i < splitString.length)
                        returnText += splitString[i];
                    
                    if (i < replacementLocations.size())
                        returnText += "" + color + ChatColor.BOLD + data.getOriginalMessage().substring(replacementLocations.get(i), replacementLocations.get(i) + data.getMatchedPhraseOriginal().length()) + ChatColor.RESET;
                }
                return returnText;

            }
            else
            {
                return returnText.replace(data.getMatchedPhraseOriginal(), "" + color + ChatColor.BOLD + data.getMatchedPhraseOriginal().toLowerCase() + ChatColor.RESET);
            }
        }
    }
}
