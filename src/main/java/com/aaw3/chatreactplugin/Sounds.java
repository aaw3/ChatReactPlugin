package com.aaw3.chatreactplugin;

import java.util.ArrayList;
import java.util.List;

public class Sounds {
    public Sound[] sounds;

    public Sound[] getSounds() { return sounds; }

    public Sounds() { }

    public Sounds(Sound[] sounds)
    {
        this.sounds = sounds;
    }

    public static Sounds GenerateConfigFile()
    {
        boolean caseSensitive = false, ignorePunctuation = true, matchIfContains = true;
        Sound[] sounds = new Sound[] {
            new Sound(new String[] {"SoundEx1", "Example1" },"minecraft:block.anvil.land", caseSensitive, ignorePunctuation, matchIfContains),
            new Sound(new String[] {"SoundEx2", "Example2" }, "minecraft:ambient.cave", caseSensitive, ignorePunctuation, matchIfContains),
            new Sound(new String[] {"SoundEx3", "Example3" }, "minecraft:ui.toast.challenge_complete", caseSensitive, ignorePunctuation, matchIfContains)
            
        };
        return new Sounds(sounds);
    }
}
