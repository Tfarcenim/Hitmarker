package tfar.hitmarker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class HitMarkerFa implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation(HitMarker.MODID, "hit"), HitMarker.HIT);
    }
}
