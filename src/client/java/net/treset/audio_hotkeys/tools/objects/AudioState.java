package net.treset.audio_hotkeys.tools.objects;

public class AudioState {
    public String name;
    public boolean muted;
    public int unmuteVolume;
    public VolumeTarget source;
    public AudioState(String name, boolean muted, int unmuteVolume, VolumeTarget source) {
        this.name = name;
        this.muted = muted;
        this.unmuteVolume = unmuteVolume;
        this.source = source;
    }
}
