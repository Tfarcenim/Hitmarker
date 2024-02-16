package tfar.hitmarker.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.hitmarker.client.HitMarkerClient;

@Mixin(Gui.class)
public class InGameHudMixin {

    @Shadow @Final private Minecraft minecraft;

    @Shadow private int screenWidth;

    @Shadow private int screenHeight;

    @Inject(
            at = @At("RETURN"),
            method = "renderCrosshair"
    )
    private void drawHit(GuiGraphics guiGraphics, CallbackInfo ci) {
        HitMarkerClient.crosshair(minecraft.gui,guiGraphics,minecraft.getDeltaFrameTime(), this.screenWidth, this.screenHeight);
    }
}
