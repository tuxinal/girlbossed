package xyz.tuxinal.girlbossed.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import xyz.tuxinal.girlbossed.utils.DeathMessageHandler;

@Mixin(ServerPlayer.class)
public class DeathMessageMixin {
    @Redirect(method = "Lnet/minecraft/server/level/ServerPlayer;die(Lnet/minecraft/world/damagesource/DamageSource;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/CombatTracker;getDeathMessage()Lnet/minecraft/network/chat/Component;"))
    private Component deathMessage(CombatTracker tracker) {
        return DeathMessageHandler.getDeathMessage(tracker, (ServerPlayer) (Object) this);
    }
}
