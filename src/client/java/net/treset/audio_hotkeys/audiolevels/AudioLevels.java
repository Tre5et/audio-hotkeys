package net.treset.audio_hotkeys.audiolevels;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.treset.audio_hotkeys.config.States;
import net.treset.audio_hotkeys.gui.AudioOverlay;
import net.treset.audio_hotkeys.gui.OverlayManager;
import net.treset.audio_hotkeys.gui.SoundSprite;
import net.treset.audio_hotkeys.tools.MathTools;
import net.treset.audio_hotkeys.tools.objects.AudioState;
import net.treset.audio_hotkeys.tools.objects.VolumeTarget;
import net.treset.vanillaconfig.config.BooleanConfig;
import net.treset.vanillaconfig.config.IntegerConfig;
import net.treset.vanillaconfig.config.base.BaseConfig;
import net.treset.vanillaconfig.config.config_type.ConfigType;
import net.treset.vanillaconfig.tools.TextTools;

public class AudioLevels {

    private static Options opt = Minecraft.getInstance().options;

    public static void toggleMute(VolumeTarget t) {
        if(t == VolumeTarget.SUBTITLES) {
            toggleSubtitles();
            return;
        }

        updateSoundLevels();

        for(AudioState e : States.STATES) {
            if(e.source == t) {
                setMute(e, !e.muted);
                if(e.muted) {
                    updateSoundLevels();

                    setSoundLevel(e, 0);
                    OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.mute"), TextTools.translateOrDefault(e.name)), SoundSprite.MUTE, 2000));
                } else {
                    opt = Minecraft.getInstance().options;

                    if(opt.getSoundSourceVolume(e.source.getCategory()) == 0) {
                        setSoundLevel(e, e.unmuteVolume);
                        OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.unmute"), TextTools.translateOrDefault(e.name), e.unmuteVolume), SoundSprite.UNMUTE, 2000));
                    } else {
                        int currentVolume = (int)(opt.getFinalSoundSourceVolume(e.source.getCategory()) * 100);
                        OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.unmute_failed"), TextTools.translateOrDefault(e.name), currentVolume), SoundSprite.UNMUTE, 2000));
                    }
                }
            }
        }

        updateSoundLevels();
    }

    public static void changeSoundLevel(VolumeTarget t, int value) {
        for(AudioState e : States.STATES) {
            if(e.source == t) {
                opt = Minecraft.getInstance().options;

                int currentVolume = (int)(opt.getSoundSourceVolume(e.source.getCategory()) * 100);
                int targetVolume = (int) MathTools.clamp(currentVolume + value, 0, 100);
                setSoundLevel(e, targetVolume);

                currentVolume = (int)(opt.getSoundSourceVolume(e.source.getCategory()) * 100);
                if(value > 0) OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.increase"), TextTools.translateOrDefault(e.name), Math.abs(value), currentVolume), SoundSprite.LOUDER, 2000));
                else OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.decrease"), TextTools.translateOrDefault(e.name), Math.abs(value), currentVolume), SoundSprite.QUIETER, 2000));
            }
        }

        updateSoundLevels();
    }

    public static void toggleSubtitles() {
        opt = Minecraft.getInstance().options;

        setMute(States.SUBTITLES, !States.SUBTITLES.muted);

        opt.showSubtitles().set(!States.SUBTITLES.muted);
        if(opt.showSubtitles().get()) {
            OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.activate"), TextTools.translateOrDefault(States.SUBTITLES.name)), SoundSprite.UNMUTE, 2000));
        } else {
            OverlayManager.drawOverlay(new AudioOverlay(String.format(TextTools.translateOrDefault("string.audiohotkeys.deactivate"), TextTools.translateOrDefault(States.SUBTITLES.name)), SoundSprite.MUTE, 2000));
        }
    }

    public static void setSoundLevel(AudioState m, int value) {
        opt = Minecraft.getInstance().options;

        double targetVolume = MathTools.clamp((float)value / 100, 0, 1);
        opt.getSoundSourceOptionInstance(m.source.getCategory()).set(targetVolume);
    }

    public static void updateSoundLevels() {
        opt = Minecraft.getInstance().options;

        for(AudioState e : States.STATES) {
            if(e != States.SUBTITLES && !e.muted && opt != null) {
                setUnmuteVolume(e, (int)(opt.getSoundSourceVolume(e.source.getCategory()) * 100));
            }
        }

        if(opt != null) opt.save();
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
