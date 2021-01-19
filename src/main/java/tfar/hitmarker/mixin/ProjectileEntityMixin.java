package tfar.hitmarker.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.hitmarker.S2CPlayHitMarker;

@Mixin(Projectile.class)
abstract class ProjectileEntityMixin extends Entity {
	public ProjectileEntityMixin(EntityType<?> type, Level world) {
		super(type, world);
	}

	@Inject(
			method = "onHitEntity",
			at = @At("RETURN")
	)
	private void sendHitToPlayer(EntityHitResult entityHitResult, CallbackInfo ci) {
		if (((ProjectileEntityAccessor)this).getOwnerUUID() != null && !this.level.isClientSide) {
			Entity entity = ((ServerLevel)this.level).getEntity(((ProjectileEntityAccessor)this).getOwnerUUID());
			if (entity instanceof ServerPlayer) {
				S2CPlayHitMarker.send((ServerPlayer)entity);
			}
		}
	}
}
