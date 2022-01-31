package net.treset.audio_hotkeys.gui;

public enum TextureCoordinate {
    MUTE (new int[]{0, 0}),
    UNMUTE (new int[]{24, 0}),
    LOUDER (new int[]{0, 24}),
    QUIETER (new int[]{24, 24});

    private final int[] coordinates;

    TextureCoordinate(int[] coordinates) { this.coordinates = coordinates; };

    public int[] getCoordinates() { return this.coordinates; }
}
