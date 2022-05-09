package net.treset.audio_hotkeys.tools;

import net.minecraft.client.MinecraftClient;
import net.treset.audio_hotkeys.HotkeyClient;
import net.treset.audio_hotkeys.audiolevels.AudioLevels;
import net.treset.audio_hotkeys.config.Config;
import net.treset.audio_hotkeys.tools.objects.VolumeTarget;
import net.treset.vanillaconfig.config.IntegerConfig;

import java.util.HashMap;
import java.util.Map;

public class KeybindTools {
    private static Map<String, VolumeTarget> keyVolumeTargets = new HashMap<String, VolumeTarget>();

    public static void init() {
        keyVolumeTargets.put("master", VolumeTarget.MASTER);
        keyVolumeTargets.put("subtitles", VolumeTarget.SUBTITLES);
        keyVolumeTargets.put("music", VolumeTarget.MUSIC);
        keyVolumeTargets.put("jukebox", VolumeTarget.JUKEBOX);
        keyVolumeTargets.put("environment", VolumeTarget.ENVIRONMENT);
        keyVolumeTargets.put("weather", VolumeTarget.WEATHER);
        keyVolumeTargets.put("blocks", VolumeTarget.BLOCKS);
        keyVolumeTargets.put("hostile", VolumeTarget.HOSTILE);
        keyVolumeTargets.put("friendly", VolumeTarget.FRIENDLY);
        keyVolumeTargets.put("player", VolumeTarget.PLAYER);
        keyVolumeTargets.put("voice", VolumeTarget.VOICE);
    }

    public static void resolveKeybinds(String keybind) {
        if(keybind.equals(Config.OPEN_CONFIG.getKey())) {
            MinecraftClient.getInstance().setScreen(HotkeyClient.CONFIG_SCREEN);
        } else
            switch(keybind.split("\\.")[keybind.split("\\.").length - 1]) {
                case "mute" -> AudioLevels.toggleMute(getVolumeTarget(keybind));
                case "up" -> AudioLevels.changeSoundLevel(getVolumeTarget(keybind), getLevelChange(keybind));
                case "down" -> AudioLevels.changeSoundLevel(getVolumeTarget(keybind), -getLevelChange(keybind));
            }
    }

    public static VolumeTarget getVolumeTarget(String keybind) {
        String key = keybind.split("\\.")[keybind.split("\\.").length - 2];
        if(!keyVolumeTargets.containsKey(key)) return null;
        return keyVolumeTargets.get(key);
    }

    public static int getLevelChange(String keybind) {
        int out = 0;

        for(IntegerConfig e : Config.Lists.INTEGER_CONFIGS) {
            if(getVolumeTarget(e.getKey()) == getVolumeTarget(keybind)) {
                out = e.getInteger();
            }
        }

        return out;
    }
}
