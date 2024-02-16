package tfar.hitmarker.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import tfar.hitmarker.HitMarker;

public class HitMarkerClientForge {

    public static void clientSetup(FMLClientSetupEvent e) {
        MinecraftForge.EVENT_BUS.addListener(HitMarkerClientForge::tick);
    }

    public static void rOverlay(RegisterGuiOverlaysEvent e) {
        e.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), HitMarker.MODID,overlay);
    }

    private static void tick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.START) {
            HitMarkerClient.clientTick();
        }
    }

    static IGuiOverlay overlay = HitMarkerClientForge::crosshair;

    private static void crosshair(ForgeGui gui, GuiGraphics graphics, float partialTick, int width, int height) {
        HitMarkerClient.crosshair(gui,graphics,partialTick,width,height);
    }
}
