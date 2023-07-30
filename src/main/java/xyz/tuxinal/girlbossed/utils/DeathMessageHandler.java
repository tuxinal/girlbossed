package xyz.tuxinal.girlbossed.utils;

import java.util.HashMap;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class DeathMessageHandler {
    public static Component getDeathMessage(CombatTracker damageTracker, ServerPlayer player) {
        DamageSource damageSource = damageTracker.entries.get(damageTracker.entries.size()-1).source();

        if (damageSource.getEntity() != null) {
            HashMap<String, String> entityDeathConfig = ConfigParser.getEntityDeathConfig();

            Entity attacker = damageSource.getEntity();
            ResourceLocation attackerType = EntityType.getKey(attacker.getType());

            String key = attackerType.toString() + "+" + damageSource.getMsgId();
            String anyKey = attackerType.toString() + "+any";
            String playerKey = attackerType.toString() + "+" + damageSource.getMsgId() + "+"
                    + player.getName().getString();
            String playerAnyKey = attackerType.toString() + "+any+" + player.getName().getString();
            if (entityDeathConfig.containsKey(playerAnyKey)) {
                return MutableComponent.create(new ReplacableContents(entityDeathConfig.get(playerAnyKey),
                        new Object[] { player.getDisplayName(), attacker.getDisplayName() }));
            }
            if (entityDeathConfig.containsKey(playerKey)) {
                return MutableComponent.create(new ReplacableContents(entityDeathConfig.get(playerKey),
                        new Object[] { player.getDisplayName(), attacker.getDisplayName() }));
            }
            if (entityDeathConfig.containsKey(anyKey)) {
                return MutableComponent.create(new ReplacableContents(entityDeathConfig.get(anyKey),
                        new Object[] { player.getDisplayName(), attacker.getDisplayName() }));
            }
            if (entityDeathConfig.containsKey(key)) {
                return MutableComponent.create(new ReplacableContents(entityDeathConfig.get(key),
                        new Object[] { player.getDisplayName(), attacker.getDisplayName() }));
            }
        } else {
            HashMap<String, String> otherDeathConfig = ConfigParser.getOtherDeathConfig();

            String key = damageSource.getMsgId();
            String playerKey = damageSource.getMsgId() + "+" + player.getName().getString();
            if (otherDeathConfig.containsKey(playerKey)) {
                return MutableComponent.create(new ReplacableContents(otherDeathConfig.get(playerKey),
                        new Object[] { player.getDisplayName() }));
            }
            if (otherDeathConfig.containsKey(key)) {
                return MutableComponent.create(new ReplacableContents(otherDeathConfig.get(key),
                        new Object[] { player.getDisplayName() }));
            }
        }
        return damageTracker.getDeathMessage();
    }
}
