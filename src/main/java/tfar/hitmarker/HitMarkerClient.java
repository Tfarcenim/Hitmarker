package tfar.hitmarker;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;

public class HitMarkerClient implements ClientModInitializer {
    public static int remainingTicks = 0;
    public static boolean kill;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            if (remainingTicks > 0) {
                --remainingTicks;
            }
        });
        registerClientMessages();
    }
    public static void registerClientMessages() {
        ClientPlayNetworking.registerGlobalReceiver(PacketHandler.sync_hit, new S2CPlayHitMarker());
    }
    public static void playHitMarkerEffect(boolean kill) {
        remainingTicks = 20;
        HitMarkerClient.kill = kill;
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(HitMarker.HIT, 1.0F));
    }
}
