package com.darkere.moredragoneggs.fabriclike.config;

import com.darkere.moredragoneggs.MoreDragonEggs;
import com.darkere.moredragoneggs.config.IForgeConfigHelper;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class ForgeConfigHelperFabricLike implements IForgeConfigHelper {

  @Override
  public void registerServerConfig(ForgeConfigSpec spec) {
    ModLoadingContext.registerConfig(MoreDragonEggs.MOD_ID, ModConfig.Type.SERVER, spec);
  }

  @Override
  public void registerClientConfig(ForgeConfigSpec spec) {
    ModLoadingContext.registerConfig(MoreDragonEggs.MOD_ID, ModConfig.Type.CLIENT, spec);
  }

  @Override
  public void registerCommonConfig(ForgeConfigSpec spec) {
    ModLoadingContext.registerConfig(MoreDragonEggs.MOD_ID, ModConfig.Type.COMMON, spec);
  }

}
