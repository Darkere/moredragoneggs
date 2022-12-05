package com.darkere.moredragoneggs.forge;

import com.darkere.moredragoneggs.MoreDragonEggs;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoreDragonEggs.MOD_ID)
public class MoreDragonEggsForge {

  public MoreDragonEggsForge() {
    // Submit our event bus to let architectury register our content on the right time
    EventBuses.registerModEventBus(MoreDragonEggs.MOD_ID,
        FMLJavaModLoadingContext.get().getModEventBus());
    MoreDragonEggs.init();
  }
}
