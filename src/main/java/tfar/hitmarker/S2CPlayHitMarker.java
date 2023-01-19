package tfar.hitmarker;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;

public class S2CPlayHitMarker implements ClientPlayNetworking.PlayChannelHandler{
    public S2CPlayHitMarker() {
    }

    @Override
    public void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        boolean kill = buf.readBoolean();
        client.execute(() -> HitMarkerClient.playHitMarkerEffect(kill));
    }
}
