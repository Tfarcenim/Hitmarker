package tfar.hitmarker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import tfar.hitmarker.network.PacketHandler;

public class HitMarkerFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, HitMarker.HIT.getLocation(), HitMarker.HIT);
        PacketHandler.registerPackets();
    }
}
