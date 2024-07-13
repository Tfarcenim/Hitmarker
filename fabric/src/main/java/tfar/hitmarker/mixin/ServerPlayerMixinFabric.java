package tfar.hitmarker.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.hitmarker.HitMarker;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixinFabric {

    @Inject(method = "die",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;awardKillScore(Lnet/minecraft/world/entity/Entity;ILnet/minecraft/world/damagesource/DamageSource;)V")
   )
    private void interceptKill(DamageSource damageSource, CallbackInfo ci) {
        HitMarker.death((LivingEntity)(Object)this,damageSource);
    }
}
