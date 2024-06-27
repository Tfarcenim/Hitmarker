package tfar.hitmarker;

import net.minecraft.core.registries.Registries;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import tfar.hitmarker.client.ModClientNeoForge;
import tfar.hitmarker.network.PacketHandlerNeoforge;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HitMarker.MODID)
public class HitMarkerNeoForge {

    public HitMarkerNeoForge(IEventBus bus, Dist dist) {
        bus.addListener(this::sounds);
        bus.addListener(PacketHandlerNeoforge::register);
        if (dist.isClient()) {
            bus.addListener(ModClientNeoForge::clientSetup);
            bus.addListener(ModClientNeoForge::rOverlay);
        }
        NeoForge.EVENT_BUS.addListener(this::hit);
        NeoForge.EVENT_BUS.addListener(this::death);
    }

    private void hit(LivingDamageEvent.Post e) {
        HitMarker.hit(e.getEntity(),e.getSource());
    }

    private void death(LivingDeathEvent e) {
        HitMarker.death(e.getEntity(), e.getSource());
    }


    private void sounds(RegisterEvent e) {
        e.register(Registries.SOUND_EVENT, HitMarker.HIT.getLocation(),() -> HitMarker.HIT);
    }
}
