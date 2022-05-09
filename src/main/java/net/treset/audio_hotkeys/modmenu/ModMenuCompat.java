package net.treset.audio_hotkeys.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.treset.audio_hotkeys.HotkeyClient;

public class ModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() { //set config screen as modmenu options
        return (screen) -> HotkeyClient.CONFIG_SCREEN;
    }
}
