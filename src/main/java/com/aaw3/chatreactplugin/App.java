package com.aaw3.chatreactplugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.HandlerList;

public class App extends JavaPlugin 
{

    @Override
    public void onEnable() {

        Cfg.DirectoryCheck(this);
        Sounds sounds = Cfg.GetSounds(this);
        if (sounds == null)
        {
            getLogger().info("Sounds Configuration File Error, not proceeding");
            return;
        }
        
        getLogger().info("Starting up");
        ChatListener cl = new ChatListener(this,sounds);
        getServer().getPluginManager().registerEvents(cl, this);
        this.getCommand("chatreact").setExecutor(new Commands(this, cl));

    }

    public void onDisable() {
        getLogger().info("Shutting down");

        HandlerList.unregisterAll(this);
    }
}
