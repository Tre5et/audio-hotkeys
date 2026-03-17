package net.treset.audio_hotkeys.gui;

public class AudioOverlay {
    public String text;
    public SoundSprite sprite;
    public int timeActive;

    public AudioOverlay(String text, SoundSprite sprite, int timeActive) {
        this.text = text;
        this.sprite = sprite;
        this.timeActive = timeActive;
    }
}
