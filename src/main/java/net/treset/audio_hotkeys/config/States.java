package net.treset.audio_hotkeys.config;

import net.treset.audio_hotkeys.tools.objects.AudioState;
import net.treset.audio_hotkeys.tools.objects.VolumeTarget;
import net.treset.vanillaconfig.config.BooleanConfig;
import net.treset.vanillaconfig.config.IntegerConfig;
import net.treset.vanillaconfig.config.PageConfig;
import net.treset.vanillaconfig.config.base.BaseConfig;
import net.treset.vanillaconfig.config.config_type.ConfigType;
import net.treset.vanillaconfig.config.managers.SaveLoadManager;

public class States {

    public static BooleanConfig MASTER_MUTED = new BooleanConfig(false, "state.audiohotkeys.master.muted");
    public static IntegerConfig MASTER_UNMUTE = new IntegerConfig(100, 0, 100, "state.audiohotkeys.master.unmute");
    public static BooleanConfig SUBTITLES_MUTED = new BooleanConfig(false, "state.audiohotkeys.subtitles.muted");
    public static BooleanConfig MUSIC_MUTED = new BooleanConfig(false, "state.audiohotkeys.music.muted");
    public static IntegerConfig MUSIC_UNMUTE = new IntegerConfig(100, 0, 100, "state.audiohotkeys.music.unmute");
    public static BooleanConfig JUKEBOX_MUTED = new BooleanConfig(false, "state.audiohotkeys.jukebox.muted");
    public static IntegerConfig JUKEBOX_UNMUTE = new IntegerConfig(100, 0, 100, "state.audiohotkeys.jukebox.unmute");
    public static BooleanConfig ENVIRONMENT_MUTED = new BooleanConfig(false, "state.audiohotkeys.environment.muted");
    public static IntegerConfig ENVIRONMENT_UNMUTE = new IntegerConfig(100, 0, 100, "state.audiohotkeys.environment.unmute");
    public static BooleanConfig WEATHER_MUTED = new BooleanConfig(false, "state.audiohotkeys.weather.muted");
    public static IntegerConfig WEATHER_UNMUTE = new IntegerConfig(100, 0, 100, "state.audiohotkeys.weather.unmute");
    public static BooleanConfig BLOCKS_MUTED = new BooleanConfig(false, "state.audiohotkeys.blocks.muted");
    public static IntegerConfig BLOCKS_UNMUTE = new IntegerConfig(100, 0, 100, "state.audiohotkeys.blocks.unmute");
    public static BooleanConfig HOSTILE_MUTED = new BooleanConfig(false, "state.audiohotkeys.hostile.muted");
    public static IntegerConfig HOSTILE_UNMUTE = new IntegerConfig(100, 0, 100, "state.audiohotkeys.hostile.unmute");
    public static BooleanConfig FRIENDLY_MUTED = new BooleanConfig(false, "state.audiohotkeys.friendly.muted");
    public static IntegerConfig FRIENDLY_UNMUTE = new IntegerConfig(100, 0, 100, "state.audiohotkeys.friendly.unmute");
    public static BooleanConfig PLAYER_MUTED = new BooleanConfig(false, "state.audiohotkeys.player.muted");
    public static IntegerConfig PLAYER_UNMUTE = new IntegerConfig(100, 0, 100, "state.audiohotkeys.player.unmute");
    public static BooleanConfig VOICE_MUTED = new BooleanConfig(false, "state.audiohotkeys.voice.muted");
    public static IntegerConfig VOICE_UNMUTE = new IntegerConfig(100, 0, 100, "state.audiohotkeys.voice.unmute");

    public static final BaseConfig[] CONFIGS = new BaseConfig[]{
            MASTER_MUTED,
            MASTER_UNMUTE,
            SUBTITLES_MUTED,
            MUSIC_MUTED,
            MUSIC_UNMUTE,
            JUKEBOX_MUTED,
            JUKEBOX_UNMUTE,
            ENVIRONMENT_MUTED,
            ENVIRONMENT_UNMUTE,
            WEATHER_MUTED,
            WEATHER_UNMUTE,
            BLOCKS_MUTED,
            BLOCKS_UNMUTE,
            HOSTILE_MUTED,
            HOSTILE_UNMUTE,
            FRIENDLY_MUTED,
            FRIENDLY_UNMUTE,
            PLAYER_MUTED,
            PLAYER_UNMUTE,
            VOICE_MUTED,
            VOICE_UNMUTE
    };

    public static final PageConfig STATES_PAGE = new PageConfig("config.audiohotkeys.states.page", CONFIGS);

    public static AudioState MASTER = new AudioState("state.audiohotkeys.master", MASTER_MUTED.getBoolean(), MASTER_UNMUTE.getInteger(), VolumeTarget.MASTER);
    public static AudioState SUBTITLES = new AudioState("state.audiohotkeys.subtitles", SUBTITLES_MUTED.getBoolean(), 100, VolumeTarget.SUBTITLES);
    public static AudioState MUSIC = new AudioState("state.audiohotkeys.music", MUSIC_MUTED.getBoolean(), MUSIC_UNMUTE.getInteger(), VolumeTarget.MUSIC);
    public static AudioState JUKEBOX = new AudioState("state.audiohotkeys.jukebox", JUKEBOX_MUTED.getBoolean(), JUKEBOX_UNMUTE.getInteger(), VolumeTarget.JUKEBOX);
    public static AudioState ENVIRONMENT = new AudioState("state.audiohotkeys.environment", ENVIRONMENT_MUTED.getBoolean(), ENVIRONMENT_UNMUTE.getInteger(), VolumeTarget.ENVIRONMENT);
    public static AudioState WEATHER = new AudioState("state.audiohotkeys.weather", WEATHER_MUTED.getBoolean(), WEATHER_UNMUTE.getInteger(), VolumeTarget.WEATHER);
    public static AudioState BLOCKS = new AudioState("state.audiohotkeys.blocks", BLOCKS_MUTED.getBoolean(), BLOCKS_UNMUTE.getInteger(), VolumeTarget.BLOCKS);
    public static AudioState HOSTILE = new AudioState("state.audiohotkeys.hostile", HOSTILE_MUTED.getBoolean(), HOSTILE_UNMUTE.getInteger(), VolumeTarget.HOSTILE);
    public static AudioState FRIENDLY = new AudioState("state.audiohotkeys.friendly", FRIENDLY_MUTED.getBoolean(), FRIENDLY_UNMUTE.getInteger(), VolumeTarget.FRIENDLY);
    public static AudioState PLAYER = new AudioState("state.audiohotkeys.player", PLAYER_MUTED.getBoolean(), PLAYER_UNMUTE.getInteger(), VolumeTarget.PLAYER);
    public static AudioState VOICE = new AudioState("state.audiohotkeys.voice", VOICE_MUTED.getBoolean(), VOICE_UNMUTE.getInteger(), VolumeTarget.VOICE);

    public static final AudioState[] STATES = new AudioState[]{
        MASTER,
        SUBTITLES,
        MUSIC,
        JUKEBOX,
        ENVIRONMENT,
        WEATHER,
        BLOCKS,
        HOSTILE,
        FRIENDLY,
        PLAYER,
        VOICE
    };

    public static void init() {
        STATES_PAGE.setPath("audio_hotkeys");
        STATES_PAGE.setSaveName("states");

        if(!Config.MAIN_PAGE.hasVersion()) {
            STATES_PAGE.migrateFileFrom("audio_hotkeys/levels.json");

            for (BaseConfig e : CONFIGS) {
                if(e.getType() == ConfigType.BOOLEAN) {
                    e.migrateFrom(e.getKey().split("\\.")[2] + "_muted");
                } else if(e.getType() == ConfigType.INTEGER) {
                    e.migrateFrom(e.getKey().split("\\.")[2] + "_unmute_value");
                }
            }
        }

        STATES_PAGE.onLoad(States::onLoadPage);

        SaveLoadManager.globalSaveConfig(STATES_PAGE);
    }

    public static void onLoadPage(boolean success, String key) {
        for (AudioState a : STATES) {
            String name = a.name.split("\\.")[2];

            for (BaseConfig b : CONFIGS) {
                if(b.getType() == ConfigType.BOOLEAN && b.getKey().split("\\.")[2].equals(name) && b.getKey().split("\\.")[3].equals("muted"))
                    a.muted = ((BooleanConfig)b).getBoolean();
                else if(b.getType() == ConfigType.INTEGER && b.getKey().split("\\.")[2].equals(name) && b.getKey().split("\\.")[3].equals("unmute"))
                    a.unmuteVolume = ((IntegerConfig)b).getInteger();
            }
        }
    }
}
