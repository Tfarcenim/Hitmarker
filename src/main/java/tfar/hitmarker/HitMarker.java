package tfar.hitmarker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class HitMarker implements ModInitializer {
	public static final String MODID = "hitmarker";
	public static SoundEvent HIT;

	public HitMarker() {
	}

	public void onInitialize() {
		HIT = Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation(MODID, "hit"), SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "hit")));
	}
}
