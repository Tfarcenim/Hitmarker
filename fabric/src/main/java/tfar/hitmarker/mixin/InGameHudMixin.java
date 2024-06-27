package tfar.hitmarker.mixin;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.hitmarker.client.HitMarkerClient;

@Mixin(Gui.class)
public class InGameHudMixin {

    @Inject(
            at = @At("RETURN"),
            method = "renderCrosshair"
    )
    private void drawHit(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HitMarkerClient.crosshair(guiGraphics,deltaTracker);
    }
}
