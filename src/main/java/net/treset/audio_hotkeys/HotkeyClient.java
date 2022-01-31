package net.treset.audio_hotkeys;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.treset.audio_hotkeys.audiolevels.AudioLevels;
import net.treset.audio_hotkeys.config.Config;
import net.treset.audio_hotkeys.config.gui.ConfigGui;
import net.treset.audio_hotkeys.keybinds.InputHandler;
import net.treset.audio_hotkeys.keybinds.KeyCallbacks;
import net.treset.audio_hotkeys.tools.FileTools;
import net.treset.audio_hotkeys.tools.KeybindTools;
import org.lwjgl.glfw.GLFW;

public class HotkeyClient implements ClientModInitializer {

    public static ConfigGui configScreen;

    @Override
    public void onInitializeClient() {

        KeybindTools.registerKeybind( //setup keybinds
                "key.audio_hotkeys.config_gui", GLFW.GLFW_KEY_O);
        ClientTickEvents.END_CLIENT_TICK.register(client -> { KeybindTools.resolveKeybinds(); });

        ConfigManager.getInstance().registerConfigHandler(HotkeyMod.MOD_ID, new Config()); //register config

        if(!FileTools.loadOrCreateConfigDir()) return; //setup config directory
        if(!AudioLevels.loadSavedLevels()) return;

        InputEventHandler.getKeybindManager().registerKeybindProvider(InputHandler.getInstance());
        KeyCallbacks.init();
    }
}
