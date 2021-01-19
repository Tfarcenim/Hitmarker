package tfar.hitmarker;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class S2CPlayHitMarker implements PacketConsumer {
    public S2CPlayHitMarker() {
    }

    public static void send(ServerPlayer player) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, PacketHandler.sync_hit, buf);
    }

    public void accept(PacketContext packetContext, FriendlyByteBuf packetByteBuf) {
        packetContext.getTaskQueue().execute(HitMarker::playHitMarkerEffect);
    }
}
