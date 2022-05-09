package net.treset.audio_hotkeys.gui;

public class AudioOverlay {
    public String text;
    public TextureCoordinate coordinate;
    public int timeActive;

    public AudioOverlay(String text, TextureCoordinate coordinate, int timeActive) {
        this.text = text;
        this.coordinate = coordinate;
        this.timeActive = timeActive;
    }
}
