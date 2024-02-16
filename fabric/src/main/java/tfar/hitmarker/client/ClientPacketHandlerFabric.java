package tfar.hitmarker.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import tfar.hitmarker.network.ClientHandler;
import tfar.hitmarker.network.ModPacket;
import tfar.hitmarker.network.S2CHitPacket;
import tfar.hitmarker.network.S2CModPacket;

import java.util.function.Function;

public class ClientPacketHandlerFabric {

    public static void registerClientMessages() {
        ClientPlayNetworking.registerGlobalReceiver(ModPacket.HIT, wrapS2C(S2CHitPacket::new));

    }

    public static <MSG extends S2CModPacket> ClientPlayNetworking.PlayChannelHandler wrapS2C(Function<FriendlyByteBuf,MSG> decodeFunction) {
        return new ClientHandler<>(decodeFunction);
    }
}
