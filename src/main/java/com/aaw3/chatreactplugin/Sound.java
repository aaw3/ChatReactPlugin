package com.aaw3.chatreactplugin;

import java.util.List;

public class Sound {

    public String[] phrases;
    public String soundID;
    public boolean caseSensitive;
    public boolean ignorePunctuation;
    public boolean matchIfContains;

    public Sound() { }

    public String[] getPhrases() { return phrases; }
    public String getSoundID() { return soundID; }
    public boolean getCaseSensitive() { return caseSensitive; }
    public boolean getIgnorePunctuation() { return ignorePunctuation; }
    public boolean getMatchIfContains() { return matchIfContains; }

    public Sound(String[] phrases, String soundID, boolean caseSensitive, boolean ignorePunctuation, boolean matchIfContains)
    {
        this.phrases = phrases;
        this.soundID = soundID;
        this.caseSensitive = caseSensitive;
        this.ignorePunctuation = ignorePunctuation;
        this.matchIfContains = matchIfContains;
    }
}
