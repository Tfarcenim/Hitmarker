package tfar.hitmarker.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import tfar.hitmarker.HitMarker;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class PacketHandlerForge {
  public static SimpleChannel INSTANCE;

  public static void registerMessages(String channelName) {
    int id = 0;

    INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(HitMarker.MODID, channelName), () -> "1.0", s -> true, s -> true);

    INSTANCE.registerMessage(id++, S2CHitPacket.class,
            S2CHitPacket::write,
            S2CHitPacket::new,
            wrapS2C());
  }

  private static <MSG extends S2CModPacket> BiConsumer<MSG, Supplier<NetworkEvent.Context>> wrapS2C() {
    return ((msg, contextSupplier) -> {
      contextSupplier.get().enqueueWork(msg::handleClient);
      contextSupplier.get().setPacketHandled(true);
    });
  }

  private static <MSG extends C2SModPacket> BiConsumer<MSG, Supplier<NetworkEvent.Context>> wrapC2S() {
    return ((msg, contextSupplier) -> {
      ServerPlayer player = contextSupplier.get().getSender();
      contextSupplier.get().enqueueWork(() -> msg.handleServer(player));
      contextSupplier.get().setPacketHandled(true);
    });
  }

}
