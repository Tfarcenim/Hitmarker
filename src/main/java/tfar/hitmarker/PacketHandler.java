package tfar.hitmarker;

import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.resources.ResourceLocation;

public class PacketHandler {
    public static final ResourceLocation sync_hit = new ResourceLocation("hitmarker", "sync_hit");

    public PacketHandler() {
    }

    public static void registerClientMessages() {
        ClientSidePacketRegistry.INSTANCE.register(sync_hit, new S2CPlayHitMarker());
    }
}
