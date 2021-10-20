package xyz.tuxinal.girlbossed.utils;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.text.Text;

public class DeathMessageHandler {
    private static HashMap<String, String> entityDeathConfig = ConfigParser.getEntityDeathConfig();
    private static HashMap<String, String> otherDeathConfig = ConfigParser.getOtherDeathConfig();
    private static Logger LOGGER = LogManager.getLogger();

    public static Text getDeathMessage(DamageTracker damageTracker) {
        DamageSource damageSource = damageTracker.getMostRecentDamage().getDamageSource();
        if (damageSource instanceof EntityDamageSource) {
            String key = EntityType.getId(damageSource.getAttacker().getType()).toString() + ":"
                    + damageSource.getName();
            String anyKey = EntityType.getId(damageSource.getAttacker().getType()).toString() + ":any";
            if (entityDeathConfig.containsKey(anyKey)) {
                return new ReplacableText(entityDeathConfig.get(anyKey), new Object[] {
                        damageTracker.getEntity().getDisplayName(), damageSource.getAttacker().getDisplayName() });
            }
            if (entityDeathConfig.containsKey(key)) {
                LOGGER.info(entityDeathConfig.get(key));
                Text returnValue = new ReplacableText(entityDeathConfig.get(key), new Object[] {
                        damageTracker.getEntity().getDisplayName(), damageSource.getAttacker().getDisplayName() });
                LOGGER.info(returnValue.getString());
                return returnValue;
            }
        } else {
            String key = damageSource.getName();
            if (otherDeathConfig.containsKey(key)) {
                return new ReplacableText(otherDeathConfig.get(key),
                        new Object[] { damageTracker.getEntity().getDisplayName() });
            }
        }
        return damageTracker.getDeathMessage();
    }
}
