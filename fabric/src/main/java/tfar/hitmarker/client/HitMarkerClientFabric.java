package tfar.hitmarker.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import tfar.hitmarker.network.PacketHandler;

public class HitMarkerClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(m -> HitMarkerClient.clientTick());
    }
}
