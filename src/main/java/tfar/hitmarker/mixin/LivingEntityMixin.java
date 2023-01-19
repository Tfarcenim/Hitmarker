package tfar.hitmarker.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import tfar.hitmarker.PacketHandler;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "die",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;awardKillScore(Lnet/minecraft/world/entity/Entity;ILnet/minecraft/world/damagesource/DamageSource;)V")
    ,locals = LocalCapture.CAPTURE_FAILSOFT)
    private void interceptKill(DamageSource damageSource, CallbackInfo ci, Entity entity, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer player)
            PacketHandler.sendSyncHit(player,true);
    }
}
