package net.treset.audio_hotkeys.tools;

import com.google.gson.*;
import net.minecraft.client.MinecraftClient;
import net.treset.audio_hotkeys.HotkeyMod;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileTools {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static boolean loadOrCreateConfigDir() {
        File modDir = new File(new File(MinecraftClient.getInstance().runDirectory, "config"), HotkeyMod.CONFIG_DIR_NAME);
        if(modDir.exists() || modDir.mkdirs()) { //check if directory exists or create it
            HotkeyMod.CONFIG_DIR = modDir; //set config dir
            return true;
        }
        return false;
    }

    public static boolean writeJsonToFile(JsonObject obj, File file)
    {
        File fileTmp = new File(file.getParentFile(), file.getName() + ".tmp"); //create temporary storage file

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileTmp), StandardCharsets.UTF_8)) { //open writer
            writer.write(GSON.toJson(obj)); //write json to file
            writer.close();

            if (file.exists() && file.isFile() && !file.delete()) return false; //delete old file if exists

            return fileTmp.renameTo(file); //commit temporary file
        }
        catch (Exception e) {
            e.printStackTrace();
            HotkeyMod.LOGGER.info("Failed to write JSON data to file '{}'", fileTmp.getAbsolutePath());
        }
        return false;
    }

    public static JsonObject readJsonFile(File file) {
        if (file.exists() && file.isFile() && file.canRead()) { //file exists and can be read

            JsonElement elm;
            try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) { //open reader
                elm = JsonParser.parseReader(reader); //read file to json element
            } catch (Exception e) {
                e.printStackTrace();
                HotkeyMod.LOGGER.error("Failed to parse the JSON file '{}'", file.getAbsolutePath());
                return null;
            }

            return elm.getAsJsonObject(); //return json object
        }
        return null;
    }
}
