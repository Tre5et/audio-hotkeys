package net.treset.audio_hotkeys.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.treset.audio_hotkeys.HotkeyClient;
import net.treset.audio_hotkeys.HotkeyMod;
import net.treset.audio_hotkeys.config.gui.ConfigGui;

public class ModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() { //set config screen as modmenu options
        HotkeyMod.LOGGER.info("set modmenu screen");
        return (screen) -> {
            HotkeyClient.configScreen = new ConfigGui();
            HotkeyClient.configScreen.setParent(screen);
            return HotkeyClient.configScreen;
        };
    }
}
