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
    private static HashMap<String, String> entityDeathConfigMap;
    private static HashMap<String, String> otherDeathConfigMap;

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
                configTemplate.otherDeaths = new DeathConfig[] { new DeathConfig() };
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
        // serialize entity death configs
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            for (EntityDeathConfig entityDeathConfig : config.entityDeaths) {
                if (entityDeathConfig.players.length != 0) {
                    for (String playerName : entityDeathConfig.players) {
                        hashMap.putIfAbsent(entityDeathConfig.attacker + ":any:" + playerName,
                                entityDeathConfig.deathMessage);
                        hashMap.putIfAbsent(
                                entityDeathConfig.attacker + ":" + entityDeathConfig.type + ":" + playerName,
                                entityDeathConfig.deathMessage);
                    }
                } else {
                    hashMap.putIfAbsent(entityDeathConfig.attacker + ":any", entityDeathConfig.deathMessage);
                    hashMap.putIfAbsent(entityDeathConfig.attacker + ":" + entityDeathConfig.type,
                            entityDeathConfig.deathMessage);
                }
            }
            entityDeathConfigMap = hashMap;
        }
         // serialize other death configs
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            for (DeathConfig otherDeathConfig : config.otherDeaths) {
                if (otherDeathConfig.players.length != 0) {
                    for (String playerName : otherDeathConfig.players) {
                        hashMap.putIfAbsent(otherDeathConfig.type + ":" + playerName,
                                otherDeathConfig.deathMessage);
                    }
                } else {
                    hashMap.putIfAbsent(otherDeathConfig.type, otherDeathConfig.deathMessage);
                }
            }
            otherDeathConfigMap = hashMap;
        }
    }

    public static HashMap<String, String> getEntityDeathConfig() {
        return entityDeathConfigMap;
    }

    public static HashMap<String, String> getOtherDeathConfig() {
        return otherDeathConfigMap;
    }

    private static Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir().resolve("girlbossed.json");
    }
}

class Config {
    EntityDeathConfig[] entityDeaths;
    DeathConfig[] otherDeaths;
}

class EntityDeathConfig extends DeathConfig {
    String attacker;
}

class DeathConfig {
    String type;
    String deathMessage;
    String[] players;
}