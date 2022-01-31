package net.treset.audio_hotkeys.tools.objects;

import fi.dy.masa.malilib.config.options.ConfigInteger;

public class ConfigVolumeInteger {
    public ConfigInteger integer;
    public VolumeTarget target;

    public ConfigVolumeInteger(ConfigInteger integer, VolumeTarget target) {
        this.integer = integer;
        this.target = target;
    }
}
