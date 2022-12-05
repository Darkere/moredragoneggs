package com.darkere.moredragoneggs.fabric;

import com.darkere.moredragoneggs.fabriclike.MoreDragonEggsFabricLike;
import net.fabricmc.api.ModInitializer;

public class MoreDragonEggsFabric implements ModInitializer {

  @Override
  public void onInitialize() {
    MoreDragonEggsFabricLike.init();
  }
}
