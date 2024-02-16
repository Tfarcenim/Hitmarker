package tfar.hitmarker.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.hitmarker.HitMarker;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "die",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;awardKillScore(Lnet/minecraft/world/entity/Entity;ILnet/minecraft/world/damagesource/DamageSource;)V")
   )
    private void interceptKill(DamageSource damageSource, CallbackInfo ci) {
        HitMarker.death((LivingEntity)(Object)this,damageSource);
    }

    @Inject(method = "actuallyHurt",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;gameEvent(Lnet/minecraft/world/level/gameevent/GameEvent;)V"))
    private void onDamaged(DamageSource damageSource, float damageAmount, CallbackInfo ci) {
        HitMarker.hit((LivingEntity)(Object)this,damageSource);
    }
}
