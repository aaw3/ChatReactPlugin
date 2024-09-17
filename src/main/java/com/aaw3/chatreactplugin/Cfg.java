package com.aaw3.chatreactplugin;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

public class Cfg {
    public static Sounds GetSounds(JavaPlugin app)
    {
        File cfgFile = new File(app.getDataFolder().toString(), "sounds.yml");

        if (!cfgFile.exists())
        {
            try
            {

                PrintWriter writer = new PrintWriter(cfgFile);
                Yaml yaml = new Yaml();
                Sounds s = Sounds.GenerateConfigFile();
                yaml.dump(s, writer);
                
                return s;
            } catch (Exception ex)
            {
                app.getLogger().info(ex.getClass() + " : " + ex.getMessage() + " : " + ex.getCause());
            }
        }

        try
        {

            InputStream stream = new FileInputStream(cfgFile);
            TagInspector t = tag -> tag.getClassName().equals(Sounds.class.getName());
            LoaderOptions l = new LoaderOptions();
            l.setTagInspector(t);
            Yaml yaml = new Yaml(new Constructor(Sounds.class, l));
            Sounds s = yaml.load(stream);
            return s;
        }
        catch (Exception ex)
        {
            app.getLogger().info(ex.getClass() + " : " + ex.getMessage() + " : " + ex.getCause());
        }

        return null;
    }

    public static void DirectoryCheck(JavaPlugin app)
    {
        if (!Files.exists(app.getDataFolder().toPath()))
        {
            app.getDataFolder().mkdirs();
        }
    }
}
