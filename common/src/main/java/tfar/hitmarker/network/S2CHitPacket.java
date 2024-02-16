package tfar.hitmarker.network;

import net.minecraft.network.FriendlyByteBuf;
import tfar.hitmarker.client.HitMarkerClient;

public class S2CHitPacket implements S2CModPacket{

  private final boolean kill;

  public S2CHitPacket(boolean kill) {
    this.kill = kill;
  }

  public S2CHitPacket(FriendlyByteBuf buf) {
    kill = buf.readBoolean();
  }

  @Override
  public void write(FriendlyByteBuf buffer) {
    buffer.writeBoolean(kill);
  }

  public void handleClient() {
    HitMarkerClient.receiveHit(kill);
  }
}