package xyz.tuxinal.girlbossed.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import xyz.tuxinal.girlbossed.utils.DeathMessageHandler;

@Mixin(ServerPlayerEntity.class)
public class DeathMessageMixin {
    @Redirect(method = "onDeath()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageTracker;getDeathMessage()Lnet/minecraft/text/Text;"))
    private Text deathMessage(DamageTracker damageTracker) {
        return DeathMessageHandler.getDeathMessage(damageTracker);
    }
}
