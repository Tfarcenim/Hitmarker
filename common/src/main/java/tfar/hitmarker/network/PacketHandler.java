package tfar.hitmarker.network;


import net.minecraft.resources.ResourceLocation;
import tfar.hitmarker.HitMarker;
import tfar.hitmarker.platform.Services;

import java.util.Locale;

public class PacketHandler {

    public static void registerPackets() {
            registerClientPackets();
    }

    public static void registerClientPackets() {
        Services.PLATFORM.registerClientPacket(S2CHitPacket.TYPE,S2CHitPacket.STREAM_CODEC);

    }

    public static ResourceLocation packet(Class<?> clazz) {
        return HitMarker.id(clazz.getName().toLowerCase(Locale.ROOT));
    }

}
