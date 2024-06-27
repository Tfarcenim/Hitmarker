package tfar.hitmarker.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import tfar.hitmarker.client.HitMarkerClient;

public record S2CHitPacket(boolean kill) implements S2CModPacket {

  public static final CustomPacketPayload.Type<S2CHitPacket> TYPE = new CustomPacketPayload.Type<>(PacketHandler.packet(S2CHitPacket.class));
  public static final StreamCodec<RegistryFriendlyByteBuf, S2CHitPacket> STREAM_CODEC = StreamCodec.composite(
          ByteBufCodecs.BOOL,
          S2CHitPacket::kill,
          S2CHitPacket::new);

  public void handleClient() {
    HitMarkerClient.receiveHit(kill);
  }

  @Override
  public Type<S2CHitPacket> type() {
    return TYPE;
  }
}