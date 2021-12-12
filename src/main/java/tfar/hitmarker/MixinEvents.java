package tfar.hitmarker;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;

public class MixinEvents {
    public MixinEvents() {
    }

    private static final ResourceLocation LOCATION = new ResourceLocation("hitmarker", "textures/hit.png");

    public static void postRenderCrosshair(PoseStack matrices, Minecraft client, int scaledWidth, int scaledHeight) {
        if (HitMarker.remainingTicks > 0) {
            RenderSystem.setShaderTexture(0,LOCATION);
            GuiComponent.blit(matrices, (scaledWidth - 11) / 2, (scaledHeight - 11) / 2, 0.0F, 0.0F, 11, 11, 11, 11);
            RenderSystem.setShaderTexture(0,Gui.GUI_ICONS_LOCATION);
        }
    }
}
