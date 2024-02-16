package tfar.hitmarker;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tfar.hitmarker.network.ModPacket;
import tfar.hitmarker.network.S2CHitPacket;
import tfar.hitmarker.platform.Services;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class HitMarker {

    public static final String MODID = "hitmarker";
    public static final SoundEvent HIT = new SoundEvent(new ResourceLocation(MODID, "hit"));
    public static final String MOD_NAME = "HitMarker";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {
    }


    public static void hit(Entity damaged, DamageSource source) {
        sendToPlayer(false,source);
    }

    public static void death(Entity killed,DamageSource source) {
        sendToPlayer(true,source);
    }

    private static void sendToPlayer(boolean kill, DamageSource attacker) {
        if (attacker.getEntity() instanceof ServerPlayer player) {
            Services.PLATFORM.sendToClient(new S2CHitPacket(kill), ModPacket.HIT, player);
        }
    }

}