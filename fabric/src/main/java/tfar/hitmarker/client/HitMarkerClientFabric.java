package tfar.hitmarker.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class HitMarkerClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPacketHandlerFabric.registerClientMessages();
        ClientTickEvents.START_CLIENT_TICK.register(m -> HitMarkerClient.clientTick());
    }
}
