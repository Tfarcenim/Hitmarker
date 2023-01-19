package tfar.hitmarker;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class PacketHandler {
    public static final ResourceLocation sync_hit = new ResourceLocation("hitmarker", "sync_hit");

    public PacketHandler() {
    }

    public static void sendSyncHit(ServerPlayer player,boolean kill) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeBoolean(kill);
        ServerPlayNetworking.send(player, PacketHandler.sync_hit, buf);
    }
}
