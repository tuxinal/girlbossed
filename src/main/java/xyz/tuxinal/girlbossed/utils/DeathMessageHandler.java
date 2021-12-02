package xyz.tuxinal.girlbossed.utils;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class DeathMessageHandler {
    private static HashMap<String, String> entityDeathConfig = ConfigParser.getEntityDeathConfig();
    private static HashMap<String, String> otherDeathConfig = ConfigParser.getOtherDeathConfig();

    public static Text getDeathMessage(DamageTracker damageTracker) {
        DamageSource damageSource = damageTracker.getMostRecentDamage().getDamageSource();
        ServerPlayerEntity player = (ServerPlayerEntity) damageTracker.getEntity();
        if (damageSource instanceof EntityDamageSource) {
            String key = EntityType.getId(damageSource.getAttacker().getType()).toString() + ":"
                    + damageSource.getName();
            String anyKey = EntityType.getId(damageSource.getAttacker().getType()).toString() + ":any";
            String playerKey = EntityType.getId(damageSource.getAttacker().getType()).toString() + ":"
                    + player.getName().asString();
            String playerAnyKey = EntityType.getId(damageSource.getAttacker().getType()).toString() + ":any:"
                    + player.getName().asString();
            if (entityDeathConfig.containsKey(playerAnyKey)) {
                return new ReplacableText(entityDeathConfig.get(playerAnyKey), new Object[] {
                        player.getDisplayName(), damageSource.getAttacker().getDisplayName() });
            }
            if (entityDeathConfig.containsKey(playerKey)) {
                return new ReplacableText(entityDeathConfig.get(playerKey), new Object[] {
                        player.getDisplayName(), damageSource.getAttacker().getDisplayName() });
            }
            if (entityDeathConfig.containsKey(anyKey)) {
                return new ReplacableText(entityDeathConfig.get(anyKey), new Object[] {
                        player.getDisplayName(), damageSource.getAttacker().getDisplayName() });
            }
            if (entityDeathConfig.containsKey(key)) {
                return new ReplacableText(entityDeathConfig.get(key), new Object[] {
                    player.getDisplayName(), damageSource.getAttacker().getDisplayName() });
            }
        } else {
            String key = damageSource.getName();
            String playerKey = damageSource.getName() + ":" + player.getName().asString();
            if (otherDeathConfig.containsKey(playerKey)) {
                return new ReplacableText(otherDeathConfig.get(playerKey),
                        new Object[] { player.getDisplayName() });
            }
            if (otherDeathConfig.containsKey(key)) {
                return new ReplacableText(otherDeathConfig.get(key),
                        new Object[] { player.getDisplayName() });
            }
        }
        return damageTracker.getDeathMessage();
    }
}
