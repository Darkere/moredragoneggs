package com.darkere.moredragoneggs;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {

    private ForgeConfigSpec.BooleanValue spawnHead;
    public ForgeConfigSpec spec;

    public ServerConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        spawnHead = builder.comment("Also Spawn a Dragon head on the Pedestal.").define("spawnHead", false);
        spec = builder.build();

    }

    public boolean shouldSpawnHead() {
        return spawnHead.get();
    }
}
