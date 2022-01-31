package net.treset.audio_hotkeys.keybinds;

import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import fi.dy.masa.malilib.hotkeys.IMouseInputHandler;
import net.treset.audio_hotkeys.HotkeyMod;
import net.treset.audio_hotkeys.config.Config;
import net.treset.audio_hotkeys.tools.objects.ConfigVolumeHotkey;

import java.util.ArrayList;
import java.util.List;

public class InputHandler implements IKeybindProvider, IMouseInputHandler
{
    private static final InputHandler INSTANCE = new InputHandler();

    private InputHandler()
    {
        super();
    }

    public static InputHandler getInstance()
    {
        return INSTANCE;
    }

    @Override
    public void addKeysToMap(IKeybindManager manager)
    {
        for (ConfigVolumeHotkey e : Config.VOLUME_HOTKEYS)
        {
            manager.addKeybindToMap(e.hotkey.getKeybind());
        }
    }

    @Override
    public void addHotkeys(IKeybindManager manager)
    {
        List<IHotkey> hotkeys = new ArrayList<>();
        for (ConfigVolumeHotkey e : Config.VOLUME_HOTKEYS) {
            hotkeys.add(e.hotkey);
        }
        manager.addHotkeysForCategory(HotkeyMod.MOD_ID, "hotkeys.audio_hotkeys.category.generic_hotkeys", hotkeys);
    }
}
