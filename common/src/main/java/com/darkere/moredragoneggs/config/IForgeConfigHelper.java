package com.darkere.moredragoneggs.config;

import net.minecraftforge.common.ForgeConfigSpec;

public interface IForgeConfigHelper {

  void registerServerConfig(ForgeConfigSpec spec);

  void registerClientConfig(ForgeConfigSpec spec);

  void registerCommonConfig(ForgeConfigSpec spec);
}
