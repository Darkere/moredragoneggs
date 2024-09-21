package com.darkere.moredragoneggs;


import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {

    private ModConfigSpec.BooleanValue spawnHead;
    public ModConfigSpec spec;

    public ServerConfig() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        spawnHead = builder.comment("Also Spawn a Dragon head on the Pedestal.").define("spawnHead", false);
        spec = builder.build();

    }

    public boolean shouldSpawnHead() {
        return spawnHead.get();
    }
}
