package tfar.hitmarker.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import tfar.hitmarker.HitMarker;

public class HitMarkerClient {
    public static final ResourceLocation HIT_TEXTURE = new ResourceLocation(HitMarker.MODID, "textures/hit.png");
    static int remainingTicks = 0;
    static boolean kill = false;

    public static void crosshair(Gui gui, PoseStack poseStack, float partialTick, int width, int height) {
        if (!Minecraft.getInstance().options.hideGui && HitMarkerClient.remainingTicks > 0) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableDepthTest();
            bind(HitMarkerClient.HIT_TEXTURE);
            if (HitMarkerClient.kill) {
                RenderSystem.setShaderColor(1, 0, 0, 1);
            }
            GuiComponent.blit(poseStack, (width - 11) / 2, (height - 11) / 2, gui.getBlitOffset(), 0.0F, 0.0F, 11, 11, 11, 11);
            bind(Gui.GUI_ICONS_LOCATION);
        }
    }

    public static void clientTick() {
        if (remainingTicks > 0) {
            remainingTicks--;
        }
    }

    public static void receiveHit(boolean kill) {
        remainingTicks = 20;
        HitMarkerClient.kill = kill;
        Minecraft.getInstance().player.playSound(HitMarker.HIT, .4f, 1);
    }

    public static void bind(ResourceLocation tex) {
        RenderSystem.setShaderTexture(0, tex);
    }
}