package net.treset.audio_hotkeys.config;

import net.minecraft.client.MinecraftClient;
import net.treset.audio_hotkeys.HotkeyClient;
import net.treset.audio_hotkeys.tools.KeybindTools;
import net.treset.vanillaconfig.config.IntegerConfig;
import net.treset.vanillaconfig.config.KeybindConfig;
import net.treset.vanillaconfig.config.PageConfig;
import net.treset.vanillaconfig.config.base.BaseConfig;
import net.treset.vanillaconfig.config.managers.SaveLoadManager;
import net.treset.vanillaconfig.config.version.ConfigVersion;
import net.treset.vanillaconfig.screen.ConfigScreen;

public class Config {

    public static final PageConfig MAIN_PAGE = new PageConfig("config.audiohotkeys.main.page");
    public static final PageConfig MASTER_PAGE = new PageConfig("config.audiohotkeys.master.page");
    public static final PageConfig MUSIC_PAGE = new PageConfig("config.audiohotkeys.music.page");
    public static final PageConfig INGAME_PAGE = new PageConfig("config.audiohotkeys.ingame.page");
    public static final PageConfig CREATURES_PAGE = new PageConfig("config.audiohotkeys.creatures.page");
    public static final PageConfig OTHER_PAGE = new PageConfig("config.audiohotkeys.other.page");

    public static final KeybindConfig OPEN_CONFIG = new KeybindConfig(new int[]{0x18 /*O*/}, 0, 10, "config.audiohotkeys.open_config", "config.audiohotkeys.open_config.comment");

    public static class Master {
        public static final KeybindConfig MASTER_MUTE =     new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.master.mute",      "config.audiohotkeys.master.mute.comment");
        public static final KeybindConfig MASTER_UP =       new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.master.up",        "config.audiohotkeys.master.up.comment");
        public static final KeybindConfig MASTER_DOWN =     new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.master.down",      "config.audiohotkeys.master.down.comment");
        public static final IntegerConfig MASTER_STEP =     new IntegerConfig(5, 1, 50,     "config.audiohotkeys.master.step",      "config.audiohotkeys.master.step.comment");
        public static final KeybindConfig SUBTITLES_MUTE =  new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.subtitles.mute",   "config.audiohotkeys.subtitles.mute.comment");

        public static final BaseConfig[] OPTIONS = new BaseConfig[] {
                MASTER_MUTE,
                MASTER_UP,
                MASTER_DOWN,
                MASTER_STEP,
                SUBTITLES_MUTE
        };

        static {
            MASTER_UP.setFullWidth(false);
            MASTER_DOWN.setFullWidth(false);
            MASTER_STEP.setSlider(true);
        }
    }

    public static class Music {
        public static final KeybindConfig MUSIC_MUTE =      new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.music.mute",      "config.audiohotkeys.music.mute.comment");
        public static final KeybindConfig MUSIC_UP =        new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.music.up",        "config.audiohotkeys.music.up.comment");
        public static final KeybindConfig MUSIC_DOWN =      new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.music.down",      "config.audiohotkeys.music.down.comment");
        public static final IntegerConfig MUSIC_STEP =      new IntegerConfig(5, 1, 50,     "config.audiohotkeys.music.step",      "config.audiohotkeys.music.step.comment");
        public static final KeybindConfig JUKEBOX_MUTE =    new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.jukebox.mute",      "config.audiohotkeys.jukebox.mute.comment");
        public static final KeybindConfig JUKEBOX_UP =      new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.jukebox.up",        "config.audiohotkeys.jukebox.up.comment");
        public static final KeybindConfig JUKEBOX_DOWN =    new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.jukebox.down",      "config.audiohotkeys.jukebox.down.comment");
        public static final IntegerConfig JUKEBOX_STEP =    new IntegerConfig(5, 1, 50,     "config.audiohotkeys.jukebox.step",      "config.audiohotkeys.jukebox.step.comment");

        public static final BaseConfig[] OPTIONS = new BaseConfig[] {
                MUSIC_MUTE,
                MUSIC_UP,
                MUSIC_DOWN,
                MUSIC_STEP,
                JUKEBOX_MUTE,
                JUKEBOX_UP,
                JUKEBOX_DOWN,
                JUKEBOX_STEP
        };

        static {
            MUSIC_UP.setFullWidth(false);
            MUSIC_DOWN.setFullWidth(false);
            MUSIC_STEP.setSlider(true);
            JUKEBOX_UP.setFullWidth(false);
            JUKEBOX_DOWN.setFullWidth(false);
            JUKEBOX_STEP.setSlider(true);
        }
    }

    public static class Ingame {
        public static final KeybindConfig ENVIRONMENT_MUTE= new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.environment.mute",      "config.audiohotkeys.environment.mute.comment");
        public static final KeybindConfig ENVIRONMENT_UP =  new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.environment.up",        "config.audiohotkeys.environment.up.comment");
        public static final KeybindConfig ENVIRONMENT_DOWN= new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.environment.down",      "config.audiohotkeys.environment.down.comment");
        public static final IntegerConfig ENVIRONMENT_STEP= new IntegerConfig(5, 1, 50,     "config.audiohotkeys.environment.step",      "config.audiohotkeys.environment.step.comment");
        public static final KeybindConfig WEATHER_MUTE =    new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.weather.mute",      "config.audiohotkeys.weather.mute.comment");
        public static final KeybindConfig WEATHER_UP =      new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.weather.up",        "config.audiohotkeys.weather.up.comment");
        public static final KeybindConfig WEATHER_DOWN =    new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.weather.down",      "config.audiohotkeys.weather.down.comment");
        public static final IntegerConfig WEATHER_STEP =    new IntegerConfig(5, 1, 50,     "config.audiohotkeys.weather.step",      "config.audiohotkeys.weather.step.comment");
        public static final KeybindConfig BLOCKS_MUTE =     new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.blocks.mute",      "config.audiohotkeys.blocks.mute.comment");
        public static final KeybindConfig BLOCKS_UP =       new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.blocks.up",        "config.audiohotkeys.blocks.up.comment");
        public static final KeybindConfig BLOCKS_DOWN =     new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.blocks.down",      "config.audiohotkeys.blocks.down.comment");
        public static final IntegerConfig BLOCKS_STEP =     new IntegerConfig(5, 1, 50,     "config.audiohotkeys.blocks.step",      "config.audiohotkeys.blocks.step.comment");

        public static final BaseConfig[] OPTIONS = new BaseConfig[] {
                ENVIRONMENT_MUTE,
                ENVIRONMENT_UP,
                ENVIRONMENT_DOWN,
                ENVIRONMENT_STEP,
                WEATHER_MUTE,
                WEATHER_UP,
                WEATHER_DOWN,
                WEATHER_STEP,
                BLOCKS_MUTE,
                BLOCKS_UP,
                BLOCKS_DOWN,
                BLOCKS_STEP
        };

        static {
            ENVIRONMENT_UP.setFullWidth(false);
            ENVIRONMENT_DOWN.setFullWidth(false);
            ENVIRONMENT_STEP.setSlider(true);
            WEATHER_UP.setFullWidth(false);
            WEATHER_DOWN.setFullWidth(false);
            WEATHER_STEP.setSlider(true);
            BLOCKS_UP.setFullWidth(false);
            BLOCKS_DOWN.setFullWidth(false);
            BLOCKS_STEP.setSlider(true);
        }
    }

    public static class Creatures {
        public static final KeybindConfig HOSTILE_MUTE =    new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.hostile.mute",      "config.audiohotkeys.hostile.mute.comment");
        public static final KeybindConfig HOSTILE_UP =      new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.hostile.up",        "config.audiohotkeys.hostile.up.comment");
        public static final KeybindConfig HOSTILE_DOWN =    new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.hostile.down",      "config.audiohotkeys.hostile.down.comment");
        public static final IntegerConfig HOSTILE_STEP =    new IntegerConfig(5, 1, 50,     "config.audiohotkeys.hostile.step",      "config.audiohotkeys.hostile.step.comment");
        public static final KeybindConfig FRIENDLY_MUTE =   new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.friendly.mute",      "config.audiohotkeys.friendly.mute.comment");
        public static final KeybindConfig FRIENDLY_UP =     new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.friendly.up",        "config.audiohotkeys.friendly.up.comment");
        public static final KeybindConfig FRIENDLY_DOWN =   new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.friendly.down",      "config.audiohotkeys.friendly.down.comment");
        public static final IntegerConfig FRIENDLY_STEP =   new IntegerConfig(5, 1, 50,     "config.audiohotkeys.friendly.step",      "config.audiohotkeys.friendly.step.comment");
        public static final KeybindConfig PLAYER_MUTE =     new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.player.mute",      "config.audiohotkeys.player.mute.comment");
        public static final KeybindConfig PLAYER_UP =       new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.player.up",        "config.audiohotkeys.player.up.comment");
        public static final KeybindConfig PLAYER_DOWN =     new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.player.down",      "config.audiohotkeys.player.down.comment");
        public static final IntegerConfig PLAYER_STEP =     new IntegerConfig(5, 1, 50,     "config.audiohotkeys.player.step",      "config.audiohotkeys.player.step.comment");

        public static final BaseConfig[] OPTIONS = new BaseConfig[] {
                HOSTILE_MUTE,
                HOSTILE_UP,
                HOSTILE_DOWN,
                HOSTILE_STEP,
                FRIENDLY_MUTE,
                FRIENDLY_UP,
                FRIENDLY_DOWN,
                FRIENDLY_STEP,
                PLAYER_MUTE,
                PLAYER_UP,
                PLAYER_DOWN,
                PLAYER_STEP
        };

        static {
            HOSTILE_UP.setFullWidth(false);
            HOSTILE_DOWN.setFullWidth(false);
            HOSTILE_STEP.setSlider(true);
            FRIENDLY_UP.setFullWidth(false);
            FRIENDLY_DOWN.setFullWidth(false);
            FRIENDLY_STEP.setSlider(true);
            PLAYER_UP.setFullWidth(false);
            PLAYER_DOWN.setFullWidth(false);
            PLAYER_STEP.setSlider(true);
        }
    }

    public static class Other {
        public static final KeybindConfig VOICE_MUTE =      new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.voice.mute",      "config.audiohotkeys.voice.mute.comment");
        public static final KeybindConfig VOICE_UP =        new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.voice.up",        "config.audiohotkeys.voice.up.comment");
        public static final KeybindConfig VOICE_DOWN =      new KeybindConfig(new int[]{}, 0, 10,   "config.audiohotkeys.voice.down",      "config.audiohotkeys.voice.down.comment");
        public static final IntegerConfig VOICE_STEP =      new IntegerConfig(5, 1, 50,     "config.audiohotkeys.voice.step",      "config.audiohotkeys.voice.step.comment");

        public static final BaseConfig[] OPTIONS = new BaseConfig[]{
                VOICE_MUTE,
                VOICE_UP,
                VOICE_DOWN,
                VOICE_STEP
        };

        static {
            VOICE_UP.setFullWidth(false);
            VOICE_DOWN.setFullWidth(false);
            VOICE_STEP.setSlider(true);
        }
    }

    public static class Lists {
        public static final KeybindConfig[] KEYBIND_CONFIGS = new KeybindConfig[] {
                OPEN_CONFIG,
                Master.MASTER_MUTE,
                Master.MASTER_UP,
                Master.MASTER_DOWN,
                Master.SUBTITLES_MUTE,
                Music.MUSIC_MUTE,
                Music.MUSIC_UP,
                Music.MUSIC_DOWN,
                Music.JUKEBOX_MUTE,
                Music.JUKEBOX_UP,
                Music.JUKEBOX_DOWN,
                Ingame.ENVIRONMENT_MUTE,
                Ingame.ENVIRONMENT_UP,
                Ingame.ENVIRONMENT_DOWN,
                Ingame.WEATHER_MUTE,
                Ingame.WEATHER_UP,
                Ingame.WEATHER_DOWN,
                Ingame.BLOCKS_MUTE,
                Ingame.BLOCKS_UP,
                Ingame.BLOCKS_DOWN,
                Creatures.HOSTILE_MUTE,
                Creatures.HOSTILE_UP,
                Creatures.HOSTILE_DOWN,
                Creatures.FRIENDLY_MUTE,
                Creatures.FRIENDLY_UP,
                Creatures.FRIENDLY_DOWN,
                Creatures.PLAYER_MUTE,
                Creatures.PLAYER_UP,
                Creatures.PLAYER_DOWN,
                Other.VOICE_MUTE,
                Other.VOICE_UP,
                Other.VOICE_DOWN,
        };

        public static final IntegerConfig[] INTEGER_CONFIGS = new IntegerConfig[] {
                Master.MASTER_STEP,
                Music.MUSIC_STEP,
                Music.JUKEBOX_STEP,
                Ingame.ENVIRONMENT_STEP,
                Ingame.WEATHER_STEP,
                Ingame.BLOCKS_STEP,
                Creatures.HOSTILE_STEP,
                Creatures.FRIENDLY_STEP,
                Creatures.PLAYER_STEP,
                Other.VOICE_STEP
        };
    }

    public static void init() {
        MAIN_PAGE.setOptions(new BaseConfig[]{
                MASTER_PAGE,
                MUSIC_PAGE,
                INGAME_PAGE,
                CREATURES_PAGE,
                OTHER_PAGE,
                OPEN_CONFIG
        });

        MASTER_PAGE.setOptions(Master.OPTIONS);
        MUSIC_PAGE.setOptions(Music.OPTIONS);
        INGAME_PAGE.setOptions(Ingame.OPTIONS);
        CREATURES_PAGE.setOptions(Creatures.OPTIONS);
        OTHER_PAGE.setOptions(Other.OPTIONS);

        for(int i = 0; i < Lists.KEYBIND_CONFIGS.length; i++) {
            Lists.KEYBIND_CONFIGS[i].onPressed(KeybindTools::resolveKeybinds);
        }

        MAIN_PAGE.setSaveName("audio_hotkeys");
        MAIN_PAGE.setPath("audio_hotkeys");

        MAIN_PAGE.loadVersion();

        if(!MAIN_PAGE.hasVersion()) MalilibMigration.migrateFromMalilib();

        States.init();

        MAIN_PAGE.setVersion(new ConfigVersion("1.0.0"));

        SaveLoadManager.globalSaveConfig(MAIN_PAGE);

        HotkeyClient.CONFIG_SCREEN = new ConfigScreen(MAIN_PAGE, MinecraftClient.getInstance().currentScreen);
    }
}
