package com.darkere.moredragoneggs.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModServerConfigs {

  public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
  public static final ForgeConfigSpec SPEC;

  public static final ForgeConfigSpec.BooleanValue SPAWN_DRAGON_HEAD;

  static {
    BUILDER.push("More Dragon Eggs").translation("config.moredragoneggs.title");
    // Mod configs
    SPAWN_DRAGON_HEAD = BUILDER.comment("Also Spawn a Dragon head on the Pedestal.")
        .translation("config.moredragoneggs.spawnhead").define(
            "spawnHead",
            false
        );
    BUILDER.pop();
    SPEC = BUILDER.build();
  }

  public boolean shouldSpawnHead() {
    return SPAWN_DRAGON_HEAD.get();
  }
}
