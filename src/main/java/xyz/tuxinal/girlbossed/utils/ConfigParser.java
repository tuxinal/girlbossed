package xyz.tuxinal.girlbossed.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.loader.api.FabricLoader;

public class ConfigParser {
    private static Config config;
    private static Logger LOGGER = LogManager.getLogger();

    public static void init() {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        File configFile = getConfigPath().toFile();
        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
                FileWriter writer = new FileWriter(configFile);
                Config configTemplate = new Config();
                configTemplate.entityDeaths = new EntityDeathConfig[] { new EntityDeathConfig() };
                configTemplate.otherDeaths = new OtherDeathConfig[] { new OtherDeathConfig() };
                writer.write(gson.toJson(configTemplate));
                writer.close();
            }
            FileReader reader = new FileReader(configFile);
            config = gson.fromJson(IOUtils.toString(reader), Config.class);
        } catch (IOException e) {
            LOGGER.catching(e);
        } catch (JsonSyntaxException e) {
            LOGGER.catching(e);
        }
    }

    public static HashMap<String, String> getEntityDeathConfig() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (EntityDeathConfig entityDeathConfig : config.entityDeaths) {
            // Death messages are gonna be stored as "identifier:deathtype":"death message"
            // so "minecraft:pillager:arrow":"${player} got shot by a pillager!"
            hashMap.putIfAbsent(entityDeathConfig.attacker + ":any", entityDeathConfig.deathMessage);
            hashMap.putIfAbsent(entityDeathConfig.attacker + ":" + entityDeathConfig.type, entityDeathConfig.deathMessage);
        }
        return hashMap;
    }

    public static HashMap<String, String> getOtherDeathConfig() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (OtherDeathConfig otherDeathConfig : config.otherDeaths) {
            hashMap.putIfAbsent(otherDeathConfig.type, otherDeathConfig.deathMessage);
        }
        return hashMap;
    }

    private static Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir().resolve("girlbossed.json");
    }
}

class Config {
    EntityDeathConfig[] entityDeaths;
    OtherDeathConfig[] otherDeaths;
}

class EntityDeathConfig {
    String type;
    String attacker;
    String deathMessage;
}

class OtherDeathConfig {
    String type;
    String deathMessage;
}