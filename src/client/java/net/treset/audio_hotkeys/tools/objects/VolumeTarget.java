package net.treset.audio_hotkeys.tools.objects;

import net.minecraft.sounds.SoundSource;

public enum VolumeTarget {
    MASTER(SoundSource.MASTER),
    MUSIC(SoundSource.MUSIC),
    JUKEBOX(SoundSource.RECORDS),
    ENVIRONMENT(SoundSource.AMBIENT),
    WEATHER(SoundSource.WEATHER),
    BLOCKS(SoundSource.BLOCKS),
    HOSTILE(SoundSource.HOSTILE),
    FRIENDLY(SoundSource.NEUTRAL),
    PLAYER(SoundSource.PLAYERS),
    VOICE(SoundSource.VOICE),
    SUBTITLES(null);

    private final SoundSource category;

    VolumeTarget(SoundSource category) { this.category = category; }
    public SoundSource getCategory() { return this.category; }
}
