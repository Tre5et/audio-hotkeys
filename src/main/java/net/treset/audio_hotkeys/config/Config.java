package net.treset.audio_hotkeys.config;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import net.treset.audio_hotkeys.HotkeyMod;
import net.treset.audio_hotkeys.audiolevels.AudioLevels;
import net.treset.audio_hotkeys.tools.FileTools;
import net.treset.audio_hotkeys.tools.objects.ConfigVolumeHotkey;
import net.treset.audio_hotkeys.tools.objects.ConfigVolumeInteger;
import net.treset.audio_hotkeys.tools.objects.VolumeKeybindType;
import net.treset.audio_hotkeys.tools.objects.VolumeTarget;

import java.io.File;
import java.util.List;

public class Config implements IConfigHandler {

    public static final int CONFIG_VERSION = 0;

    public static class Master {
        public static final ConfigVolumeHotkey TOGGLE_MUTE = new ConfigVolumeHotkey(new ConfigHotkey("Toggle Mute", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle mute for the entire Minecraft audio."), VolumeKeybindType.TOGGLE, VolumeTarget.MASTER);
        public static final ConfigVolumeHotkey VOLUME_UP = new ConfigVolumeHotkey(new ConfigHotkey("Volume Up", "", KeybindSettings.PRESS_ALLOWEXTRA, "Increase the volume by amount specified in Volume Step."), VolumeKeybindType.UP, VolumeTarget.MASTER);
        public static final ConfigVolumeHotkey VOLUME_DOWN = new ConfigVolumeHotkey(new ConfigHotkey("Volume Down", "", KeybindSettings.PRESS_ALLOWEXTRA, "Decrease the volume by amount specified in Volume Step."), VolumeKeybindType.DOWN, VolumeTarget.MASTER);
        public static final ConfigVolumeInteger VOLUME_STEP = new ConfigVolumeInteger(new ConfigInteger("Volume Step", 5, "Amount the volume is increased / decreased in percent."), VolumeTarget.MASTER);
        public static final ConfigVolumeHotkey SUBTITLES_TOGGLE = new ConfigVolumeHotkey(new ConfigHotkey("Toggle Subtitles", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle audio subtitles."), VolumeKeybindType.SUBTITLES, VolumeTarget.SUBTITLES);

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                TOGGLE_MUTE.hotkey,
                VOLUME_UP.hotkey,
                VOLUME_DOWN.hotkey,
                VOLUME_STEP.integer,
                SUBTITLES_TOGGLE.hotkey
        );
    }

    public static class Music {
        public static final ConfigVolumeHotkey MUSIC_TOGGLE_MUTE = new ConfigVolumeHotkey( new ConfigHotkey("Toggle Music Mute", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle mute for music audio."), VolumeKeybindType.TOGGLE, VolumeTarget.MUSIC);
        public static final ConfigVolumeHotkey MUSIC_VOLUME_UP = new ConfigVolumeHotkey( new ConfigHotkey("Volume Music Up", "", KeybindSettings.PRESS_ALLOWEXTRA, "Increase the volume by amount specified in Volume Step."), VolumeKeybindType.UP, VolumeTarget.MUSIC);
        public static final ConfigVolumeHotkey MUSIC_VOLUME_DOWN = new ConfigVolumeHotkey( new ConfigHotkey("Volume Music Down", "", KeybindSettings.PRESS_ALLOWEXTRA, "Decrease the volume by amount specified in Volume Step."), VolumeKeybindType.DOWN, VolumeTarget.MUSIC);
        public static final ConfigVolumeInteger MUSIC_VOLUME_STEP = new ConfigVolumeInteger( new ConfigInteger("Volume Music Step", 5,  "Amount the volume is increased / decreased in percent."), VolumeTarget.MUSIC);
        public static final ConfigVolumeHotkey JUKEBOX_TOGGLE_MUTE = new ConfigVolumeHotkey( new ConfigHotkey("Toggle Jukebox/Note Blocks Mute", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle mute for Jukebox and Note Bock audio."), VolumeKeybindType.TOGGLE, VolumeTarget.JUKEBOX);
        public static final ConfigVolumeHotkey JUKEBOX_VOLUME_UP = new ConfigVolumeHotkey( new ConfigHotkey("Volume Jukebox/Note Blocks Up", "", KeybindSettings.PRESS_ALLOWEXTRA, "Increase the volume by amount specified in Volume Step."), VolumeKeybindType.UP, VolumeTarget.JUKEBOX);
        public static final ConfigVolumeHotkey JUKEBOX_VOLUME_DOWN = new ConfigVolumeHotkey( new ConfigHotkey("Volume Jukebox/Note Blocks Down", "", KeybindSettings.PRESS_ALLOWEXTRA, "Decrease the volume by amount specified in Volume Step."), VolumeKeybindType.DOWN, VolumeTarget.JUKEBOX);
        public static final ConfigVolumeInteger JUKEBOX_VOLUME_STEP = new ConfigVolumeInteger( new ConfigInteger("Volume Jukebox/Note Blocks Step", 5, "Amount the volume is increased / decreased in percent."), VolumeTarget.JUKEBOX);

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                MUSIC_TOGGLE_MUTE.hotkey,
                MUSIC_VOLUME_UP.hotkey,
                MUSIC_VOLUME_DOWN.hotkey,
                MUSIC_VOLUME_STEP.integer,
                JUKEBOX_TOGGLE_MUTE.hotkey,
                JUKEBOX_VOLUME_UP.hotkey,
                JUKEBOX_VOLUME_DOWN.hotkey,
                JUKEBOX_VOLUME_STEP.integer
        );
    }

    public static class Ingame {
        public static final ConfigVolumeHotkey ENVIRONMENT_TOGGLE_MUTE = new ConfigVolumeHotkey( new ConfigHotkey("Toggle Environment Mute", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle mute for environment audio."), VolumeKeybindType.TOGGLE, VolumeTarget.ENVIRONMENT);
        public static final ConfigVolumeHotkey ENVIRONMENT_VOLUME_UP = new ConfigVolumeHotkey( new ConfigHotkey("Volume Environment Up", "", KeybindSettings.PRESS_ALLOWEXTRA, "Increase the volume by amount specified in Volume Step."), VolumeKeybindType.UP, VolumeTarget.ENVIRONMENT);
        public static final ConfigVolumeHotkey ENVIRONMENT_VOLUME_DOWN = new ConfigVolumeHotkey(  new ConfigHotkey("Volume Environment Down", "", KeybindSettings.PRESS_ALLOWEXTRA, "Decrease the volume by amount specified in Volume Step."), VolumeKeybindType.DOWN, VolumeTarget.ENVIRONMENT);
        public static final ConfigVolumeInteger ENVIRONMENT_VOLUME_STEP = new ConfigVolumeInteger( new ConfigInteger("Volume Environment Step", 5,  "Amount the volume is increased / decreased in percent."), VolumeTarget.ENVIRONMENT);
        public static final ConfigVolumeHotkey WEATHER_TOGGLE_MUTE = new ConfigVolumeHotkey(  new ConfigHotkey("Toggle Weather Mute", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle mute for weather audio."), VolumeKeybindType.TOGGLE, VolumeTarget.WEATHER);
        public static final ConfigVolumeHotkey WEATHER_VOLUME_UP = new ConfigVolumeHotkey(  new ConfigHotkey("Volume Weather Up", "", KeybindSettings.PRESS_ALLOWEXTRA, "Increase the volume by amount specified in Volume Step."), VolumeKeybindType.UP, VolumeTarget.WEATHER);
        public static final ConfigVolumeHotkey WEATHER_VOLUME_DOWN = new ConfigVolumeHotkey(  new ConfigHotkey("Volume Weather Down", "", KeybindSettings.PRESS_ALLOWEXTRA, "Decrease the volume by amount specified in Volume Step."), VolumeKeybindType.DOWN, VolumeTarget.WEATHER);
        public static final ConfigVolumeInteger WEATHER_VOLUME_STEP = new ConfigVolumeInteger( new ConfigInteger("Volume Weather Step", 5, "Amount the volume is increased / decreased in percent."), VolumeTarget.WEATHER);
        public static final ConfigVolumeHotkey BLOCKS_TOGGLE_MUTE = new ConfigVolumeHotkey(  new ConfigHotkey("Toggle Blocks Mute", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle mute for block audio."), VolumeKeybindType.TOGGLE, VolumeTarget.BLOCKS);
        public static final ConfigVolumeHotkey BLOCKS_VOLUME_UP = new ConfigVolumeHotkey(  new ConfigHotkey("Volume Blocks Up", "", KeybindSettings.PRESS_ALLOWEXTRA, "Increase the volume by amount specified in Volume Step."), VolumeKeybindType.UP, VolumeTarget.BLOCKS);
        public static final ConfigVolumeHotkey BLOCKS_VOLUME_DOWN = new ConfigVolumeHotkey(  new ConfigHotkey("Volume Blocks Down", "", KeybindSettings.PRESS_ALLOWEXTRA, "Decrease the volume by amount specified in Volume Step."), VolumeKeybindType.DOWN, VolumeTarget.BLOCKS);
        public static final ConfigVolumeInteger BLOCKS_VOLUME_STEP = new ConfigVolumeInteger( new ConfigInteger("Volume Blocks Step", 5, "Amount the volume is increased / decreased in percent."), VolumeTarget.BLOCKS);

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                ENVIRONMENT_TOGGLE_MUTE.hotkey,
                ENVIRONMENT_VOLUME_UP.hotkey,
                ENVIRONMENT_VOLUME_DOWN.hotkey,
                ENVIRONMENT_VOLUME_STEP.integer,
                WEATHER_TOGGLE_MUTE.hotkey,
                WEATHER_VOLUME_UP.hotkey,
                WEATHER_VOLUME_DOWN.hotkey,
                WEATHER_VOLUME_STEP.integer,
                BLOCKS_TOGGLE_MUTE.hotkey,
                BLOCKS_VOLUME_UP.hotkey,
                BLOCKS_VOLUME_DOWN.hotkey,
                BLOCKS_VOLUME_STEP.integer
        );
    }

    public static class Creatures {
        public static final ConfigVolumeHotkey HOSTILE_TOGGLE_MUTE = new ConfigVolumeHotkey( new ConfigHotkey("Toggle Hostile Mobs Mute", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle mute for hostile mobs."), VolumeKeybindType.TOGGLE, VolumeTarget.HOSTILE);
        public static final ConfigVolumeHotkey HOSTILE_VOLUME_UP = new ConfigVolumeHotkey( new ConfigHotkey("Volume Hostile Mobs Up", "", KeybindSettings.PRESS_ALLOWEXTRA, "Increase the volume by amount specified in Volume Step."), VolumeKeybindType.UP, VolumeTarget.HOSTILE);
        public static final ConfigVolumeHotkey HOSTILE_VOLUME_DOWN = new ConfigVolumeHotkey( new ConfigHotkey("Volume Hostile Mobs Down", "", KeybindSettings.PRESS_ALLOWEXTRA, "Decrease the volume by amount specified in Volume Step."), VolumeKeybindType.DOWN, VolumeTarget.HOSTILE);
        public static final ConfigVolumeInteger HOSTILE_VOLUME_STEP = new ConfigVolumeInteger( new ConfigInteger("Volume Hostile Mobs Step", 5, "Amount the volume is increased / decreased in percent."), VolumeTarget.HOSTILE);
        public static final ConfigVolumeHotkey FRIENDLY_TOGGLE_MUTE = new ConfigVolumeHotkey( new ConfigHotkey("Toggle Friendly Mobs Mute", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle mute for friendly mobs."), VolumeKeybindType.TOGGLE, VolumeTarget.FRIENDLY);
        public static final ConfigVolumeHotkey FRIENDLY_VOLUME_UP = new ConfigVolumeHotkey( new ConfigHotkey("Volume Friendly Mobs Up", "", KeybindSettings.PRESS_ALLOWEXTRA, "Increase the volume by amount specified in Volume Step."), VolumeKeybindType.UP, VolumeTarget.FRIENDLY);
        public static final ConfigVolumeHotkey FRIENDLY_VOLUME_DOWN = new ConfigVolumeHotkey( new ConfigHotkey("Volume Friendly Mobs Down", "", KeybindSettings.PRESS_ALLOWEXTRA, "Decrease the volume by amount specified in Volume Step."), VolumeKeybindType.DOWN, VolumeTarget.FRIENDLY);
        public static final ConfigVolumeInteger FRIENDLY_VOLUME_STEP = new ConfigVolumeInteger( new ConfigInteger("Volume Friendly Mobs Step", 5, "Amount the volume is increased / decreased in percent."), VolumeTarget.FRIENDLY);
        public static final ConfigVolumeHotkey PLAYER_TOGGLE_MUTE = new ConfigVolumeHotkey( new ConfigHotkey("Toggle Players Mute", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle mute for players."), VolumeKeybindType.TOGGLE, VolumeTarget.PLAYER);
        public static final ConfigVolumeHotkey PLAYER_VOLUME_UP = new ConfigVolumeHotkey( new ConfigHotkey("Volume Players Up", "", KeybindSettings.PRESS_ALLOWEXTRA, "Increase the volume by amount specified in Volume Step."), VolumeKeybindType.UP, VolumeTarget.PLAYER);
        public static final ConfigVolumeHotkey PLAYER_VOLUME_DOWN = new ConfigVolumeHotkey( new ConfigHotkey("Volume Players Down", "", KeybindSettings.PRESS_ALLOWEXTRA,  "Decrease the volume by amount specified in Volume Step."), VolumeKeybindType.DOWN, VolumeTarget.PLAYER);
        public static final ConfigVolumeInteger PLAYER_VOLUME_STEP = new ConfigVolumeInteger( new ConfigInteger("Volume Players Step", 5, "Amount the volume is increased / decreased in percent."), VolumeTarget.PLAYER);

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                HOSTILE_TOGGLE_MUTE.hotkey,
                HOSTILE_VOLUME_UP.hotkey,
                HOSTILE_VOLUME_DOWN.hotkey,
                HOSTILE_VOLUME_STEP.integer,
                FRIENDLY_TOGGLE_MUTE.hotkey,
                FRIENDLY_VOLUME_UP.hotkey,
                FRIENDLY_VOLUME_DOWN.hotkey,
                FRIENDLY_VOLUME_STEP.integer,
                PLAYER_TOGGLE_MUTE.hotkey,
                PLAYER_VOLUME_UP.hotkey,
                PLAYER_VOLUME_DOWN.hotkey,
                PLAYER_VOLUME_STEP.integer
        );
    }

    public static class Other {
        public static final ConfigVolumeHotkey VOICE_TOGGLE_MUTE = new ConfigVolumeHotkey( new ConfigHotkey("Toggle Voice Mute", "", KeybindSettings.PRESS_ALLOWEXTRA, "Toggle mute for voice audio."), VolumeKeybindType.TOGGLE, VolumeTarget.VOICE);
        public static final ConfigVolumeHotkey VOICE_VOLUME_UP = new ConfigVolumeHotkey( new ConfigHotkey("Volume Voice Up", "", KeybindSettings.PRESS_ALLOWEXTRA, "Increase the volume by amount specified in Volume Step."), VolumeKeybindType.UP, VolumeTarget.VOICE);
        public static final ConfigVolumeHotkey VOICE_VOLUME_DOWN = new ConfigVolumeHotkey( new ConfigHotkey("Volume Voice Down", "", KeybindSettings.PRESS_ALLOWEXTRA, "Decrease the volume by amount specified in Volume Step."), VolumeKeybindType.DOWN, VolumeTarget.VOICE);
        public static final ConfigVolumeInteger VOICE_VOLUME_STEP = new ConfigVolumeInteger( new ConfigInteger("Volume Voice Step", 5, "Amount the volume is increased / decreased in percent."), VolumeTarget.VOICE);

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
          VOICE_TOGGLE_MUTE.hotkey,
          VOICE_VOLUME_UP.hotkey,
          VOICE_VOLUME_DOWN.hotkey,
          VOICE_VOLUME_STEP.integer
        );
    }

    public static final List<ConfigVolumeHotkey> VOLUME_HOTKEYS = ImmutableList.of(
            Master.TOGGLE_MUTE,
            Master.VOLUME_UP,
            Master.VOLUME_DOWN,
            Master.SUBTITLES_TOGGLE,

            Music.MUSIC_TOGGLE_MUTE,
            Music.MUSIC_VOLUME_UP,
            Music.MUSIC_VOLUME_DOWN,
            Music.JUKEBOX_TOGGLE_MUTE,
            Music.JUKEBOX_VOLUME_UP,
            Music.JUKEBOX_VOLUME_DOWN,

            Ingame.ENVIRONMENT_TOGGLE_MUTE,
            Ingame.ENVIRONMENT_VOLUME_UP,
            Ingame.ENVIRONMENT_VOLUME_DOWN,
            Ingame.WEATHER_TOGGLE_MUTE,
            Ingame.WEATHER_VOLUME_UP,
            Ingame.WEATHER_VOLUME_DOWN,
            Ingame.BLOCKS_TOGGLE_MUTE,
            Ingame.BLOCKS_VOLUME_UP,
            Ingame.WEATHER_VOLUME_DOWN,

            Creatures.HOSTILE_TOGGLE_MUTE,
            Creatures.HOSTILE_VOLUME_UP,
            Creatures.HOSTILE_VOLUME_DOWN,
            Creatures.FRIENDLY_TOGGLE_MUTE,
            Creatures.FRIENDLY_VOLUME_UP,
            Creatures.FRIENDLY_VOLUME_DOWN,
            Creatures.PLAYER_TOGGLE_MUTE,
            Creatures.PLAYER_VOLUME_UP,
            Creatures.PLAYER_VOLUME_DOWN,

            Other.VOICE_TOGGLE_MUTE,
            Other.VOICE_VOLUME_UP,
            Other.VOICE_VOLUME_DOWN
    );

    public static List<ConfigVolumeInteger> VOLUME_STEPS = ImmutableList.of(
            Master.VOLUME_STEP,

            Music.MUSIC_VOLUME_STEP,
            Music.JUKEBOX_VOLUME_STEP,

            Ingame.ENVIRONMENT_VOLUME_STEP,
            Ingame.WEATHER_VOLUME_STEP,
            Ingame.BLOCKS_VOLUME_STEP,

            Creatures.HOSTILE_VOLUME_STEP,
            Creatures.FRIENDLY_VOLUME_STEP,
            Creatures.PLAYER_VOLUME_STEP,

            Other.VOICE_VOLUME_STEP
    );

    public static void loadFromFile() {
        File configFile = new File(HotkeyMod.CONFIG_DIR, HotkeyMod.CONFIG_FILE_NAME);

        if (configFile.exists() && configFile.isFile() && configFile.canRead()) { //does file exist and is readable

            JsonObject obj;
            if ((obj = FileTools.readJsonFile(configFile)) != null) { //read file
                ConfigUtils.readConfigBase(obj, "Master", Config.Master.OPTIONS);
                ConfigUtils.readConfigBase(obj, "Music", Config.Music.OPTIONS);
                ConfigUtils.readConfigBase(obj, "Ingame", Ingame.OPTIONS);
                ConfigUtils.readConfigBase(obj, "Creatures", Config.Creatures.OPTIONS);
                ConfigUtils.readConfigBase(obj, "Other", Config.Other.OPTIONS);

            }
        }
    }

    public static void saveToFile() {
        File dir = HotkeyMod.CONFIG_DIR;

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) { //does config dir exist or create it

            JsonObject obj = new JsonObject();

            ConfigUtils.writeConfigBase(obj, "Master", Config.Master.OPTIONS);
            ConfigUtils.writeConfigBase(obj, "Music", Config.Music.OPTIONS);
            ConfigUtils.writeConfigBase(obj, "Ingame", Ingame.OPTIONS);
            ConfigUtils.writeConfigBase(obj, "Creatures", Config.Creatures.OPTIONS);
            ConfigUtils.writeConfigBase(obj, "Other", Config.Other.OPTIONS);
            obj.add("config_version", new JsonPrimitive(CONFIG_VERSION)); //add config file version option

            FileTools.writeJsonToFile(obj, new File(dir, HotkeyMod.CONFIG_FILE_NAME)); //write file
        }
    }

    @Override
    public void load() {
        loadFromFile();
        AudioLevels.loadSavedLevels();
    }

    @Override
    public void save() {
        saveToFile();
        AudioLevels.saveLevels();
    }
}
