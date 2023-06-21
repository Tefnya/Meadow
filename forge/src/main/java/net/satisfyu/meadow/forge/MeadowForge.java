package net.satisfyu.meadow.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.forge.villager.ForgeVillager;
import net.satisfyu.meadow.forge.world.ForgeEntitySpawn;
import net.satisfyu.meadow.terrablender.MeadowRegion;

@Mod(Meadow.MOD_ID)
public class MeadowForge {
    public MeadowForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(Meadow.MOD_ID, modEventBus);

        Meadow.init();
        ForgeVillager.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork( () -> {
            MeadowRegion.loadTerrablender();
           // ForgeVillager.registerPOIs();
            ForgeEntitySpawn.registerEntitySpawn();
        });
        Meadow.commonSetup();
    }
}
