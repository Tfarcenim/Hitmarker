package tfar.hitmarker.platform;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import tfar.hitmarker.network.C2SModPacket;
import tfar.hitmarker.network.PacketHandlerForge;
import tfar.hitmarker.network.S2CModPacket;
import tfar.hitmarker.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public void sendToClient(S2CModPacket msg, ResourceLocation channel, ServerPlayer player) {
        PacketHandlerForge.INSTANCE.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public void sendToServer(C2SModPacket msg, ResourceLocation channel) {
        PacketHandlerForge.INSTANCE.sendToServer(msg);
    }

}