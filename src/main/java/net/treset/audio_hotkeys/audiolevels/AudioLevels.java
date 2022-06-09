package net.treset.audio_hotkeys.audiolevels;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.treset.audio_hotkeys.config.States;
import net.treset.audio_hotkeys.gui.AudioOverlay;
import net.treset.audio_hotkeys.gui.OverlayManager;
import net.treset.audio_hotkeys.gui.TextureCoordinate;
import net.treset.audio_hotkeys.tools.MathTools;
import net.treset.audio_hotkeys.tools.objects.AudioState;
import net.treset.audio_hotkeys.tools.objects.VolumeTarget;
import net.treset.vanillaconfig.config.BooleanConfig;
import net.treset.vanillaconfig.config.IntegerConfig;
import net.treset.vanillaconfig.config.base.BaseConfig;
import net.treset.vanillaconfig.config.config_type.ConfigType;
import net.treset.vanillaconfig.tools.TextTools;

public class AudioLevels {

    private static GameOptions opt = MinecraftClient.getInstance().options;

    public static void toggleMute(VolumeTarget t) {
        if(t == VolumeTarget.SUBTITLES) {
            toggleSubtitles();
            return;
        }

        updateSoundLevels();

        for(AudioState e : States.STATES) {
            if(e.target == t) {
                setMute(e, !e.muted);
                if(e.muted) {
                    updateSoundLevels();

                    setSoundLevel(e, 0);
                    OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.mute"), TextTools.translateOrDefault(e.name)), TextureCoordinate.MUTE, 2000));
                } else {
                    if((opt = MinecraftClient.getInstance().options) == null) return;

                    if(opt.getSoundVolume(e.target.getCategory()) == 0) {
                        setSoundLevel(e, e.unmuteVolume);
                        OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.unmute"), TextTools.translateOrDefault(e.name), e.unmuteVolume), TextureCoordinate.UNMUTE, 2000));
                    } else {
                        int currentVolume = (int)(opt.getSoundVolume(e.target.getCategory()) * 100);
                        OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.unmute_failed"), TextTools.translateOrDefault(e.name), currentVolume), TextureCoordinate.UNMUTE, 2000));
                    }
                }
            }
        }

        updateSoundLevels();
    }

    public static void changeSoundLevel(VolumeTarget t, int value) {
        for(AudioState e : States.STATES) {
            if(e.target == t) {
                if((opt = MinecraftClient.getInstance().options) == null) return;

                int currentVolume = (int)(opt.getSoundVolume(e.target.getCategory()) * 100);
                int targetVolume = (int) MathTools.clamp(currentVolume + value, 0, 100);
                setSoundLevel(e, targetVolume);

                currentVolume = (int)(opt.getSoundVolume(e.target.getCategory()) * 100);
                if(value > 0) OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.increase"), TextTools.translateOrDefault(e.name), Math.abs(value), currentVolume), TextureCoordinate.LOUDER, 2000));
                else OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.decrease"), TextTools.translateOrDefault(e.name), Math.abs(value), currentVolume), TextureCoordinate.QUIETER, 2000));
            }
        }

        updateSoundLevels();
    }

    public static void toggleSubtitles() {
        if((opt = MinecraftClient.getInstance().options) == null) return;

        setMute(States.SUBTITLES, !States.SUBTITLES.muted);

        opt.getShowSubtitles().setValue(!States.SUBTITLES.muted);
        if(opt.getShowSubtitles().getValue()) {
            OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.activate"), TextTools.translateOrDefault(States.SUBTITLES.name)), TextureCoordinate.UNMUTE, 2000));
        } else {
            OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.deactivate"), TextTools.translateOrDefault(States.SUBTITLES.name)), TextureCoordinate.MUTE, 2000));
        }
    }

    public static void setSoundLevel(AudioState m, int value) {
        if((opt = MinecraftClient.getInstance().options) == null) return;

        float targetVolume = MathTools.clamp((float)value / 100, 0, 1);
        opt.setSoundVolume(m.target.getCategory(), targetVolume);
    }

    public static void updateSoundLevels() {
        if(opt == null) opt = MinecraftClient.getInstance().options;

        for(AudioState e : States.STATES) {
            if(!e.muted && opt != null) {
                setUnmuteVolume(e, (int)(opt.getSoundVolume(e.target.getCategory()) * 100));
            }
        }

        if(opt != null) opt.write();
    }

    public static void setMute(AudioState a, boolean mute) {
        String name = a.name.split("\\.")[2];
        for (BaseConfig e : States.CONFIGS) {
            if(e.getType() == ConfigType.BOOLEAN && e.getKey().split("\\.")[2].equals(name) && e.getKey().split("\\.")[3].equals("muted")) {
                ((BooleanConfig)e).setBoolean(mute);
                a.muted = ((BooleanConfig)e).getBoolean();
                break;
            }
        }
    }

    public static void setUnmuteVolume(AudioState a, int value) {
        String name = a.name.split("\\.")[2];
        for (BaseConfig e : States.CONFIGS) {
            if(e.getType() == ConfigType.INTEGER && e.getKey().split("\\.")[2].equals(name) && e.getKey().split("\\.")[3].equals("unmute")) {
                ((IntegerConfig)e).setInteger(value);
                a.unmuteVolume = ((IntegerConfig)e).getInteger();
            }
        }
    }
}
