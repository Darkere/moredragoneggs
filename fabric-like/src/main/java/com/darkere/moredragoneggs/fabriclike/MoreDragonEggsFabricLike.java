package com.darkere.moredragoneggs.fabriclike;

import com.darkere.moredragoneggs.MoreDragonEggs;
import com.darkere.moredragoneggs.config.ModServerConfigs;
import com.darkere.moredragoneggs.fabriclike.config.ForgeConfigHelperFabricLike;

public class MoreDragonEggsFabricLike {

  public static void init() {
    MoreDragonEggs.init();

    new ForgeConfigHelperFabricLike().registerServerConfig(ModServerConfigs.SPEC);
    new ForgeConfigHelperFabricLike().registerCommonConfig(ModServerConfigs.SPEC);
  }
}
