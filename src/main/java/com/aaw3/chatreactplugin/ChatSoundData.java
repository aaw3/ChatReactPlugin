package com.aaw3.chatreactplugin;

public class ChatSoundData {

    private String message;
    private String matchedPhraseWithCase;
    private String matchedPhraseOriginal;
    private Sound soundObj;

    public String getOriginalMessage() { return message; }
    public String getMatchedPhraseWithCase() { return matchedPhraseWithCase; }
    public String getMatchedPhraseOriginal() { return matchedPhraseOriginal; }
    public Sound getSoundObject() { return soundObj; }

    public ChatSoundData(String message, String matchedPhraseWithCase, String matchedPhraseOriginal, Sound soundObj)
    {
        this.message = message;
        this.matchedPhraseWithCase = matchedPhraseWithCase;
        this.matchedPhraseOriginal = matchedPhraseOriginal;
        this.soundObj = soundObj;
    }
}
