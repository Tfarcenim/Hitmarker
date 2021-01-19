package tfar.hitmarker.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.hitmarker.MixinEvents;

@Mixin(Gui.class)
public class InGameHudMixin extends GuiComponent {

    @Shadow @Final private Minecraft minecraft;

    @Shadow private int screenWidth;

    @Shadow private int screenHeight;

    @Inject(
            at = @At("RETURN"),
            method = "renderCrosshair"
    )
    private void drawHit(PoseStack matrices, CallbackInfo ci) {
        MixinEvents.postRenderCrosshair(matrices, this.minecraft, this.screenWidth, this.screenHeight);
    }
}
