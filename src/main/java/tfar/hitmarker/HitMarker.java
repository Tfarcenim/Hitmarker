package tfar.hitmarker;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class HitMarker implements ModInitializer, ClientModInitializer {
	public static final String MODID = "hitmarker";
	public static int remainingTicks = 0;
	public static SoundEvent HIT;

	public HitMarker() {
	}

	public void onInitialize() {
		HIT = Registry.register(Registry.SOUND_EVENT, new ResourceLocation(MODID, "hit"), new SoundEvent(new ResourceLocation(MODID, "hit")));
	}

	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register((minecraftClient) -> {
			if (remainingTicks > 0) {
				--remainingTicks;
			}
		});
		PacketHandler.registerClientMessages();
	}

	public static void playHitMarkerEffect() {
		remainingTicks = 20;
		Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(HIT, 1.0F));
	}
}
