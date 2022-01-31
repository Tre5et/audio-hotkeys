package net.treset.audio_hotkeys.keybinds;

import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import net.treset.audio_hotkeys.HotkeyMod;
import net.treset.audio_hotkeys.audiolevels.AudioLevels;
import net.treset.audio_hotkeys.config.Config;
import net.treset.audio_hotkeys.tools.objects.ConfigVolumeHotkey;

public class KeyCallbacks
{
    public static void init()
    {
        Callbacks callback = new Callbacks();

        for (ConfigVolumeHotkey e : Config.VOLUME_HOTKEYS) {
            e.hotkey.getKeybind().setCallback(callback);
        }
    }

    public static class Callbacks implements IHotkeyCallback
    {
        @Override
        public boolean onKeyAction(KeyAction action, IKeybind key)
        {
            for(ConfigVolumeHotkey e : Config.VOLUME_HOTKEYS) {
                if(e.hotkey.getKeybind() == key) {
                    AudioLevels.resolveKey(e);

                    return true;
                }
            }

            HotkeyMod.LOGGER.warn("Unable to resolve registered hotkey " + key.getKeysDisplayString());

            return false;
        }
    }
}
