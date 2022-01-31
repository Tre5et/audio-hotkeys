package net.treset.audio_hotkeys;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class HotkeyMod implements ModInitializer {

	public static final String MOD_ID = "audio_hotkeys";
	public static final String CONFIG_DIR_NAME = MOD_ID;
	public static final String CONFIG_FILE_NAME = MOD_ID + ".json";
	public static final String LEVELS_FILE_NAME = "levels.json";

	public static File CONFIG_DIR;

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
	}
}
