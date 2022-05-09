package net.treset.audio_hotkeys.tools.objects;

import net.minecraft.sound.SoundCategory;

public enum VolumeTarget {
    MASTER(SoundCategory.MASTER),
    MUSIC(SoundCategory.MUSIC),
    JUKEBOX(SoundCategory.RECORDS),
    ENVIRONMENT(SoundCategory.AMBIENT),
    WEATHER(SoundCategory.WEATHER),
    BLOCKS(SoundCategory.BLOCKS),
    HOSTILE(SoundCategory.HOSTILE),
    FRIENDLY(SoundCategory.NEUTRAL),
    PLAYER(SoundCategory.PLAYERS),
    VOICE(SoundCategory.VOICE),
    SUBTITLES(null);

    private final SoundCategory category;

    private VolumeTarget(SoundCategory category) { this.category = category; }
    public SoundCategory getCategory() { return this.category; }
}
