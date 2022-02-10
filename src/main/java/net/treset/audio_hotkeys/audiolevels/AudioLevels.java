package net.treset.audio_hotkeys.audiolevels;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.treset.audio_hotkeys.HotkeyMod;
import net.treset.audio_hotkeys.config.Config;
import net.treset.audio_hotkeys.tools.MathTools;
import net.treset.audio_hotkeys.tools.objects.*;
import net.treset.audio_hotkeys.gui.AudioOverlay;
import net.treset.audio_hotkeys.gui.OverlayManager;
import net.treset.audio_hotkeys.gui.TextureCoordinate;
import net.treset.audio_hotkeys.tools.FileTools;

import java.io.File;
import java.util.List;

public class AudioLevels {
    public static AudioState MASTER = new AudioState("master", false, 100, VolumeTarget.MASTER);
    public static AudioState MUSIC = new AudioState("music", false, 100, VolumeTarget.MUSIC);
    public static AudioState JUKEBOX = new AudioState("jukebox", false, 100, VolumeTarget.JUKEBOX);
    public static AudioState ENVIRONMENT = new AudioState("environment", false, 100, VolumeTarget.ENVIRONMENT);
    public static AudioState WEATHER = new AudioState("weather", false, 100, VolumeTarget.WEATHER);
    public static AudioState BLOCKS = new AudioState("blocks", false, 100, VolumeTarget.BLOCKS);
    public static AudioState HOSTILE = new AudioState("hostile", false, 100, VolumeTarget.HOSTILE);
    public static AudioState FRIENDLY = new AudioState("friendly", false, 100, VolumeTarget.FRIENDLY);
    public static AudioState PLAYER = new AudioState("player", false, 100, VolumeTarget.PLAYER);
    public static AudioState VOICE = new AudioState("voice", false, 100, VolumeTarget.VOICE);

    public static List<AudioState> STATES = ImmutableList.of(
            MASTER,
            MUSIC,
            JUKEBOX,
            ENVIRONMENT,
            WEATHER,
            BLOCKS,
            HOSTILE,
            FRIENDLY,
            PLAYER,
            VOICE
    );

    private static GameOptions opt = MinecraftClient.getInstance().options;

    public static boolean loadSavedLevels() {
        File dir = HotkeyMod.CONFIG_DIR;

        JsonObject obj;
        if((obj = FileTools.readJsonFile(new File(dir, HotkeyMod.LEVELS_FILE_NAME))) != null) {
            for(AudioState e : STATES) {
                if(obj.has(e.name + "_muted")) e.muted = obj.get(e.name + "_muted").getAsBoolean();
                if(obj.has(e.name + "_unmute_value")) e.unmuteVolume = obj.get(e.name + "_unmute_value").getAsInt();
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean saveLevels() {
        File dir = HotkeyMod.CONFIG_DIR;

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
            updateSoundLevels();

            JsonObject obj = new JsonObject();

            for (AudioState e : STATES) {
                obj.addProperty(e.name + "_muted", e.muted);
                obj.addProperty(e.name + "_unmute_value", e.unmuteVolume);
            }

            if((opt = MinecraftClient.getInstance().options) != null) {
                opt.write();
            } else HotkeyMod.LOGGER.warn("Failed to save client options.");

            return(FileTools.writeJsonToFile(obj, new File(dir, HotkeyMod.LEVELS_FILE_NAME)));
        }
        return false;
    }

    public static void resolveKey(ConfigVolumeHotkey hotkey) {
        switch (hotkey.type) {
            case TOGGLE -> toggleMute(hotkey.target);
            case UP -> changeSoundLevel(hotkey.target, getLevelChange(hotkey.target));
            case DOWN -> changeSoundLevel(hotkey.target, -getLevelChange(hotkey.target));
            case SUBTITLES -> toggleSubtitles(hotkey.target);
        }
    }

    public static void toggleMute(VolumeTarget t) {
        updateSoundLevels();

        for(AudioState e : STATES) {
            if(e.target == t) {
                e.muted = !e.muted;
                if(e.muted) {

                    setSoundLevel(e, 0);
                    OverlayManager.drawOverlay(new AudioOverlay("Muted " + e.name + ".", TextureCoordinate.MUTE, 2000));
                } else {
                    if((opt = MinecraftClient.getInstance().options) == null) return;

                    if(opt.getSoundVolume(e.target.getCategory()) == 0) {
                        setSoundLevel(e, e.unmuteVolume);
                        OverlayManager.drawOverlay(new AudioOverlay("Unmuted " + e.name + ". Volume is now " + e.unmuteVolume + "%.", TextureCoordinate.UNMUTE, 2000));
                    } else {
                        int currentVolume = (int)(opt.getSoundVolume(e.target.getCategory()) * 100);
                        OverlayManager.drawOverlay(new AudioOverlay("Unmuted " + e.name + " but didn't change manually changed volume of " + currentVolume + "%.", TextureCoordinate.UNMUTE, 2000));
                    }
                }
            }
        }

        updateSoundLevels();
    }

    public static void changeSoundLevel(VolumeTarget t, int value) {
        for(AudioState e : STATES) {
            if(e.target == t) {
                if((opt = MinecraftClient.getInstance().options) == null) return;

                    int currentVolume = (int)(opt.getSoundVolume(e.target.getCategory()) * 100);
                int targetVolume = (int) MathTools.clamp(currentVolume + value, 0, 100);
                setSoundLevel(e, targetVolume);

                currentVolume = (int)(opt.getSoundVolume(e.target.getCategory()) * 100);
                OverlayManager.drawOverlay(new AudioOverlay(((value < 0) ? "Decreased " : "Increased ") + e.name + " volume by " + Math.abs(value) + "% to " + currentVolume + "%.", (value < 0) ? TextureCoordinate.QUIETER : TextureCoordinate.LOUDER, 2000));
            }
        }

        updateSoundLevels();
    }

    public static void toggleSubtitles(VolumeTarget t) {
        if((opt = MinecraftClient.getInstance().options) == null) return;

        opt.showSubtitles = !opt.showSubtitles;
        if(opt.showSubtitles) {
            OverlayManager.drawOverlay(new AudioOverlay("Activated subtitles.", TextureCoordinate.UNMUTE, 2000));
        } else {
            OverlayManager.drawOverlay(new AudioOverlay("Deactivated subtitles.", TextureCoordinate.MUTE, 2000));
        }
    }

    public static int getLevelChange(VolumeTarget t) {
        int out = 0;

        for(ConfigVolumeInteger e : Config.VOLUME_STEPS) {
            if(e.target == t) {
                out = e.integer.getIntegerValue();
            }
        }

        return out;
    }

    public static void setSoundLevel(AudioState m, int value) {
        if((opt = MinecraftClient.getInstance().options) == null) return;

        float targetVolume = MathTools.clamp((float)value / 100, 0, 1);
        opt.setSoundVolume(m.target.getCategory(), targetVolume);
    }

    public static void updateSoundLevels() {
        if(opt == null) opt = MinecraftClient.getInstance().options;

        for(AudioState e : STATES) {
            if(!e.muted && opt != null) {
                int currentVolume = (int)(opt.getSoundVolume(e.target.getCategory()) * 100);
                e.unmuteVolume = currentVolume;
            }
        }
    }
}
