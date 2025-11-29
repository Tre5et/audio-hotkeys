package net.treset.audio_hotkeys;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.treset.audio_hotkeys.config.Config;
import net.treset.audio_hotkeys.tools.KeybindTools;
import net.treset.vanillaconfig.screen.ConfigScreen;

public class HotkeyClient implements ClientModInitializer {

    public static ConfigScreen getConfigScreen() {
        return new ConfigScreen(Config.MAIN_PAGE, MinecraftClient.getInstance().currentScreen);
    }

    @Override
    public void onInitializeClient() {
        KeybindTools.init();
        Config.init();
    }
}
