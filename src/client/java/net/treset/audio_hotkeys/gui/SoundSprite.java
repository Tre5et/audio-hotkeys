package net.treset.audio_hotkeys.gui;

import net.minecraft.resources.Identifier;
import net.treset.audio_hotkeys.HotkeyMod;

public enum SoundSprite {
    MUTE (Identifier.fromNamespaceAndPath(HotkeyMod.MOD_ID, "hud/speaker_muted")),
    UNMUTE (Identifier.fromNamespaceAndPath(HotkeyMod.MOD_ID, "hud/speaker_unmuted")),
    LOUDER (Identifier.fromNamespaceAndPath(HotkeyMod.MOD_ID, "hud/speaker_louder")),
    QUIETER (Identifier.fromNamespaceAndPath(HotkeyMod.MOD_ID, "hud/speaker_quieter"));

    private final Identifier identifier;

    SoundSprite(Identifier identifier) { this.identifier = identifier; }

    public Identifier getIdentifier() { return this.identifier; }
}
