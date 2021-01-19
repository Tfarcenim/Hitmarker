package tfar.hitmarker.mixin;

import java.util.UUID;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Projectile.class})
public interface ProjectileEntityAccessor {
    @Accessor
    UUID getOwnerUUID();
}
