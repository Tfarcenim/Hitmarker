package tfar.hitmarker.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import tfar.hitmarker.HitMarker;

public interface ModPacket {

    ResourceLocation HIT = new ResourceLocation(HitMarker.MODID,"hit");
    void write(FriendlyByteBuf to);

}
