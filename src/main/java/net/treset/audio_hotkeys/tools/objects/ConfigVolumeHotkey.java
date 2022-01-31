package net.treset.audio_hotkeys.tools.objects;

import fi.dy.masa.malilib.config.options.ConfigHotkey;

public class ConfigVolumeHotkey {
    public final ConfigHotkey hotkey;
    public final VolumeKeybindType type;
    public final VolumeTarget target;

    public ConfigVolumeHotkey(ConfigHotkey hotkey, VolumeKeybindType type, VolumeTarget target) {
        this.hotkey = hotkey;
        this.type = type;
        this.target = target;
    }
}
