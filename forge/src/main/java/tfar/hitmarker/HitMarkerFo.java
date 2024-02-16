package tfar.hitmarker;

import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;
import tfar.hitmarker.client.HitMarkerClientForge;
import tfar.hitmarker.network.PacketHandlerForge;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HitMarker.MODID)
public class HitMarkerFo {

    public HitMarkerFo() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::sounds);
        bus.addListener(this::setup);
        if (FMLEnvironment.dist.isClient()) {
            bus.addListener(HitMarkerClientForge::clientSetup);
            bus.addListener(HitMarkerClientForge::rOverlay);
        }
        MinecraftForge.EVENT_BUS.addListener(this::hit);
        MinecraftForge.EVENT_BUS.addListener(this::death);
    }

    private void hit(LivingDamageEvent e) {
        HitMarker.hit(e.getEntity(),e.getSource());
    }

    private void death(LivingDeathEvent e) {
        HitMarker.death(e.getEntity(), e.getSource());
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandlerForge.registerMessages(HitMarker.MODID);
    }

    private void sounds(RegisterEvent e) {
        e.register(Registries.SOUND_EVENT, HitMarker.HIT.getLocation(),() -> HitMarker.HIT);
    }
}
