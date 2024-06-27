package tfar.hitmarker.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;
import tfar.hitmarker.HitMarker;

public class ModClientNeoForge {

    public static void clientSetup(FMLClientSetupEvent e) {
        NeoForge.EVENT_BUS.addListener(ModClientNeoForge::tick);
    }

    public static void rOverlay(RegisterGuiLayersEvent e) {
        e.registerAbove(VanillaGuiLayers.CROSSHAIR, HitMarker.id(HitMarker.MODID),overlay);
    }

    private static void tick(ClientTickEvent.Post e) {
            HitMarkerClient.clientTick();
    }

    static LayeredDraw.Layer overlay = ModClientNeoForge::crosshair;

    private static void crosshair(GuiGraphics graphics, DeltaTracker partialTick) {
        HitMarkerClient.crosshair(graphics,partialTick);
    }
}
